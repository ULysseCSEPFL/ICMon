package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Azumarill extends Pokemon{

    private final List<ICMonFightAction> actionList = new ArrayList<>();

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Azumarill(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "azumarill", 15, 1);
        actionList.add(new RunAwayAction());
        actionList.add(new AttackAction());
        actionList.add(new AzumarillSuperAttack());
    }

    /**
     * Default constructor for Azumarill with a predefined location, orientation, and initial statistics.
     */
    public Azumarill() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "azumarill", 15, 1);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new AzumarillSuperAttack());
    }

    /**
     * Gets the list of actions that Azumarill can perform in a fight.
     *
     * @return The list of Azumarill's fight actions.
     */
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}
