package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AronSuperAttack extends SuperAttackAction{

    private final int ARON_SUPER_DAMAGE = 3;

    /**
     * Performs a special action for Aron during a Pokemon battle.
     *
     * @param attacker The Pokemon initiating the action.
     * @param target   The Pokemon receiving the action.
     * @return True if the action was successfully performed, false otherwise.
     */
    public boolean doAction(Pokemon attacker, Pokemon target) {
        super.doAction(attacker,target);
        target.sufferDamage(ARON_SUPER_DAMAGE);
        return true;
    }
}
