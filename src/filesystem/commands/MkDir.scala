package filesystem.commands

import filesystem.State
import filesystem.files.{Dir, Directory}


class MkDir(name: String) extends CommandCreate(name) {
  //TODO: Implement proper check for illegal name

  override def doCreateSpecific(s: State): Dir = {
    Directory.empty(s.workdir.path,name)
  }
}

object MkDir {


}


