package gabsu.osu.rsao;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public enum Tile {
    FLOOR((char) 250, AsciiPanel.white), WALL((char) 178,
            AsciiPanel.yellow), WATER((char) 178,
                    AsciiPanel.blue), BOUNDS('x', AsciiPanel.red);

    //initialize private members
    private char glyph;
    private Color color;

    //constructors
    public char glyph() {
        return this.glyph;
    }

    public Color color() {
        return this.color;
    }

    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

}
