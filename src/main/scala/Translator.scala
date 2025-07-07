package com.sdraycott.morsetranslator

object Translator {

  //translate english to morse code string
  //either is the return type of the function and allows for error type (left) or success outcome type (right)
  def englishToMorse(input: String): Either[String, String] = {
    //splits the english sentence into words using space
    val words = input.toUpperCase().split(" ")

    /*this code creates a new value 'moreseWords' which we take the string array of 'words' created above and say that for each word => in 'words' we are going to create a val morseChars that is a character array of that word
    then for each Char we say does it match something in the MorseCode map. Some means there is a match (morse) is a value I've given to represent that matching char.
    the code then returns that match as right (i.e. tagging it as a success case) if there's no match 'None' then it is tagged as Left (error case)
    */
    val morseWords = words.map { word =>
      val morseChars = word.toCharArray.map { char =>
        MorseCode.morseCode.get(char) match { //the .get returns the value of the map not the key so returns the morse code
          case Some(morse) => Right(morse)
          case None => Left(s"Invalid character: '$char'")
        }
      }
      sequenceEither(morseChars).map(_.mkString(" ")) //this will only run it they are all Rights
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

  /*
  sequence either is a method with parameters of type L and R
  the parameter 'seq' is the name of the paramter and the type is a Seq (like a list) of either type L or R e.g Right("..."), Right(".-."), Left(Error) etc...
  the ':Either[L, Seq[R]] is the return type of the function which in this case will either be a list of rights or a single left (the first input to cause an error)
   */
  private def sequenceEither[L, R](seq: Seq[Either[L, R]]): Either[L, Seq[R]] = {
    seq.foldRight(Right(Nil): Either[L, List[R]]) { (element, accumulatedResult) => //fold right lets you loop over the list from right to left to find either a list of rights (List[R]) or a single error case left L. It starts with Right[Nil] to create an empty list that's tagged with right. i.e. success case. 'e' refers to each element from the sequence 'acc' is the accumulated result so far
      for {
        xs <- accumulatedResult //if accumulated list is all Right(listOfResults) extract that list to xs
        x <- element //if element is a right(morseChar) extract the Morse character into x
        //if either of these are left then the code block short circuits and returns that left   -- this is built in to the Either function within scala (if every step is right the either function keeps going if any step is left it breaks and returns that left)
      } yield x :: xs //appends x to the list xs
    }
  }
}
