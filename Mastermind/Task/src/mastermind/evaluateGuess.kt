package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val positions = getGuessedInRightPositions(secret, guess)
    val letters = getGuessedInWrongPositions(secret, guess)

    return Evaluation(positions, letters)
}

private fun getGuessedInRightPositions(secret: String, guess: String): Int {
    var positions = 0
    for(i in secret.indices) {
        if(secret[i] == guess[i]) {
            positions ++
        }
    }
    return positions
}

private fun getGuessedInWrongPositions(secret: String, guess: String): Int {
    var letters = 0
    var newSecret = ""
    var newGuess = ""

    for(i in secret.indices) {
        if(secret[i] != guess[i]) {
            newSecret += secret[i]
            newGuess += guess[i]
        }
    }

    val evaluatedChars = mutableListOf<Char>()
    if(newSecret.isNotEmpty()) {
        for (element in guess) {
            if (!evaluatedChars.contains(element)) {
                val howManyInSecret = countHowMany(newSecret, element)
                val howManyInGuess = countHowMany(newGuess, element)
                letters += if (howManyInSecret == howManyInGuess || howManyInSecret > howManyInGuess) howManyInGuess
                else howManyInSecret

                evaluatedChars.add(element)
            }
        }
    }

    return letters
}

private fun countHowMany(letters: String, letter: Char): Int {
    var howMany = 0
    for (element in letters) {
        if (element == letter) {
            howMany++
        }
    }
    return howMany
}
