package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

import java.util.List;

public class ResumeEventAction implements Action {

    private List<ICMonEvent> suspendedEvents;

    /**
     * Constructor for ResumeEventAction.
     *
     * @param suspendedEvents The list of suspended ICMon events to be resumed.
     */
    public ResumeEventAction(List<ICMonEvent> suspendedEvents){
        this.suspendedEvents = suspendedEvents;
    }

    /**
     * Performs the action by resuming each event in the list.
     */
    @Override
    public void perform() {
        for (ICMonEvent icMonEvent : suspendedEvents){
            icMonEvent.suspend();
        }
    }
}
