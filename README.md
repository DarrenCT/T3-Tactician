# Tic-Tac-Toe

Tic Tac Toe in Java

How to Play:
1. Open TicTacToe.java in your preferred ide
2. run code
3. Have Fun!

Initial Game Board:<br />
![image](https://github.com/DarrenCT/Tic-Tac-Toe/assets/99516347/a83b9fc9-a67e-445c-800e-dfeb47706fd6)

Challenges:<br />
- The original version of this tic tac toe game was meant to just use the Math.Random class in java for the ai to make moves, but after finishing the project I decided to implement the minimax algorithm to make the ai unbeatable.
- It was challenging to implement the minimax algorithm because as you can see the game board is a 5x5 2d array instead of the traditional 3x3 layout.
- I had to store the pieces 'X', 'O' and ' ' in a seperate char[] and insert the pieces as the board is being printed each turn.
- Understanding and adapting the minimax algorithm to tic tac toe was also challenging, since it was a recursive algorithm, debugging took alot of time espesially with my unorthodox initial gameboard architecture.
