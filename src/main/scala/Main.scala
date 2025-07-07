package com.sdraycott.morsetranslator

import jdk.nashorn.internal.ir.TryNode

object Main extends App {
  OutputHandler.welcome()
  var running = true
  while(running) {
    InputHandler.getUserInput() match {
      case "1" =>
        val english = InputHandler.getEnglishInput()
        val morseResult = Translator.englishToMorse(english)
        OutputHandler.printResult(morseResult)

      case "2" =>
        val morse = InputHandler.getMorseInput()
        val englishResult = Translator.morseToEnglish(morse)
        OutputHandler.printResult(englishResult)

      case "3" =>
        println("\uD83D\uDC4B Goodbye!")
        running = false

      case other =>
        println(s"⚠️ Unknown choice '$other'. Please try again.")
    }
  }
}
