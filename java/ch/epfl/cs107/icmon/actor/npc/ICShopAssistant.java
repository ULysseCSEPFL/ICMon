package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class ICShopAssistant extends NPCActor{


    /**
     * Constructs an ICShopAssistant with the specified area, orientation, and position.
     *
     * @param area        The area in which the ICShopAssistant exists.
     * @param orientation The orientation of the ICShopAssistant.
     * @param position    The position of the ICShopAssistant.
     */
    public ICShopAssistant(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "actors/assistant");
    }

    /**
     * Accepts interaction with a visitor, forwarding the interaction to an ICMonInteractionVisitor.
     *
     * @param v                 The visitor performing the interaction.
     * @param isCellInteraction A boolean indicating whether the interaction is cell-based.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

}
