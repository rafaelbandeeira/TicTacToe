import java.lang.Exception

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

            if (board[x-1][y-1].isNotEmpty())
                println("Position already taken, try again")
            else {
                board[x-1][y-1] = "X"
                displayBoard()
            }
        } catch (e: Exception) {
            println("Invalid input")
        }
    } while (continueGame)
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