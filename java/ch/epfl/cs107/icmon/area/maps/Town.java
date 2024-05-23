package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;


public class Town extends ICMonArea {


    /**
     * Gets the player's spawn position in the town area.
     *
     * @return The player's spawn position in the town area.
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 5);
    }

    /**
     * Creates the town area, registering actors such as the background, foreground, shop assistant, and doors.
     */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICShopAssistant shopAssistant = new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(8, 8));
        registerActor(shopAssistant);
        registerActor(new Door(this, Orientation.UP, "lab", new DiscreteCoordinates(6, 2), new DiscreteCoordinates(15, 24)));
        registerActor(new Door(this, Orientation.UP, "arena", new DiscreteCoordinates(4, 2), new DiscreteCoordinates(20, 16)));
    }

    /**
     * Gets the title of the town area.
     *
     * @return The title of the town area.
     */
    @Override
    public String getTitle() {
        return "town";
    }

}




