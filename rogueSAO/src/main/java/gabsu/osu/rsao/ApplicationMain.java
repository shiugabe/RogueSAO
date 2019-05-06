package gabsu.osu.rsao;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;
import gabsu.osu.rsao.screens.Screen;
import gabsu.osu.rsao.screens.StartScreen;

public class ApplicationMain extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;
    private Screen screen;

    public ApplicationMain() {
        super();
        this.terminal = new AsciiPanel();
        this.terminal.write("Gabe's rogue-like game", 1, 1);
        this.add(this.terminal);
        this.pack();
        this.screen = new StartScreen();
        this.addKeyListener(this);
        this.repaint();
    }

    @Override
    public void repaint() {
        this.terminal.clear();
        this.screen.displayOutput(this.terminal);
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.screen = this.screen.respondToUserInput(e);
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
