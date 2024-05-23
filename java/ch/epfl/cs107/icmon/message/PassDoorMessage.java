package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;

public class PassDoorMessage extends GamePlayMessage{


    private Door door;

    private ICMon.ICMonGameState gameState;

    /**
     * Message to pass through a door and switch the game state's area.
     *
     * @param door      The door to pass through.
     * @param gameState The game state to switch the area.
     */
    public PassDoorMessage(Door door, ICMon.ICMonGameState gameState) {
        this.door = door;
        this.gameState = gameState;
    }

    /**
     * Processes the message by switching the game state's area through the specified door.
     */
    @Override
    public void process() {
        gameState.switchArea(door);
    }




}
