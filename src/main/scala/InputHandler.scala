package com.sdraycott.morsetranslator

import scala.io.StdIn.readLine

object InputHandler {
  def getUserInput(): String = {
    println("\nEnter either Morse or English text to translate (or type 'exit' to quit)\n❗ Make sure to separate morse letters with a space and words with a slash '/'")
    readLine().trim.toLowerCase
  }
  
  def inputDetector(input: String): Either[String,String] = {
    val morseInput = Set('.', '-', ' ', '/')
    input.trim.toLowerCase match {
      case "" => Left("⚠️ Please enter English or Morse to translate")
      case "exit" => Right("exit")
      case s if s.forall(morseInput.contains) => Right("morse")
      case _ => Right("english")
    }
    //.forall(...) is a method on sequences (and strings, since they’re sequences of chars) that checks if all elements satisfy a given condition.
  }
}
