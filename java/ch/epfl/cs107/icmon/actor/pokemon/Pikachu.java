package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Pikachu extends Pokemon{

    private final List<ICMonFightAction> actionList = new ArrayList<>();

    /**
     * Constructor for Pikachu with a specified area, orientation, and position.
     *
     * @param area        The area where Pikachu is located.
     * @param orientation The initial orientation of Pikachu.
     * @param position    The initial position of Pikachu.
     */
    public Pikachu(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "pikachu", 10, 1);
        actionList.add(new RunAwayAction());
        actionList.add(new AttackAction());
        actionList.add(new PikachuSuperAttack());
    }

    /**
     * Default constructor for Pikachu with predefined values for area, orientation, and position.
     */
    public Pikachu() {
        super(new Town(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "pikachu", 10, 1);
        actionList.add(new AttackAction());
        actionList.add(new RunAwayAction());
        actionList.add(new PikachuSuperAttack());
    }

    /**
     * Gets the list of actions that Pikachu can perform in a fight.
     *
     * @return The list of Pikachu's fight actions.
     */
    public List<ICMonFightAction> getActionsList() {
        return actionList;
    }
}
