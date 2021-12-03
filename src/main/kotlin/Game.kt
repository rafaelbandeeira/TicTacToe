import java.lang.Exception
import kotlin.random.Random

var board = arrayListOf<ArrayList<String>>()

fun main() {
    for (x in 0..2) {
        val row = arrayListOf<String>()
        for (y in 0..2)
            row.add("")
        board.add(row)
    }

    displayBoard()

    var continueGame = true

    do {
        println("Enter position (e.g. 1, 2)")
        val input = readLine() ?: ""
        var x = 0
        var y = 0

        try {
            val positions = input.split(",")
            x = positions[0].trim().toInt()
            y = positions[1].trim().toInt()
            var skipRound = false

            if (board[x-1][y-1].isNotEmpty()) {
                println("Position already taken, try again")
                skipRound = true
            }
            else {
                board[x-1][y-1] = "X"
                displayBoard()
            }

            if (!skipRound) {
                val playerWon = checkWinner(true)
                if (playerWon) {
                    println("You won, mate!")
                    continueGame = false
                }

                val isBoardFull = checkBoardFull()
                if (!playerWon && isBoardFull) {
                    println("It's a bloody tie!")
                    continueGame = false
                }

                if (continueGame) {
                    placeComputerMove()
                    displayBoard()
                    val computerWon = checkWinner(false)

                    if (computerWon) {
                        println("Computer won")
                        continueGame = false
                    }
                }
            }
        } catch (e: Exception) {
            println("Invalid input")
        }
    } while (continueGame)
}

fun checkWinner(player: Boolean): Boolean {
    var won = false
    val checkSymbol = if (player) "X" else "O"
    for (i in 0..2) {
        if (board[i][0] == checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol) {
            won = true
            break
        }

        if (board[0][i] == checkSymbol && board[1][i] == checkSymbol && board[2][i] == checkSymbol) {
            won = true
            break
        }
    }
    if (board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol)
        won = true
    if (board[0][2] == checkSymbol && board[1][1] == checkSymbol && board[2][0] == checkSymbol)
        won = true

    return won
}

fun checkBoardFull(): Boolean {
    var isBoardFull = true
    for (x in 0..2) {
        for (y in 0..2) {
            if (board[x][y].isEmpty()) {
                isBoardFull = false
                break
            }
        }
    }

    return isBoardFull
}

fun placeComputerMove() {
    var x = 0
    var y = 0
    do {
        x = Random.nextInt(until = 3)
        y = Random.nextInt(until = 3)
    } while (board[x][y].isNotEmpty())

    board[x][y] = "O"
}

fun displayBoard() {
    println("-------------")
    for (x in 0..2) {
        for (y in 0..2) {
            when (board[x][y]) {
                "X" -> print("| X ")
                "O" -> print("| O ")
                else -> print("|   ")
            }
        }
        println("|")
        println("-------------")
    }
}