package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action {
    private final ICMonEvent event;
    private final ICMon.ICMonEventManager eventManager;

    /**
     * Constructor for RegisterEventAction.
     *
     * @param event        The event to be registered.
     * @param eventManager The event manager responsible for handling events.
     */
    public RegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager eventManager) {
        this.event = event;
        this.eventManager = eventManager;
    }

    /**
     * Performs the action by registering the associated event with the event manager.
     */
    @Override
    public void perform() {
        eventManager.registerEvent(event);
    }
}
