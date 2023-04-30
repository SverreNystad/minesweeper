package minesweeper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldTest {
    @Test
    void testGetCell() {
        World world = new World(10, 10, 10);
        assert world.getCell(0, 0) != null;
    }

    @Test
    void testGetCellNeighbours() {
        World world = new World(10, 10, 10);
        assert world.getCell(0, 0).getNeighbours().size() == 3;
        assert world.getCell(1, 1).getNeighbours().size() == 8;
    }

    @Test
    void testGetCellNeighboursEdge() {
        World world = new World(10, 10, 10);
        assert world.getCell(0, 1).getNeighbours().size() == 5;
        assert world.getCell(9, 9).getNeighbours().size() == 3;
    }

    @Test
    void testMineCount() {
        World world = new World(10, 10, 10);
        int mineCount = 0;
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                if (world.getCell(x, y).getType() == TileType.MINE) {
                    mineCount++;
                }
            }
        }
        assert mineCount == 10;
    }

    @Test
    void testWorldToSmallForMines() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new World(10, 10, 101);
        });
    }

    @Test 
    void testWorldNegativeMines() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new World(10, 10, -1);
        });
    }

    @Test
    void testWorldNegativeWidth() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new World(-1, 10, 10);
        });
    }
    @Test
    void testWorldNegativeHeight() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new World(10, -1, 10);
        });
    }

    @Test
    void testWorldZeroParams() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new World(0, 0, 0);
        });
    }

    @Test
    void testRevealCell() {
        World world = new World(10, 10, 10);
        assert !world.getCell(0, 0).isRevealed();
        
        world.revealCell(0, 0);
        assert world.getCell(0, 0).isRevealed();
    }

    @Test
    void testRevealAllMines() {
        World world = new World(10, 10, 10);
        world.revealAllMines();
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                if (world.getCell(x, y).getType() == TileType.MINE) {
                    assert world.getCell(x, y).isRevealed();
                }
                else {
                    assert !world.getCell(x, y).isRevealed();
                }
            }
        }
    }

    @Test
    void testFlagCell() {
        World world = new World(10, 10, 10);
        assert !world.getCell(0, 0).isFlagged();
        
        world.flagCell(0, 0);
        assert world.getCell(0, 0).isFlagged();
        world.flagCell(0, 0);
        assert !world.getCell(0, 0).isFlagged();
    }

    @Test
    void testWorldIsSolvable() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new World(10, 10, 100);
        });
    }

}
