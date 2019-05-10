package gabsu.osu.rsao;

public class FungusAi extends CreatureAi {

    private CreatureFactory factory;
    private int spreadcount;

    public FungusAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    @Override
    public void onUpdate() {
        if (this.spreadcount < 5 && Math.random() < 0.001) {
            this.spread();
        }
    }

    //spread fungus
    private void spread() {
        int x = this.creature.x + (int) (Math.random() * 11) - 5;
        int y = this.creature.y + (int) (Math.random() * 11) - 5;

        if (this.creature.canEnter(x, y)) {
            Creature child = this.factory.newFungus();
            child.x = x;
            child.y = y;
            this.spreadcount++;
        }
    }
}
