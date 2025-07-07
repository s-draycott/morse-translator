package com.sdraycott.morsetranslator

import scala.io.StdIn.readLine

object InputHandler {
  def getUserInput(): String = {
    println("\nEnter either Morse or English text to translate (or type 'exit' to quit)\n❗ Make sure to separate morse letters with a space and words with a slash '/'")
    readLine().trim.toLowerCase
  }
  
  def inputDetector(input: String): Either[String,String] = {
    val morseInput = Set('.', '-', ' ', '/')
    if(input.trim.isEmpty) {
      Left("⚠️ Please enter English or Morse to translate")
    } else if (input.forall(morseInput.contains)) {
      Right("morse")
    }  else {
      Right("english")
    }
    //.forall(...) is a method on sequences (and strings, since they’re sequences of chars) that checks if all elements satisfy a given condition.
  }
}
