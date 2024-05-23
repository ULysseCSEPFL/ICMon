package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.NidoqueenSuperAttack;
import ch.epfl.cs107.icmon.gamelogic.actions.RunAwayAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Nidoqueen extends Pokemon {


    private List<ICMonFightAction> actionList = new ArrayList<>();


    /**
     * Constructor for Nidoqueen with a specified area, orientation, and position.
     *
     * @param area        The area where Nidoqueen is located.
     * @param orientation The initial orientation of Nidoqueen.
     * @param position    The initial position of Nidoqueen.
     */
    public Nidoqueen(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "nidoqueen", 15, 2);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new NidoqueenSuperAttack());
    }

    /**
     * Default constructor for Nidoqueen with predefined values for area, orientation, and position.
     */
    public Nidoqueen() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "nidoqueen", 15, 2);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new NidoqueenSuperAttack());
    }

    /**
     * Gets the list of actions that Nidoqueen can perform in a fight.
     *
     * @return The list of Nidoqueen's fight actions.
     */
    @Override
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}
