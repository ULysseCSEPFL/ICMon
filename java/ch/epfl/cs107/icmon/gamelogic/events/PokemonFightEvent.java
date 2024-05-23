package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.window.Canvas;

public class PokemonFightEvent extends PauseMenuEvent{

    private final ICMonFight fight;

    private final ICMonPlayer player;

    private boolean isAlreadyStarted = false;

    /**
     * PokemonFightEvent represents an event related to a Pokemon fight in the game.
     * This event is responsible for managing the progression and completion of a Pokemon fight.
     *
     * @param eventManager The event manager handling this event.
     * @param fight        The Pokemon fight associated with this event.
     * @param player       The player involved in the fight.
     */
    public PokemonFightEvent(ICMon.ICMonEventManager eventManager, ICMonFight fight, ICMonPlayer player) {
        super(eventManager);
        this.fight = fight;
        this.player = player;

    }

    /**
     * Update method for the Pokemon fight event. This method is called periodically to perform
     * any necessary updates related to the Pokemon fight, such as starting or completing the fight.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        if (isStarted() && !isAlreadyStarted){
            fight.setIsRunning(true);
            isAlreadyStarted = true;
        }
        if (!fight.getIsRunning()){
            complete();
            player.setIsInFight(false);
        }
    }

    /**
     * Get the pause menu associated with this event. Subclasses must implement this method
     * to provide the specific pause menu instance.
     *
     * @return The pause menu associated with this event, which is the Pokemon fight.
     */
    @Override
    public PauseMenu getPauseMenu(){ // peut-etre le PB vienty d'ici, retourner getFight ??
        return fight;
    }

}
