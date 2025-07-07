package com.sdraycott.morsetranslator

import com.sdraycott.morsetranslator
import org.scalatest.funsuite.AnyFunSuite

class MorseTranslatorTest extends AnyFunSuite {
  test("Empty string input should return error") {
    val result = InputHandler.inputDetector(" ")
    assert(result == Left("⚠️ Please enter English or Morse to translate"))
  }

  test("English to Morse - simple word") {
    val result = Translator.englishToMorse("SOS")
    assert(result == Right("... --- ..."))
  }

  test("English to Morse - sentence") {
    val result = Translator.englishToMorse("Hello nice to meet you")
    assert(result == Right(".... . .-.. .-.. --- / -. .. -.-. . / - --- / -- . . - / -.-- --- ..-"))
  }

  test("Morse to english") {
    val result = Translator.morseToEnglish(".... . .-.. .-.. --- / -. .. -.-. . / - --- / -- . . - / -.-- --- ..-")
    assert(result == Right("HELLO NICE TO MEET YOU"))
  }

  test("Invalid English input - wrong character") {
    val result = Translator.englishToMorse("Hee/o")
    assert(result == Left("Invalid character: '/'"))
  }

  test("Invalid morse code - wrong characters") {
    val result = Translator.morseToEnglish("///")
    assert(result == Left("Invalid Morse Code: '///'"))
  }

  test("Invalid morse code - wrong morse") {
    val result = Translator.morseToEnglish(".- --- ... --..--")
    assert(result == Left("Invalid Morse Code: '--..--'"))
  }
}
