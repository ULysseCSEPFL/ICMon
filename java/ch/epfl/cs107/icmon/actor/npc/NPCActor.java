package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class NPCActor extends ICMonActor implements AreaInteractionVisitor, Interactable {


    private Sprite sprite;

    /**
     * Constructs an NPCActor with the specified area, orientation, position, and sprite name.
     *
     * @param area        The area in which the NPCActor exists.
     * @param orientation The initial orientation of the NPCActor.
     * @param position    The initial position of the NPCActor.
     * @param spriteName  The name of the sprite used by the NPCActor.
     */
    public NPCActor(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position);

        sprite = new RPGSprite(spriteName, 1, 1.3125f, this, new RegionOfInterest(0, 0, 16, 21));
    }

    /**
     * Draws the ICShopAssistant on the canvas.
     *
     * @param canvas The canvas on which to draw the ICShopAssistant.
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /**
     * Returns the current cells occupied by the ICShopAssistant.
     *
     * @return A list containing the current main cell coordinates of the ICShopAssistant.
     */
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Indicates whether the ICShopAssistant takes cell space.
     *
     * @return True, as the ICShopAssistant takes cell space.
     */
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * Indicates whether the ICShopAssistant is interactable on the cell level.
     *
     * @return False, as the ICShopAssistant is not interactable on the cell level.
     */
    public boolean isCellInteractable() {
        return false;
    }

    /**
     * Indicates whether the ICShopAssistant is interactable on the view level.
     *
     * @return True, as the ICShopAssistant is interactable on the view level.
     */
    @Override
    public boolean isViewInteractable() {
        return true;
    }
}
