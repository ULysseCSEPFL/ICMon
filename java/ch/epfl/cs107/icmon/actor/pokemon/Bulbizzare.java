package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Bulbizzare extends Pokemon{


    private final List<ICMonFightAction> actionList = new ArrayList<>();

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Bulbizzare(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "bulbizarre", 20, 1);
        actionList.add(new RunAwayAction());
        actionList.add(new AttackAction());
        actionList.add(new BulbizzareSuperAttack());
    }

    /**
     * Default constructor for Bulbizarre with a predefined location, orientation, and initial statistics.
     */
    public Bulbizzare(){
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "bulbizarre", 20, 1);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new BulbizzareSuperAttack());
    }

    /**
     * Gets the list of actions that Bulbizarre can perform in a fight.
     *
     * @return The list of Bulbizarre's fight actions.
     */
    public List<ICMonFightAction> getActionsList(){
        return actionList;
    }
}
