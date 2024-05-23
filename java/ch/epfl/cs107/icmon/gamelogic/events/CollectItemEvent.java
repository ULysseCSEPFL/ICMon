package ch.epfl.cs107.icmon.gamelogic.events;


import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Canvas;


public class CollectItemEvent extends ICMonEvent  {


    private final ICMonItem itemToCollect;
    private final ICMonPlayer icMonPlayer;

    /**
     * Constructor for CollectItemEvent.
     *
     * @param icMonPlayer  The player associated with the event.
     * @param eventManager The event manager handling this event.
     * @param ItemToCollect The item to be collected.
     * @param actions      Actions associated with the event.
     */
    public CollectItemEvent(ICMonPlayer icMonPlayer, ICMon.ICMonEventManager eventManager, ICMonItem ItemToCollect, Action... actions){
        super(eventManager);
        this.icMonPlayer = icMonPlayer;
        this.itemToCollect = ItemToCollect;
    }


    /**
     * Updates the event based on the state of the item to be collected.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        if(itemToCollect.isCollected()){
            complete();
        }
    }

    /**
     * Handles interaction with an ICShopAssistant during the event.
     *
     * @param assistant          The ICShopAssistant to interact with.
     * @param isCellInteraction  A flag indicating if the interaction is cell-based.
     */
    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction ){
        if(icMonPlayer.wantsViewInteraction() && !isCompleted()) {
            System.out.println("This is an interaction between the ICshopAssistant and the player based on events !");
            icMonPlayer.openDialog("collect_item_event_interaction_with_icshopassistant");
        }

    }
}




