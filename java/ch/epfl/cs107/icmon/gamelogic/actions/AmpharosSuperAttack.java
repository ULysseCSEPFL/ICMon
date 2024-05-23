package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AmpharosSuperAttack extends SuperAttackAction{

    private final int AMPHAROS_SUPER_DAMAGE = 6;

    /**
     * Performs a special action for Ampharos during a Pokemon battle.
     *
     * @param attacker The Pokemon initiating the action.
     * @param target   The Pokemon receiving the action.
     * @return True if the action was successfully performed, false otherwise.
     */
    public  boolean doAction(Pokemon attacker, Pokemon target){
        super.doAction(attacker,target);
        target.sufferDamage(AMPHAROS_SUPER_DAMAGE);
        return true;
    }
}
