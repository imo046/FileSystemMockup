package filesystem.commands

import filesystem.State
import filesystem.files.{Dir, File}

class Touch(name:String) extends CommandCreate(name) {
  override def doCreateSpecific(s: State): Dir = {
    File.empty(s.workdir.path,name)
  }
}


object Touch {

}