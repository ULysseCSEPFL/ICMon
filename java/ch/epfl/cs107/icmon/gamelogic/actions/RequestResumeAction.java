package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class RequestResumeAction implements Action {

    private ICMon icmon;

    /**
     * Constructor for RequestResumeAction.
     *
     * @param icMon The ICMon instance where the resume is requested.
     */
    public RequestResumeAction(ICMon icMon){
        this.icmon = icMon;
    }

    /**
     * Performs the action by requesting a resume in the ICMon game.
     */
    @Override
    public void perform() {
        icmon.requestResume();
    }
}
