# Minesweeper
Classic Minesweeper game, written in Java as a console game. The board size is 9x9 and the user can choose bombs number (from 1 to 80).
## Gameplay
The goal is to uncover all the squares on the field, that do not contain bombs, without being "blown up" by checking the square with the bomb underneath.
## How to play
After choosing bombs number the game prints the field:

![Field of the game](/images/field.JPG)

User can type 2 types of commands:
` X Y free` or `X Y mine`, where `X` is the column number and `Y` is the row number. `free` command means user wants to check that particular square, while `mine` command is to flag the square. If the square is checked and has no bomb underneath, it reveals the number of bombs around it. If the square is empty and has no mines around, all the squares around it are explored automatically. The first move is always bomb-free.

`free` command example:

![Free command example](/images/free.JPG)

`mine` command example:

![Mine command example](/images/mine.JPG)

The square can be printed as:
* `.` - if the square is unexplored
* `/` - if the square is checked without mines around it
* `*` - if it's flagged by the user as bomb
* number `1-8` - if the square is checked and has mines around it

## How to win
The game ends when all squares without bombs are checked or when user properly flag all of the bombs.
