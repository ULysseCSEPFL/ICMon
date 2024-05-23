package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class LeaveAreaAction implements Action{

    private ICMonActor icMonActor;

    private ICMonArea currentArea;

    /**
     * Constructor for LeaveAreaAction.
     *
     * @param actor      The ICMonActor that will leave the area.
     * @param currentArea The current ICMonArea from which the actor will leave.
     */
    public LeaveAreaAction(ICMonActor actor, ICMonArea currentArea){
        this.icMonActor = actor;
        this.currentArea = currentArea;
    }

    /**
     * Performs the action, causing the actor to leave the current area.
     */
    public void perform() {
        currentArea.unregisterActor(icMonActor);
    }

}
