package filesystem

import filesystem.files.Directory

case class State(
                  root:Directory,
                  workdir:Directory,
                  history:String = "",
                  running:Boolean = true,
                )
{
  val SHELL_TOKEN = "$ "

  def show(): Unit = {
    if (history.nonEmpty) println(this.history)
    print(this.SHELL_TOKEN)
  }
  def setMessage(message:String):State = this.copy(history = message)

}
