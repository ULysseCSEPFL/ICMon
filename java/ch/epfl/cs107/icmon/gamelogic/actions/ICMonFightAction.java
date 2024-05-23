package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public interface ICMonFightAction {

    /**
     * Gets the name of the fight action.
     *
     * @return The name of the fight action.
     */
    String name();

    /**
     * Performs the fight action.
     *
     * @param attacker The Pokemon initiating the action.
     * @param target   The Pokemon receiving the action.
     */
    boolean doAction(Pokemon attacker, Pokemon target);

}
