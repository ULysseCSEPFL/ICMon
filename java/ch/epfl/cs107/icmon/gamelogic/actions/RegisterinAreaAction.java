package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterinAreaAction implements Action{

    private Area area ;
    private Actor actor ;

    /**
     * Constructor for RegisterinAreaAction.
     *
     * @param area  The area where the actor will be registered.
     * @param actor The actor to be registered in the area.
     */
    public RegisterinAreaAction(Area area, Actor actor){
        this.area = area;
        this.actor = actor;
    }

    /**
     * Performs the action by registering the actor in the specified area.
     */
    public void perform() { area.registerActor(actor); }

}
