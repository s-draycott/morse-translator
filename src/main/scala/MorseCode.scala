package com.sdraycott.morsetranslator

// this is set as an object to keep it as a singleton (only instance)
// used val as immutable
object MorseCode {
  val morseCode: Map[Char, String] = Map(
    'A' -> ".-", 'B' -> "-...", 'C' -> "-.-.", 'D' -> "-..",
    'E' -> ".",    'F' -> "..-.", 'G' -> "--.",  'H' -> "....",
    'I' -> "..",   'J' -> ".---", 'K' -> "-.-",  'L' -> ".-..",
    'M' -> "--",   'N' -> "-.",   'O' -> "---",  'P' -> ".--.",
    'Q' -> "--.-", 'R' -> ".-.",  'S' -> "...",  'T' -> "-",
    'U' -> "..-",  'V' -> "...-", 'W' -> ".--",  'X' -> "-..-",
    'Y' -> "-.--", 'Z' -> "--..", ' ' -> "/"
  )
}
