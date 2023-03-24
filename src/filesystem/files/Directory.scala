package filesystem.files

class Directory(
                 override val parentPath: String,
                 override val dirName: String,
                 contents: List[Dir] //can be custom
               ) extends Dir(parentPath, dirName) {


  def hasEntry(path:String):Boolean = ???

}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())

  def ROOT: Directory = Directory.empty("","")

}
