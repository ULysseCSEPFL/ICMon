package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;

import java.util.List;

public class SuspendWithEventMessage extends GamePlayMessage implements PauseMenuEventMessage {
    private final PokemonFightEvent fightEvent;

    /**
     * Creates a SuspendWithEventMessage with the specified fight event.
     *
     * @param fightEvent The PokemonFightEvent to suspend.
     */
    public SuspendWithEventMessage(PokemonFightEvent fightEvent){
        this.fightEvent = fightEvent;
    }

    /**
     * Processes the message.
     */
    @Override
    public void process() {

    }

    /**
     * Processes the message with additional event list information.
     * Overloading the process method to grant access to the necessary data when used in ICMon.
     * This process method is specific to the SuspendWitheventMessage.
     *
     * @param eventList The list of ICMon events.
     */
    public void process(List<ICMonEvent> eventList){
        fightEvent.onStart(new SuspendEventAction(eventList));
        fightEvent.onComplete(new ResumeEventAction(eventList));
        fightEvent.start();

    }

    /**
     * Gets the PauseMenuEvent associated with this message.
     *
     * @return The PauseMenuEvent.
     */
    @Override
    public PauseMenuEvent getEvent(){
        return fightEvent;
    }


}
