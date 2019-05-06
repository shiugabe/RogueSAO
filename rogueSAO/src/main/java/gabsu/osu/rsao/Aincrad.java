package gabsu.osu.rsao;

import java.awt.Color;

public class Aincrad {

    //initialize private members
    private Tile[][] tiles;
    private int width;
    private int height;

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
    }

    //methods to get details about Aincrad
    public Tile tile(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return Tile.BOUNDS;
        } else {
            return this.tiles[x][y];
        }
    }

    public char glyph(int x, int y) {
        return this.tile(x, y).glyph();
    }

    public Color color(int x, int y) {
        return this.tile(x, y).color();
    }
}
