package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.LatiosSuperAttack;
import ch.epfl.cs107.icmon.gamelogic.actions.RunAwayAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Latios extends Pokemon{


    private List<ICMonFightAction> actionList = new ArrayList<>();

    /**
     * Constructor for Latios with a specified area, orientation, and position.
     *
     * @param area        The area where Latios is located.
     * @param orientation The initial orientation of Latios.
     * @param position    The initial position of Latios.
     */
    public Latios(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "latios", 8, 5);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new LatiosSuperAttack());
    }

    /**
     * Default constructor for Latios with predefined values for area, orientation, and position.
     */
    public Latios() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "latios", 8, 5);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new LatiosSuperAttack());
    }

    /**
     * Gets the list of actions that Latios can perform in a fight.
     *
     * @return The list of Latios's fight actions.
     */
    @Override
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}
