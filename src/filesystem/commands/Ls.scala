package filesystem.commands

import filesystem.State
import filesystem.files.Dir

class Ls extends Command {


  def listContents(contents: List[Dir]): String = {
    if (contents.isEmpty) ""

    else {
      val entry = contents.head
      entry.dirName + " [" + entry.getType + "]" + "\n" + listContents(contents.tail)
    }
  }

  override def apply(s: State): State = {
    val contents = s.workdir.contents
    val out = listContents(contents)
    s.copy(history = out)
  }


}


object Ls {

}