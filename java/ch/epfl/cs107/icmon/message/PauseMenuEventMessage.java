package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RequestPauseAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RequestResumeAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;

/**
 * This interface must be implemented by all messages whose events involve a pause menu.
 * Classes implementing this interface must have a mandatory getter that returns an instance of type PauseMenuEvent.
 */
public interface PauseMenuEventMessage {

    /**
     * Gets the PauseMenuEvent associated with this process.
     *
     * @return The PauseMenuEvent.
     */
    PauseMenuEvent getEvent();

    /**
     * Default method to process a PauseMenuEvent in the context of ICMon.
     *
     * @param icMon           The ICMon instance.
     * @param pauseMenuEvent  The PauseMenuEvent to process.
     */
    default void process(ICMon icMon, PauseMenuEvent pauseMenuEvent){
        pauseMenuEvent.onStart(new RequestPauseAction(icMon));
        pauseMenuEvent.onComplete(new RequestResumeAction(icMon));
    }


}
