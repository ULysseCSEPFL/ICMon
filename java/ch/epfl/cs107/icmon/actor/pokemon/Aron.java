package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Aron extends Pokemon{

    private final List<ICMonFightAction> actionList = new ArrayList<>();

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Aron(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "aron", 45, 0);
        actionList.add(new RunAwayAction());
        actionList.add(new AttackAction());
        actionList.add(new AronSuperAttack());
    }

    /**
     * Default constructor for Aron with a predefined location, orientation, and initial statistics.
     */
    public Aron() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "aron", 45, 0);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new AronSuperAttack());
    }

    /**
     * Gets the list of actions that Aron can perform in a fight.
     *
     * @return The list of Aron's fight actions.
     */
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}
