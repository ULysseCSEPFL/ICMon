package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class UnRegisterEventAction implements Action{
    private final ICMonEvent event;
    private final ICMon.ICMonEventManager eventManager;

    /**
     * Constructor for UnRegisterEventAction.
     *
     * @param event       The ICMon event to be unregistered.
     * @param eventManager The event manager from which the event will be unregistered.
     */
    public UnRegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager eventManager){
        this.event = event;
        this.eventManager = eventManager;
    }

    /**
     * Performs the action by unregistering the event from the event manager.
     */
    @Override
    public void perform() {
        eventManager.unregisterEvent(event);
    }
}
