package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class NidoqueenSuperAttack extends SuperAttackAction{


    private final int NIDOQUUEN_SUPER_DAMAGE = 4;

    /**
     * Performs the action of a Nidoqueen super attack, dealing additional damage to the target.
     *
     * @param attacker The Pokemon initiating the attack.
     * @param target   The Pokemon being attacked.
     * @return True if the action is successfully performed.
     */
    @Override
    public boolean doAction(Pokemon attacker, Pokemon target) {
        super.doAction(attacker,target);
        target.sufferDamage(attacker.getProperties().damage()+NIDOQUUEN_SUPER_DAMAGE);
        return true;
    }
}
