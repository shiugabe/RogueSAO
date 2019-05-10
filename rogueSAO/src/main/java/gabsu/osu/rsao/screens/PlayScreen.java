package gabsu.osu.rsao.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import gabsu.osu.rsao.Aincrad;
import gabsu.osu.rsao.Creature;
import gabsu.osu.rsao.CreatureFactory;
import gabsu.osu.rsao.WorldBuilder;

public class PlayScreen implements Screen {

    //initialize private members
    private Aincrad aincrad;

    private int screenWidth;
    private int screenHeight;
    private Creature player;

    //public constructor
    public PlayScreen() {
        this.screenWidth = 80;
        this.screenHeight = 21;
        this.createWorld();
        CreatureFactory creatureFactory = new CreatureFactory(this.aincrad);
        this.player = creatureFactory.newPlayer();

        for (int i = 0; i < 8; i++) {
            creatureFactory.newFungus();
        }
    }

    private void createWorld() {
        this.aincrad = new WorldBuilder(90, 31).makeCaves().build();
    }

    /*
     * methods tell us how to we can scroll along the X/Y axis, to prevent
     * scrolling too far to top/bottom
     *
     */
    public int getScrollX() {
        return Math.max(0, Math.min(this.player.x - this.screenWidth / 2,
                this.aincrad.width() - this.screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(this.player.y - this.screenHeight / 2,
                this.aincrad.height() - this.screenHeight));
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < this.screenWidth; x++) {
            for (int y = 0; y < this.screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;

                terminal.write(this.aincrad.glyph(wx, wy), x, y,
                        this.aincrad.color(wx, wy));
            }
        }

        for (Creature c : this.aincrad.creatures) {
            if ((c.x > left && c.x < left + this.screenWidth)
                    && (c.y >= top && c.y < top + this.screenHeight)) {
                terminal.write(c.glyph(), c.x - left, c.y - top, c.color());
            }
        }
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        int left = this.getScrollX();
        int top = this.getScrollY();
        this.displayTiles(terminal, left, top);
        //show where character is
        terminal.write(this.player.glyph(), this.player.x - left,
                this.player.y - top, this.player.color());
        terminal.writeCenter("-- press [escape] to lose or [enter] to win --",
                22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseScreen();
            case KeyEvent.VK_ENTER:
                return new WinScreen();
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
                this.player.moveBy(-1, 0);
                this.aincrad.update();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                this.player.moveBy(1, 0);
                this.aincrad.update();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                this.player.moveBy(0, -1);
                this.aincrad.update();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                this.player.moveBy(0, 1);
                this.aincrad.update();
                break;
            case KeyEvent.VK_Y:
                this.player.moveBy(-1, -1);
                this.aincrad.update();
                break;
            case KeyEvent.VK_U:
                this.player.moveBy(1, -1);
                break;
            case KeyEvent.VK_B:
                this.player.moveBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                this.player.moveBy(1, 1);
                break;
        }

        return this;
    }

}
