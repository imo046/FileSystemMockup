package filesystem.files

abstract class Dir(val parentPath: String, val dirName:String) {

  def path: String = parentPath + Directory.SEPARATOR + dirName

  def asDirectory:Directory

  def asFile:File

  def getType: String

}
