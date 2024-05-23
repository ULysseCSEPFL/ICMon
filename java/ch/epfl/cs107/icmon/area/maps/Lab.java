package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Lab extends ICMonArea {


    /**
     * Gets the title of the lab area.
     *
     * @return The title of the lab area.
     */
    @Override
    public String getTitle() {
        return "lab";
    }

    /**
     * Creates the lab area, registering actors such as the background, foreground, and doors.
     */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this, Orientation.UP, "town", new DiscreteCoordinates(15, 23), new DiscreteCoordinates(6, 1), new DiscreteCoordinates(7, 1)));
    }

    /**
     * Gets the player's spawn position in the lab area.
     *
     * @return The player's spawn position in the lab area.
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(6, 2);
    }
}
