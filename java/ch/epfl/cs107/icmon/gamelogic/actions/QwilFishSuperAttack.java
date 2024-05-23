package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class QwilFishSuperAttack extends SuperAttackAction{

    private final int QWILFISH_SUPER_HEALTH = 3;

    private final int QWILFISH_SUPER_DAMAGE = 3;

    /**
     * Performs the action of a Qwilfish super attack, healing the attacker by a fixed amount.
     *
     * @param attacker The Pokemon initiating the attack.
     * @param target   The Pokemon being attacked.
     * @return True if the action is successfully performed.
     */
    @Override
    public boolean doAction(Pokemon attacker, Pokemon target) {
        super.doAction(attacker,target);

        if(attacker.getProperties().hp() > attacker.getProperties().maxHp() - QWILFISH_SUPER_HEALTH){
            target.sufferDamage(QWILFISH_SUPER_DAMAGE);
        }
        else attacker.setHp(QWILFISH_SUPER_HEALTH);
        return true;
    }
}
