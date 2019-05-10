package gabsu.osu.rsao;

public class PlayerAi extends CreatureAi {

    public PlayerAi(Creature creature) {
        super(creature);
    }

    /*
     * overrides onEnter from CreatureAi
     * 
     * @see gabsu.osu.rsao.CreatureAi#onEnter(int, int, gabsu.osu.rsao.Tile) dig
     * through walls and walk on ground tiles
     *
     */
    @Override
    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            this.creature.x = x;
            this.creature.y = y;
        } else if (tile.isDiggable()) {
            this.creature.dig(x, y);
        }
    }
}
