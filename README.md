# Minesweeper

A terminal-based implementation of the classic Minesweeper game, written in Java and built with Gradle. The game has a few key classes: `Cell`, `World`, `GameLogic`, and an `enum` called `TileType`. The main class for running the game is `MinesweeperApp`.

## Getting Started

To run the game, you will need to have Java and Gradle installed on your computer.

1. Clone the repository to your local machine:

```
$ git clone https://github.com/SverreNystad/minesweeper.git
```

2. Navigate to the directory containing the project:

```
$ cd minesweeper
```

3. Compile and run the project using Gradle:

```
$ gradle run
```

## Gameplay

The game begins by prompting the player to enter the dimensions of the game board (width and height) and the number of mines to be placed on the board. The player then clicks on cells to reveal what is hidden underneath. If the player reveals a mine, the game ends. If the player reveals all cells that are not mines, the player wins the game. The player can also flag cells as mines to keep track of which cells they think contain mines.

## Testing

The project includes test suites for much of the functionality. To run the tests, use the following command:

```
$ gradle test
```

## Future Improvements

- Implement a user-friendly graphical interface
- Add the ability to save and load games
- Add the ability to customize the game difficulty level
- Add the ability to play against other players or the computer.