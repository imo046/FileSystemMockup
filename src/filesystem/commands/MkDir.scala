package filesystem.commands

import filesystem.State
import filesystem.files.Directory

class MkDir(name: String) extends Command {
  //TODO: Implement proper check for illegal name
  override def apply(s: State): State = {
    val workdir = s.workdir
    if (workdir.hasEntry(name)) {
      s.copy(history = s"$name already exists!")
    }
    else if (name.contains(Directory.SEPARATOR)) {
      s.copy(history = s"Use of separators is forbidden!")
    }
    else if (checkIllegal(name)) {
      s.copy(history = s"$name i s illegal path!")
    }
    else {
      runMkDir(name,s)
    }

  }

def checkIllegal(str: String):Boolean = ???

}

def runMkDir(name: String,currentState: State): State = ???
