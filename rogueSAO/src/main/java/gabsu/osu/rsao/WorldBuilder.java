package gabsu.osu.rsao;

public class WorldBuilder {

    //initialize private members
    private int width;
    private int height;
    private Tile[][] tiles;

    //public constructor
    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    //randomize tiles
    private WorldBuilder randomizeTiles() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.tiles[x][y] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
            }
        }
        return this;
    }

    //repeatedly smooth the randomized tiles
    private WorldBuilder smooth(int times) {
        Tile[][] tiles2 = new Tile[this.width][this.height];
        for (int time = 0; time < times; time++) {

            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    int floors = 0;
                    int rocks = 0;

                    for (int ox = -1; ox < 2; ox++) {
                        for (int oy = -1; oy < 2; oy++) {
                            if (x + ox < 0 || x + ox >= this.width || y + oy < 0
                                    || y + oy >= this.height) {
                                continue;
                            }

                            if (this.tiles[x + ox][y + oy] == Tile.FLOOR) {
                                floors++;
                            } else {
                                rocks++;
                            }
                        }
                    }
                    tiles2[x][y] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
                }
            }
            this.tiles = tiles2;
        }
        return this;
    }

    public WorldBuilder makeCaves() {
        return this.randomizeTiles().smooth(8);
    }

    public Aincrad build() {
        return new Aincrad(this.tiles);
    }
}
