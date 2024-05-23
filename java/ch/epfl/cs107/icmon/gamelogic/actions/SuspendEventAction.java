package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

import java.util.List;

public class SuspendEventAction implements Action{

    private List<ICMonEvent> currentEvents;

    /**
     * Constructor for SuspendEventAction.
     *
     * @param currentEvents List of ICMon events to be suspended.
     */
    public SuspendEventAction(List<ICMonEvent> currentEvents){
        this.currentEvents = currentEvents;
    }

    /**
     * Performs the action by suspending each event in the list.
     */
    @Override
    public void perform() {
        for (ICMonEvent icMonEvent : currentEvents){
            icMonEvent.suspend();
        }
    }
}
