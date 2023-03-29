package filesystem.commands

import filesystem.State


/*
Able to modify current State
 */
trait Command {
  def apply(s:State):State
}


object Command {

  val MKDIR = "mkdir"
  val LS = "ls"
  val EXIT = "exit"

  def emptyCommand = new Command {
    override def apply(s: State): State = s
  }

  def exitCommand = new Command {
    override def apply(s: State): State = s.copy(running = false)
  }

  def incompleteCommand(name:String) = new Command {
    override def apply(s: State): State = s.copy(history = name + ": incomplete commad")
  }

  def from(input:String):Command = {

    if (input.isEmpty) return emptyCommand

    val tokens = input.split(" ")

    tokens.head match {
      case MKDIR if tokens.length < 2 => incompleteCommand(MKDIR)
      case MKDIR => new MkDir(tokens(1))
      case LS => new Ls
      case EXIT => exitCommand
      case _ => new UnknownCommand
    }
  } //modify to add commands
}