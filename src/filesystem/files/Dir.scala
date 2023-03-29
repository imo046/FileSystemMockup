package filesystem.files

abstract class Dir(val parentPath: String, val dirName:String) {

  def path: String = parentPath + Directory.SEPARATOR + dirName

  def asDirectory:Directory

  def getType: String

}
