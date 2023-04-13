package filesystem.files

import filesystem.TypeException

import java.nio.file.FileSystemException

class File(override val parentPath: String, val name: String, val contents: String) extends Dir(parentPath,dirName = name) {
   override def asDirectory: Directory = throw new TypeException("Cannot be converted to directory!")

   override def getType: String = "File"

  override def asFile: File = this
}


object File {
  def empty(parentPath: String, name: String): File = new File(parentPath, name, "")

}