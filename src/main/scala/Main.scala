package com.sdraycott.morsetranslator

import jdk.nashorn.internal.ir.TryNode

object Main extends App {
  OutputHandler.welcome()
  var running = true
  while(running) {
    val input = InputHandler.getUserInput()
    val inputType = InputHandler.inputDetector(input)
    
    if(input.toLowerCase == "exit") {
      println("Goodbye \uD83D\uDC4B")
      running = false
    } else if (input.isEmpty) {
      println("⚠️ Please enter English or Morse to translate")
    } else if (inputType == Right("english")) {
      OutputHandler.printResult(Translator.englishToMorse(input))
    } else if(inputType == Right("morse")) {
      OutputHandler.printResult(Translator.morseToEnglish(input))
    } else {
      println("⚠️ Unknown choice. Please try again.")
    }
    
  }
}
