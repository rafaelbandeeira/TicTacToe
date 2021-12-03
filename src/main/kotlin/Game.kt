var board = arrayListOf<ArrayList<String>>()

fun main() {
    for (x in 0..2) {
        val row = arrayListOf<String>()
        for (y in 0..2)
            row.add("")
        board.add(row)
    }

    displayBoard()
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