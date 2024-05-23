package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * Action representing a run away attempt in ICMonFight.
 */
public class RunAwayAction implements ICMonFightAction {

    /**
     * Gets the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String name() {
        return "Run Away !";
    }

    /**
     * Performs the run away action.
     *
     * @param player The Pokemon initiating the action.
     * @param target The target Pokemon for the action.
     * @return Always returns false for a run away attempt.
     */
    @Override
    public boolean doAction(Pokemon player, Pokemon target) {
        return false;
    }
}
