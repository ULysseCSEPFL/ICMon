package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;


public class StartEventAction implements Action {
    private ICMonEvent eventToStart;

    /**
     * Constructor for StartEventAction.
     *
     * @param eventToStart The ICMonEvent to start.
     */
    public StartEventAction(ICMonEvent eventToStart) {
        this.eventToStart = eventToStart;
    }

    /**
     * Performs the action, starting the associated ICMonEvent.
     */
    @Override
    public void perform() {
        if (eventToStart != null) {
            eventToStart.start();
        }
    }
}

