package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public abstract class ICMonFightableActor extends ICMonActor {
    /**
     * Creates a fightable actor in the specified area with the given orientation and position.
     *
     * @param area        The area in which the actor is placed.
     * @param orientation The initial orientation of the actor.
     * @param position    The initial position of the actor.
     */
    public ICMonFightableActor(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    /**
     * Accepts an interaction visitor for area interactions.
     *
     * @param v                The interaction visitor.
     * @param isCellInteraction A boolean indicating if the interaction is at the cell level.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

}
