package site.hackery.parametric.context

import site.hackery.parametric.utils.PeekableIterator

class CommandExecutionContext(val arguments: PeekableIterator<String>, val parameters: Array<Class<*>>, var currentParameter: Int)
