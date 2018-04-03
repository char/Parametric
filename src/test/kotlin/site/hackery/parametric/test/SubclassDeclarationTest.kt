package site.hackery.parametric.test

import org.junit.Assert
import org.junit.Test
import site.hackery.parametric.CommandDeclaration
import site.hackery.parametric.CommandManager

var output: String = ""

class SubclassDeclarationTest {
    @Test
    fun test() {
        output = ""

        val commandManager = CommandManager()
        commandManager.registerCommand(EchoCommandDeclaration())

        commandManager.executeCommand("echo Hello, world!")
        Assert.assertEquals("Hello, world!", output)
    }
}

class EchoCommandDeclaration : CommandDeclaration("echo") {
    init {
        branch { message: String ->
            output = message
        }
    }
}
