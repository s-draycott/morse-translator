package com.sdraycott.morsetranslator

import scala.io.StdIn.readLine

object InputHandler {
  def getUserInput(): String = {
    print("\nChoose an option by typing the number and pressing enter \n1: English → Morse\n2: Morse → English\n3: Exit: ")
    readLine().trim.toLowerCase
  }

  def getEnglishInput(): String ={
    print("\t\uD83D\uDD24 Enter English Text: ")
    readLine().trim
  }

  def getMorseInput(): String = {
    print("\uD83D\uDCE1 Enter Morse Text (separate letters with a space ' ' and words with a slash '/' : ")
    readLine().trim
  }
}
