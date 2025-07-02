package com.sdraycott.morsetranslator

object Translator {

  //translate english to morse code string
  //either allows for error type (left) or success outcome type (right)
  def englishToMorse(input: String): Either[String, String] = {
    val words = input.toUpperCase().split(" ")
    val morseWords = words.map { word => //essentially this means for each word in words
      val morseChars = word.toCharArray.map { char => //for each character in this word apply a function
        MorseCode.morseCode.get(char) match {
          case Some(morse) => Right(morse) //character found
          case None => Left(s"Invalid character: '$char'")
        }
      }
      sequenceEither(morseChars).map(_.mkString(" "))
    }
    sequenceEither(morseWords).map(_.mkString(" / "))
  }

  def morseToEnglish(input: String): Either[String, String] = {
    val reversedMap: Map[String, Char] = MorseCode.morseCode.map(_.swap) //underscore used as shorthand for each element
    val morseWords= input.split(" / ")

    //for each word, map morse code chars to either a string or char
    val englishWords: Array[Either[String, Seq[Char]]] = morseWords.map { word =>
      val chars = word.split(" ")

      //converts each morse char to english char using reversed map
      val charsOrErrors: Seq[Either[String, Char]] = chars.map { morseChar =>
          reversedMap.get(morseChar) match {
            case Some(c) => Right(c)
            case None => Left(s"Invalid Morse Code: '$morseChar''")
          }
      }
      sequenceEither(charsOrErrors)
    }
    //combine words into Either[String, Seq[Seq[Char]]]
    sequenceEither(englishWords).map { wordsSeq =>
      wordsSeq.map(_.mkString).mkString(" ")
    }
  }

  private def sequenceEither[L, R](seq: Seq[Either[L, R]]): Either[L, Seq[R]] = {
    seq.foldRight(Right(Nil): Either[L, List[R]]) { (e, acc) => //fold right lets you loop over the list from right to left to find either a list of rights (List[R]) or a single error case left L. It starts with Right[Nil] to create an empty list that's tagged with right. i.e. success case. 'e' refers to each element from the sequence 'acc' is the accumulated result so far
      for {
        xs <- acc //if acc is Right(listOfResults) extract that list to xs
        x <- e //if e is a right(morseChar) extract the Morse character into x
        //if either of these are left then the code block shor circuits and returns that left   -- this is built in to the Either function within scala (if every step is right the either function keeps going if any step is left it breaks and returns that left)
      } yield x :: xs
    }
  }
}
