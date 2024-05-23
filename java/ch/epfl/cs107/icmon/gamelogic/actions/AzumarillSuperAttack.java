package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AzumarillSuperAttack extends SuperAttackAction{
    private final int AZUMARILL_SUPER_HEALTH = 2;

    private final int AZUMARILL_SUPER_DAMAGE = 2;


    /**
     * Performs the Azumarill super attack action, healing Azumarill's health after successfully attacking the target.
     *
     * @param attacker The Azumarill Pokemon initiating the action.
     * @param target   The Pokemon receiving the action.
     * @return True if the action was successfully performed, false otherwise.
     */
    @Override
    public boolean doAction(Pokemon attacker, Pokemon target) {
        super.doAction(attacker, target);

        if (attacker.getProperties().hp() > attacker.getProperties().maxHp()-AZUMARILL_SUPER_HEALTH){
            target.sufferDamage(AZUMARILL_SUPER_DAMAGE);
        }
        else attacker.setHp(AZUMARILL_SUPER_HEALTH);
        return true;
    }
}
