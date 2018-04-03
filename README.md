# Parametric

A command execution system for Kotlin.

Parametric allows you to register commands and sub-branches of commands.

## Example

The following shows an `echo` command defined, with a default branch and a branch called 'reversed'

```kotlin
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
```
