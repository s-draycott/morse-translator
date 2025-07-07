package com.sdraycott.morsetranslator

object OutputHandler {
  def printResult(result: Either[String, String]): Unit = {
    result match {
      case Right(value) => println(s"\n✅ Result: $value")
      case Left(error) => println(s"\n❌ Error: $error")
    }
  }

  def welcome(): Unit = {
    println("\uD83D\uDC4B Welcome to the Morse Code Translator \uD83D\uDCE1\uD83D\uDD24")
  }
}
