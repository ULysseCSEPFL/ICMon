package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public abstract class SuperAttackAction implements ICMonFightAction {


    private final int MALUS_SUPER_ATTACK = 1;

    /**
     * Returns the name of the action.
     *
     * @return The name of the action.
     */
    @Override
    public String name() {
        return "SUPER ATTACK";
    }

    /**
     * Performs the super attack action, applying damage malus to the attacker.
     *
     * @param attacker The Pokemon performing the super attack.
     * @param target   The target Pokemon.
     * @return True, indicating the action was performed.
     */
    @Override
    public  boolean doAction(Pokemon attacker, Pokemon target){
        attacker.sufferDamage(MALUS_SUPER_ATTACK);
        return true;
    }
}
