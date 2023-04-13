package filesystem.files

import filesystem.TypeException

import scala.annotation.tailrec

//TODO: Use linked list
class Directory(
                 override val parentPath: String,
                 override val dirName: String,
                 val contents: List[Dir] //can be custom
               ) extends Dir(parentPath, dirName) {


  def hasEntry(path:String):Boolean = findEntry(path) != null

  def getAllFoldersInPath:List[String] = {
    this.path.substring(1).split(Directory.SEPARATOR).toList.filter(_.nonEmpty)
  }

  def findDescendant(path:List[String]): Directory = {
    if (path.isEmpty) this
    else {
      findEntry(path.head).asDirectory.findDescendant(path.tail)
    }
  }

  def addEntry(dir:Dir):Directory = new Directory(parentPath,dirName, contents :+ dir)

  def updateEntry(oldEntry:String,newEntry:Dir):Directory = new Directory(parentPath, dirName, contents.filter(entry => !entry.dirName.equals(oldEntry)) :+ newEntry)

  def findEntry(name: String): Dir = {

    @tailrec
    def findEntryHelper(name: String, contentlist: List[Dir]):Dir = {
      if (contentlist.isEmpty) null
      else if (contentlist.head.dirName.equals(name)) contentlist.head
      else findEntryHelper(name, contentlist.tail)
    }

    findEntryHelper(name,contents)

  }

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def asFile: File = throw new TypeException("Cannot be converted to file!")
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())

  def ROOT: Directory = Directory.empty(ROOT_PATH,"")

}
