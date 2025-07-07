package com.sdraycott.morsetranslator

import jdk.nashorn.internal.ir.TryNode

object Main extends App {
  OutputHandler.welcome()
  var running = true
  while(running) {
    val input = InputHandler.getUserInput()
    val inputType = InputHandler.inputDetector(input)
    
    InputHandler.inputDetector(input) match {
      case Right("exit") =>
        println("Goodbye \uD83D\uDC4B")
        running = false

      case Right("morse") =>
        OutputHandler.printResult(Translator.morseToEnglish(input))

      case Right("english") =>
        OutputHandler.printResult(Translator.englishToMorse(input))

      case Left(error) =>
        println(error)
      
      case(other) =>
        println(s"Error: unexpected case $other ")

    }    
  }
}
