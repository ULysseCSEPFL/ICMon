package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AttackAction implements ICMonFightAction {

    /**
     * Gets the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String name() {
        return "Attack Action";
    }

    /**
     * Performs the attack action, causing the attacker to inflict damage on the target.
     *
     * @param attacker The Pokemon initiating the action.
     * @param target   The Pokemon receiving the action.
     * @return True if the action was successfully performed, false otherwise.
     */
    @Override
    public boolean doAction(Pokemon attacker, Pokemon target) {
        target.sufferDamage(attacker.getProperties().damage());
        return true;
    }
}
