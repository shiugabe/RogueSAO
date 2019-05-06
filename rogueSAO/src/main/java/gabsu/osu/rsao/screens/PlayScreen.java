package gabsu.osu.rsao.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import gabsu.osu.rsao.Aincrad;
import gabsu.osu.rsao.WorldBuilder;

public class PlayScreen implements Screen {

    //initialize private members
    private Aincrad aincrad;
    private int centerX;
    private int centerY;
    private int screenWidth;
    private int screenHeight;

    //public constructor
    public PlayScreen() {
        this.screenWidth = 80;
        this.screenHeight = 21;
        this.createWorld();
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
        return Math.max(0, Math.min(this.centerX - this.screenWidth / 2,
                this.aincrad.width() - this.screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(this.centerY - this.screenHeight / 2,
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
    }

    private void scrollBy(int mx, int my) {
        this.centerX = Math.max(0,
                Math.min(this.centerX + mx, this.aincrad.width() - 1));
        this.centerY = Math.max(0,
                Math.min(this.centerY + my, this.aincrad.height() - 1));
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        int left = this.getScrollX();
        int top = this.getScrollY();
        this.displayTiles(terminal, left, top);
        //show where character is
        terminal.write('x', this.centerX - left, this.centerY - top);
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
                this.scrollBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                this.scrollBy(1, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                this.scrollBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                this.scrollBy(0, 1);
                break;
            case KeyEvent.VK_Y:
                this.scrollBy(-1, -1);
                break;
            case KeyEvent.VK_U:
                this.scrollBy(1, -1);
                break;
            case KeyEvent.VK_B:
                this.scrollBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                this.scrollBy(1, 1);
                break;
        }

        return this;
    }

}
