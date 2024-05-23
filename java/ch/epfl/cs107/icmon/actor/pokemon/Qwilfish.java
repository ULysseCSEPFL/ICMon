package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ICMonFightAction;
import ch.epfl.cs107.icmon.gamelogic.actions.QwilFishSuperAttack;
import ch.epfl.cs107.icmon.gamelogic.actions.RunAwayAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Qwilfish extends Pokemon{

    private final List<ICMonFightAction> actionList = new ArrayList<>();

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Qwilfish(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "qwilfish", 10, 2);
        actionList.add(new RunAwayAction());
        actionList.add(new AttackAction());
        actionList.add(new QwilFishSuperAttack());
    }

    /**
     * Creates a Qwilfish Pokemon with the specified attributes.
     */
    public Qwilfish() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "qwilfish", 10, 2);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new QwilFishSuperAttack());
    }

    /**
     * Retrieves the list of fight actions associated with the Qwilfish Pokemon.
     *
     * @return The list of fight actions associated with the Qwilfish Pokemon.
     */
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}
