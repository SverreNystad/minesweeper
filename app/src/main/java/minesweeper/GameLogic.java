package minesweeper;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
    private boolean gameOver;
    private World world;

    public GameLogic() {
        this.gameOver = false;
    }

    public void startGame(int width, int height, int mines) {
        this.world = new World(width, height, mines);
    }


    public void clickCell(int x, int y) {
        clickCell(x, y, new HashSet<>());
    }
    
    private void clickCell(int x, int y, Set<Cell> visited) {
        if (gameOver || visited.contains(world.getCell(x, y))) {
            return;
        }
    
        visited.add(world.getCell(x, y));
        world.revealCell(x, y);
    
        if (world.getCell(x, y).getType() == TileType.MINE) {
            gameOver = true;
        }
    
        if (world.isGameWon()) {
            gameOver = true;
        }
    
        if (world.getCell(x, y).getNeighbourMineCount() == 0) {
            for (Cell neighbour : world.getCell(x, y).getNeighbours()) {
                clickCell(neighbour.getX(), neighbour.getY(), visited);
            }
        }
    }
    

    public boolean isGameOver() {
        return gameOver;
    }


    public void renderWorld() {
        world.renderInTerminal();
    }

    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.startGame(5, 5, 1);

        while (!game.isGameOver()) {
            game.renderWorld();
            
            int[] cords = GameLogic.getUserInput();
            game.clickCell(cords[0], cords[1]);
        }
        System.out.println("Game Over");
        game.renderWorld();
    }

    /**
     * Gets user input from the terminal
     * @return
     */
    private static int[] getUserInput() {
        int x, y;
        int[] coordinates = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x coordinate: ");
        x = scanner.nextInt();
        System.out.println("Enter y coordinate: ");
        y = scanner.nextInt();
        coordinates[0] = x;
        coordinates[1] = y;

        return coordinates;
    }
}
