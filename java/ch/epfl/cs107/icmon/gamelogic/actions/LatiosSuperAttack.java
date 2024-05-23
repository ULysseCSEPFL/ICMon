package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class LatiosSuperAttack extends SuperAttackAction {

    private final int LATIOS_SUPER_DAMAGE = 8;

    /**
     * Overrides the default action to perform a special action for the Latios Pokémon.
     *
     * @param attacker The Pokémon initiating the action.
     * @param target   The Pokémon being targeted by the action.
     * @return True if the action was successfully executed, false otherwise.
     */
    @Override
    public boolean doAction(Pokemon attacker, Pokemon target) {
        super.doAction(attacker,target);
        target.sufferDamage(attacker.getProperties().damage()+LATIOS_SUPER_DAMAGE);
        return true;
    }
}
