package minesweeper;

import java.util.List;

public class Cell {
    private int x;
    private int y;
    private TileType type;
    private boolean flagged;
    private boolean revealed;
    private static TileType defaultType = TileType.GRASS;
    private List<Cell> neighbours;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = defaultType;
        this.flagged = false;
        this.revealed = false;
    }

    public Cell(int x, int y, TileType type) {
        this(x, y);
        this.type = type;
    }

    public TileType getType() {
        return this.type;
    }

    public void setFlag(boolean flag) {
        this.flagged = flag;
    }

    public boolean isFlagged() {
        return this.flagged;
    }
        
    public void setType(TileType type) {
        this.type = type;
    }

    public int getNeighbourMineCount() {
        int count = 0;
        for (Cell neighbour : this.getNeighbours()) {
            if (neighbour.getType() == TileType.MINE) {
                count++;
            }
        }
        return count;
    }

    public List<Cell> getNeighbours() {
        return this.neighbours;
    }
    

    public void setNeighbours(List<Cell> newNeighbours) {
        this.neighbours = newNeighbours;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void reveal() {
        this.revealed = true;
    }

}
