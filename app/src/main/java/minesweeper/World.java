package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;

    private List<ArrayList<Cell>> grid;

    /**
     * Creates a new world with the given width and height and the given number of mines
     * It will validate the given parameters and throw an IllegalArgumentException if they are invalid
     * It does not ensure that the first click is not a mine
     * @param width
     * @param height
     * @param start_mines
     */
    public World(int width, int height, int start_mines) {
        this.width = width;
        this.height = height;
        this.grid = new ArrayList<ArrayList<Cell>>();
        validateWorld(width, height, start_mines);

        // Initialize the grid
        this.grid = makeGrid(width, height);

        // Add mines to the grid
        addMinesToGrid(this.grid, start_mines);

        // Add Neighbours
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x ++) {
                
                List<Cell> neighbours = new ArrayList<Cell>();
                // Neighbors are the 8 cells around the current cell
                if (x > 0) {
                    neighbours.add(this.grid.get(y).get(x-1));
                }
                if (x < width - 1) {
                    neighbours.add(this.grid.get(y).get(x+1));
                }
                if (y > 0) {
                    neighbours.add(this.grid.get(y-1).get(x));
                }
                if (y < height - 1) {
                    neighbours.add(this.grid.get(y+1).get(x));
                }
                if (x > 0 && y > 0) {
                    neighbours.add(this.grid.get(y-1).get(x-1));
                }
                if (x < width - 1 && y > 0) {
                    neighbours.add(this.grid.get(y-1).get(x+1));
                }
                if (x > 0 && y < height - 1) {
                    neighbours.add(this.grid.get(y+1).get(x-1));
                }
                if (x < width - 1 && y < height - 1) {
                    neighbours.add(this.grid.get(y+1).get(x+1));
                }
                this.grid.get(y).get(x).setNeighbours(neighbours);
            }
        }

    }

    private static void validateWorld(int x, int y, int mineCount) throws IllegalArgumentException {
        // Check that the world is valid
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        if (mineCount < 0) {
            throw new IllegalArgumentException("Mine count must be positive");
        }
        
        if (mineCount >= x * y) {
            throw new IllegalArgumentException("Too many mines");
        }

    }

    private static void addMinesToGrid(List<ArrayList<Cell>> grid, int mineCount) {
        // Add mines
        for (int i = 0; i < mineCount; i++) {
            int x = (int) (Math.random() * grid.get(0).size());
            int y = (int) (Math.random() * grid.size());
            grid.get(y).get(x).setType(TileType.MINE);
        }
    }

    private static void addMinesToGrid(List<ArrayList<Cell>> grid, int mineCount, int startX, int startY) {
        // Add mines
        int x = startX;
        int y = startY;
        for (int i = 0; i < mineCount; i++) {
            while (startX == x && startY == y) {
                x = (int) (Math.random() * grid.get(0).size());
                y = (int) (Math.random() * grid.size());
                if (grid.get(y).get(x).getType() != TileType.MINE) {
                    grid.get(y).get(x).setType(TileType.MINE);
                }
            }
        }
    }

    private static List<ArrayList<Cell>> makeGrid(int width, int height) {
        List<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
        for (int y = 0; y < height; y++) {
            grid.add(new ArrayList<Cell>());
        }
        // Add cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x ++) {
                grid.get(y).add(new Cell(x,y));
            }
        }
        return grid;
    }

    public World(int width, int height, int start_mines, int start_x, int start_y) {
        
        this(width, height, start_mines);
    }
    public Cell getCell(int x,int y) throws IndexOutOfBoundsException {
        return this.grid.get(y).get(x);
    }



    public void revealCell(int x, int y) {
        Cell cell = this.getCell(x, y);
        cell.setFlag(false);
        cell.reveal();
        if (cell.getType() == TileType.GRASS && cell.getNeighbourMineCount() == 0) {
            for (Cell neighbour : cell.getNeighbours()) {
                if (!neighbour.isRevealed()) {
                    this.revealCell(neighbour.getX(), neighbour.getY());
                }
            }
        }
    }

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

    public void flagCell(int x, int y) {
        Cell cell = this.getCell(x, y);
        cell.setFlag(!cell.isFlagged());
    }

    public void revealAllMines() {
        for (ArrayList<Cell> row : this.grid) {
            for (Cell cell : row) {
                if (cell.getType() == TileType.MINE) {
                    cell.reveal();
                }
            }
        }
    }

    public void renderInTerminal() {
        System.out.println("World:");
        for (int y = 0; y < this.height; y++) {
            String line = "";
            for (int x = 0; x < this.width; x++) {
                Cell cell = this.getCell(x, y);
                if (cell.isRevealed()) {
                    if (cell.getType() == TileType.MINE) {
                        line += "|X|";
                    } else {
                        line += "|" + cell.getNeighbourMineCount() + "|";
                    }
                } else if (cell.isFlagged()) {
                    line += "|F|";
                } else {
                    line += "|_|";
                }
            }
            System.out.println(line);
        }
    }

    public boolean isGameWon() {
        for (ArrayList<Cell> row : this.grid) {
            for (Cell cell : row) {
                if (cell.getType() != TileType.MINE && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        
        World w = new World(10, 10, 10);
        w.renderInTerminal();
        for (int y = 0; y < 10;y++) {
            for (int x = 0; x < 10; x++) {
                w.revealCell(x, y);
                w.renderInTerminal();
            }
        }
        


    }
}
