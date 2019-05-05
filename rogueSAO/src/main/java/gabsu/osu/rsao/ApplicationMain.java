package gabsu.osu.rsao;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;

public class ApplicationMain extends JFrame {
    private static final long serialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;

    public ApplicationMain() {
        super();
        this.terminal = new AsciiPanel();
        this.terminal.write("Gabe's rogue-like game", 2, 2);
        this.add(this.terminal);
        this.pack();
    }

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
