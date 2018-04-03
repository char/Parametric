package site.hackery.parametric

import site.hackery.parametric.parser.ArgumentParser
import java.lang.reflect.ParameterizedType

open class CommandDeclaration(val name: String) {
    private val branches = mutableListOf<BranchDeclaration>()
    private val aliases = mutableListOf<String>()

    fun alias(aliasName: String) {
        aliases += aliasName
    }

    fun branch(branchName: String? = null, handler: Function<*>): BranchDeclaration {
        val params = handler.javaClass.genericInterfaces
        for (param in params) {
            if (param is ParameterizedType) {
                val parameterTypes = mutableListOf<Class<*>>()

                param.actualTypeArguments
                        .filterIndexed { index, _ -> index != param.actualTypeArguments.lastIndex }
                        .filterIsInstance<Class<*>>()
                        .forEach { parameterTypes += it }

                val branchDecl = BranchDeclaration(branchName, handler, parameterTypes.toTypedArray())
                branches += branchDecl

                return branchDecl
            }
        }

        throw IllegalStateException("Handler function had no generic parameters!")
    }

    fun buildCommand(): Command {
        val command = Command(name, aliases.toTypedArray())
        command.branches.addAll(
                branches.asSequence()
                        .map { Command.CommandBranch(it.branchName, it.aliases.toTypedArray(), it.parameterTypes, it.handler, it.typeHints) }
        )

        return command
    }

    inner class BranchDeclaration internal constructor(val branchName: String?, val handler: Function<*>, val parameterTypes: Array<Class<*>>) {
        internal val aliases = mutableListOf<String>()
        internal val typeHints = mutableMapOf<Int, ArgumentParser<*>>()

        fun alias(aliasName: String) {
            aliases += aliasName
        }

        fun typeHint(index: Int, parser: ArgumentParser<*>) {
            typeHints.put(index, parser)
        }
    }
}
