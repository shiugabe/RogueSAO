package gabsu.osu.rsao;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Aincrad {

    //initialize private members
    private Tile[][] tiles;
    private int width;
    private int height;
    public List<Creature> creatures;

    //public constructors
    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public Aincrad(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.creatures = new ArrayList<Creature>();
    }

    //gets creature at specific location
    public Creature creature(int x, int y) {
        for (Creature c : this.creatures) {
            if (c.x == x && c.y == y) {
                return c;
            }
        }
        return null;
    }

    //how to remove creatures
    public void remove(Creature other) {
        this.creatures.remove(other);
    }

    public void update() {
        List<Creature> toUpdate = new ArrayList<>(this.creatures);
        for (Creature creature : toUpdate) {
            creature.update();
        }
    }

    //methods to get details about Aincrad
    public Tile tile(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return Tile.BOUNDS;
        } else {
            return this.tiles[x][y];
        }
    }

    //Used to dig through cave walls so the floor are connected
    public void dig(int x, int y) {
        if (this.tile(x, y).isDiggable()) {
            this.tiles[x][y] = Tile.FLOOR;
        }
    }

    /*
     * set creature position at random x and y coordinate ground tile, and add
     * creature where there isn't already one, also add new creatures to our
     * list
     *
     */
    public void addAtEmptyLocation(Creature creature) {
        int x, y;

        do {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
        } while (!this.tile(x, y).isGround() || this.creature(x, y) != null);

        creature.x = x;
        creature.y = y;
        this.creatures.add(creature);
    }

    public char glyph(int x, int y) {
        return this.tile(x, y).glyph();
    }

    public Color color(int x, int y) {
        return this.tile(x, y).color();
    }
}
