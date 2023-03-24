package filesystem.commands

import filesystem.State

class UnknownCommand extends Command {
  override def apply(s: State): State = s.setMessage("Command not found!")
}
