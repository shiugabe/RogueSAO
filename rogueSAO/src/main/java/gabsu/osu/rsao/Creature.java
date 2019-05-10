package gabsu.osu.rsao;

import java.awt.Color;

public class Creature {
    //initialize private member
    private Aincrad aincrad;
    private Color color;
    private char glyph;
    private CreatureAi ai;
    //x and y coordinates
    public int x, y;

    public char glyph() {
        return this.glyph;
    }

    public Color color() {
        return this.color;
    }

    public Creature(Aincrad aincrad, char glyph, Color color) {
        this.aincrad = aincrad;
        this.color = color;
        this.glyph = glyph;
    }

    //setter
    public void setCreatureAi(CreatureAi ai) {
        this.ai = ai;
    }

    public void update() {
        this.ai.onUpdate();
    }

    //checks if monster can be placed at specific location
    public boolean canEnter(int x, int y) {
        return this.aincrad.tile(x, y).isGround()
                && this.aincrad.creature(x, y) == null;
    }

    //how the creature moves/attack
    public void moveBy(int mx, int my) {
        Creature other = this.aincrad.creature(this.x + mx, this.y + my);

        if (other == null) {
            this.ai.onEnter(this.x + mx, this.y + my,
                    this.aincrad.tile(this.x + mx, this.y + my));
        } else {
            this.attack(other);
        }
    }

    public void attack(Creature other) {
        this.aincrad.remove(other);
    }

    public void dig(int wx, int wy) {
        this.aincrad.dig(wx, wy);
    }

}
