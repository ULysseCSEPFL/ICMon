package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class BulbizzareSuperAttack extends SuperAttackAction{

    private final int BULBIZZARE_SUPER_DAMAGE = 3;

    /**
     * Performs the Bulbizarre super attack action, inflicting additional damage to the target.
     *
     * @param attacker The Bulbizarre Pokemon initiating the action.
     * @param target   The Pokemon receiving the action.
     * @return True if the action was successfully performed, false otherwise.
     */
    public boolean doAction(Pokemon attacker, Pokemon target) {
        super.doAction(attacker,target);
        target.sufferDamage(attacker.getProperties().damage()+BULBIZZARE_SUPER_DAMAGE);
        return true;
    }
}
