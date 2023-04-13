package filesystem.commands

import filesystem.State

class Pwd extends Command {
  override def apply(s: State): State = s.setMessage(s.workdir.parentPath)
}


object Pwd {

}