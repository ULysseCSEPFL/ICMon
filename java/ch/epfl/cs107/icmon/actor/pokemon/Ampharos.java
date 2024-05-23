package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Ampharos extends Pokemon{


    private final List<ICMonFightAction> actionList = new ArrayList<>();


    /**
     * Constructs an Ampharos with specified area, orientation, and position.
     *
     * @param area        The area in which Ampharos is located.
     * @param orientation The initial orientation of Ampharos.
     * @param position    The initial position of Ampharos in the area.
     */
    public Ampharos(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "ampharos", 10, 4);
        actionList.add(new RunAwayAction());
        actionList.add(new AttackAction());
        actionList.add(new AmpharosSuperAttack());
    }

    /**
     * Default constructor for Ampharos with a predefined location and orientation.
     */
    public Ampharos() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "ampharos", 10, 4);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new AmpharosSuperAttack());
    }

    /**
     * Gets the list of actions that Ampharos can perform in a fight.
     *
     * @return The list of Ampharos's fight actions.
     */
    @Override
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}

