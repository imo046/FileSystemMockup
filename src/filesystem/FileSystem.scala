package filesystem

import filesystem.commands.Command
import filesystem.files.Directory

import java.util.Scanner

object FileSystem extends App {

  private val scanner = new Scanner(System.in)

  val root = Directory.ROOT
  /*
  TODO: Get rid of var
   */
  var s = State(root,root)





  //main event loop
  while (s.running) {
    s.show()
    val userInput = scanner.nextLine()
    s = Command.from(userInput).apply(s) //apply new command to a current state, return new updated state
  }

}
