package site.hackery.parametric.parser

import site.hackery.parametric.context.CommandExecutionContext

interface ArgumentParser<out T> {
    fun parse(context: CommandExecutionContext): T?
    fun provideSuggestions(context: CommandExecutionContext): List<String> = emptyList()

    fun minimumAcceptedArguments(): Int = 1
}
