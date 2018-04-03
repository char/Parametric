package site.hackery.parametric

import site.hackery.parametric.parser.ArgumentParser

class Command(val name: String, val aliases: Array<String>) {
    companion object {
        fun declare(name: String, init: CommandDeclaration.() -> Unit): CommandDeclaration {
            val declaration = CommandDeclaration(name)
            init(declaration)

            return declaration
        }
    }

    val branches = mutableListOf<CommandBranch>()
    class CommandBranch(val name: String?, val aliases: Array<String>, val parameterTypes: Array<Class<*>>, private val handler: Function<*>, val typeHints: Map<Int, ArgumentParser<*>>) {
        fun execute(vararg args: Any) {
            handler.javaClass.declaredMethods.first { it.name == "invoke" }.apply { this.isAccessible = true }.invoke(handler, *args)
        }
    }
}
