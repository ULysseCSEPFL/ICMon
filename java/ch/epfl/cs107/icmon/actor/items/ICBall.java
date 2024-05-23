package ch.epfl.cs107.icmon.actor.items;


import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;


public class ICBall extends ICMonItem {

    /**
     * Constructor for the ICBall class.
     *
     * @param area          The area to which the ball belongs.
     * @param orientation   The initial orientation of the ball.
     * @param position      The initial position of the ball in the area.
     * @param spriteName    The name of the sprite associated with the ball.
     */
    public ICBall(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
    }

    /**
     * Accepts interaction with a visitor, forwarding the interaction to an ICMonInteractionVisitor.
     *
     * @param v                  The visitor performing the interaction.
     * @param isCellInteraction  A boolean indicating whether the interaction is cell-based.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**
     * Indicates whether the ball can be interacted with in the view.
     *
     * @return True, as the ball is view-interactable.
     */
    public boolean isViewInteractable() {
        return true;
    }

}
