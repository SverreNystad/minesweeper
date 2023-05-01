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
$ gradlew run
```

## Gameplay

The game begins by prompting the player to enter the dimensions of the game board (width and height) and the number of mines to be placed on the board. The player then clicks on cells to reveal what is hidden underneath. If the player reveals a mine, the game ends. If the player reveals all cells that are not mines, the player wins the game. The player can also flag cells as mines to keep track of which cells they think contain mines.

## Testing

The project includes test suites for much of the functionality. To run the tests, use the following command:

```
$ gradlew test
```

## Future Improvements

- Implement a user-friendly graphical interface
- Add the ability to save and load games
- Add countdown.
- Add score board.

## Notes
The project was a challenge for myself to implement the game in under 2 hours. The next face is to refactor the application.

### Learning point
* Try to design top down not bottom up
  * I Saw that i created things i did not need. In the End every models only purpose was to serve the top classes so if i had started with them I could have created a simpler design and spared time.
* TDD works pretty good and ensures test suit.
  * I made some large changes in the last hour because i realised after play testing that the grid with mines should not place mines before the first player move. This made me refactor some parts to make it easier to make the change and keep a version without the change as both are valid.
  * Tests made changes much easier later on as when refactoring i could check if it did as expected.
  * Remember to keep test suit updated when purpose of methods change. (lost 10 min to mental model being outdated XD)
  * I switched to TDD mid project and development vent much smoother. Need to test this approach more.