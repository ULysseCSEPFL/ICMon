package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;

public class EndOfTheGameEvent extends ICMonEvent{

    private final ICMonPlayer player;


    /**
     * Constructor for EndOfTheGameEvent.
     *
     * @param player       The player associated with the event.
     * @param eventManager The event manager handling this event.
     */
    public EndOfTheGameEvent(ICMonPlayer player, ICMon.ICMonEventManager eventManager) {
        super(eventManager);
        this.player = player;

    }

    /**
     * Updates the event based on the time elapsed since the last update.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
    }

    /**
     * Handles interaction with an ICShopAssistant during the event.
     *
     * @param icShopAssistant  The ICShopAssistant to interact with.
     * @param isCellInteraction  A flag indicating if the interaction is cell-based.
     */
    public void interactWith(ICShopAssistant icShopAssistant, boolean isCellInteraction){
        if (player.wantsViewInteraction()){
            player.openDialog("end_of_game_event_interaction_with_icshopassistant");
        }
    }
}
