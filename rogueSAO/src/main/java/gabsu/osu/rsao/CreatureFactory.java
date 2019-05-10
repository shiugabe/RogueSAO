package gabsu.osu.rsao;

import asciiPanel.AsciiPanel;

public class CreatureFactory {
    private Aincrad aincrad;

    public CreatureFactory(Aincrad aincrad) {
        this.aincrad = aincrad;
    }

    public Creature newPlayer() {
        Creature player = new Creature(this.aincrad, '@',
                AsciiPanel.brightWhite);
        this.aincrad.addAtEmptyLocation(player);
        new PlayerAi(player);
        return player;
    }

    public Creature newFungus() {
        Creature fungus = new Creature(this.aincrad, 'f', AsciiPanel.green);
        this.aincrad.addAtEmptyLocation(fungus);
        new FungusAi(fungus, this);
        return fungus;
    }
}
