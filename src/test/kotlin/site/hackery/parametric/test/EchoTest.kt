package site.hackery.parametric.test

import org.junit.Assert
import org.junit.Test
import site.hackery.parametric.Command
import site.hackery.parametric.CommandManager

class EchoTest {
    var output: String = ""

    @Test
    fun test() {
        val commandManager = CommandManager()

        commandManager.registerCommand(Command.declare("echo") {
            alias("output")

            branch("reversed") { s: String ->
                output = StringBuilder(s).reverse().toString()
            }

            branch { s: String ->
                output = s
            }
        })

        commandManager.executeCommand("echo Hello, world!")
        Assert.assertEquals("Hello, world!", output)

        commandManager.executeCommand("output reversed Hello, world!")
        Assert.assertEquals("!dlrow ,olleH", output)
    }
}
