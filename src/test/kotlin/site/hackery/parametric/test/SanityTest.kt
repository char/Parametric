package site.hackery.parametric.test

import org.junit.Assert
import org.junit.Test
import site.hackery.parametric.Command
import site.hackery.parametric.CommandManager

class SanityTest {
    var hasRanTestCommand = false

    @Test
    fun test() {
        val commandManager = CommandManager()
        commandManager.registerCommand(Command.declare("test") {
            branch {
                hasRanTestCommand = true
            }
        })

        commandManager.executeCommand("test")

        Assert.assertEquals(true, hasRanTestCommand)
    }
}
