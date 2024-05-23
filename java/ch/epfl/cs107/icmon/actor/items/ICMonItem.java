package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;


import java.util.Collections;
import java.util.List;

public abstract class ICMonItem extends CollectableAreaEntity {

    private Sprite sprite;    // on le caractérise par un attribut de type Sprite.

    /**
     * Constructs an ICMonItem with the specified area, orientation, position, and sprite name.
     *
     * @param area        The area in which the ICMonItem exists.
     * @param orientation The initial orientation of the ICMonItem.
     * @param position    The initial position of the ICMonItem.
     * @param spriteName  The name of the sprite used by the ICMonItem.
     */
    public ICMonItem(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position);
        sprite = new RPGSprite(spriteName , 1f, 1f, this);
        orientate(Orientation.DOWN);
    }

    /**
     * Retrieves the list of discrete coordinates corresponding to the main cell occupied by the NPC actor.
     *
     * @return The list containing the main cell coordinates of the NPC actor.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Indicates that the NPC actor takes up space in the cells it occupies.
     *
     * @return True, as the NPC actor takes cell space.
     */
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * Indicates that the NPC actor can be interacted with at the cell level.
     *
     * @return True, as the NPC actor is cell-interactable.
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * Indicates that the NPC actor cannot be interacted with in the view.
     *
     * @return False, as the NPC actor is not view-interactable.
     */
    @Override
    public boolean isViewInteractable() {
        return false;
    }


    /**
     * Accepts interaction with a visitor, forwarding the interaction to an ICMonInteractionVisitor.
     *
     * @param v                  The visitor performing the interaction.
     * @param isCellInteraction  A boolean indicating whether the interaction is cell-based.
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }


    /**
     * Draws the sprite of the NPC actor on the given canvas.
     *
     * @param canvas The canvas on which the sprite is drawn.
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}




