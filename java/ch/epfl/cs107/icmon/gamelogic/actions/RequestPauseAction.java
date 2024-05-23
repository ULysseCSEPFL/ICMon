package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class RequestPauseAction implements Action{

    private ICMon icmon;

    /**
     * Constructor for RequestPauseAction.
     *
     * @param icMon The ICMon instance where the pause is requested.
     */
    public RequestPauseAction(ICMon icMon){
        this.icmon = icMon;
    }

    /**
     * Performs the action by requesting a pause in the ICMon game.
     */
    @Override
    public void perform() {
        icmon.requestPause();
    }


}
