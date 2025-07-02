package com.sdraycott.morsetranslator

import jdk.nashorn.internal.ir.TryNode

object Main extends App {
  val input = "Sally"
  Translator.englishToMorse(input) match {
    case Right(morse) => println(s"Morse: $morse")
    case Left(error) => println(s"Error: $error")
  }

  val input2 = ".... . .-.. .-.. --- / ... .- .-.. .-.. -.--"
  Translator.morseToEnglish(input2) match {
    case Right(english) => println(s"Translation: $english")
    case Left(error) => println(s"Error: $error")
  }
}
