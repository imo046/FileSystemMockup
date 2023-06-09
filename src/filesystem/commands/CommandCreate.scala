package filesystem.commands

import filesystem.State
import filesystem.files.{Dir, Directory}

abstract class CommandCreate(name: String) extends Command {
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
      doCreateEntry(name, s)
    }

  }

  def checkIllegal(str: String): Boolean = Seq(".", ",").contains(str)

  def doCreateEntry(name: String,currentState: State): State = {

    val currentWD = currentState.workdir


    /*
    someDir
      /a
      /b
      (new) /d  =>
    new someDir
      /a
      /b
      /d
     */
    def updateStructure(currentDir: Directory, dirs: List[String], newDir: Dir):Directory  = {
      if (dirs.isEmpty) currentDir.addEntry(newDir) //adds new dir to a current directory, returns updated current directory with the same name and path and new dir as its content
      else {
        //find head of the path
        println(dirs)
        val originalEntry = currentDir.findEntry(dirs.head)//always a directory
        println(originalEntry)
        currentDir.updateEntry(originalEntry.dirName, updateStructure(originalEntry.asDirectory,dirs.tail, newDir))

      }
    }


    /*
      1. all the directories in the fullPath
      2. create new directory entry in the working directory
      3. update the whole directory structure starting from the root
      4. After adding a new directory, find new working directory instance given wd's full path, in the new directory structure
       */
    //1.
    val dirsInPath = currentWD.getAllFoldersInPath

    //2.
    val newEntry:Dir = doCreateSpecific(currentState)

    //3.
    val newRoot:Directory = updateStructure(currentState.root,dirsInPath,newEntry)

    //4.
    val newWorkDir = newRoot.findDescendant(dirsInPath)

    State(newRoot,newWorkDir)
  }

  def doCreateSpecific(s:State):Dir

}
