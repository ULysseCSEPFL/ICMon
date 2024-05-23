package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.engine.PauseMenu;

// Abstract class that must be implemented by all events involving a pause menu.
public abstract class PauseMenuEvent extends ICMonEvent {


    /**
     * PauseMenuEvent represents an event related to the pause menu in the game.
     * This event is responsible for updating and managing the pause menu during gameplay.
     *
     * @param eventManager The event manager handling this event.
     */
    public PauseMenuEvent(ICMon.ICMonEventManager eventManager) {
        super(eventManager);
    }

    /**
     * Update method for the pause menu event. This method is called periodically to perform
     * any necessary updates related to the pause menu.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        // Implementation of update logic goes here
    }

    /**
     * Get the pause menu associated with this event. Subclasses must implement this method
     * to provide the specific pause menu instance.
     *
     * @return The pause menu associated with this event.
     */
    public abstract PauseMenu getPauseMenu();

}
