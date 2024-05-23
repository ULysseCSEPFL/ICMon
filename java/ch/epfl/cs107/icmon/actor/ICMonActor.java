package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;


public abstract class ICMonActor extends MovableAreaEntity {


    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICMonActor(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }


    /**
     * Returns a list containing the main cell coordinates of the door.
     *
     * @return A list containing the main cell coordinates of the door.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Indicates whether the door occupies space in the cell grid.
     *
     * @return Always returns false, as a door does not occupy cell space.
     */
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    /**
     * Indicates whether the door is interactable at the cell level.
     *
     * @return Always returns true, as the door is interactable at the cell level.
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * Indicates whether the door is interactable at the view level.
     *
     * @return Always returns false, as the door is not interactable at the view level.
     */
    @Override
    public boolean isViewInteractable() {
        return false;
    }

    /**
     * Draws the door on the given canvas.
     *
     * @param canvas The canvas on which the door is drawn.
     */
    @Override
    public void draw(Canvas canvas) {
    }

    /**
     * Enters the specified area and sets the actor's position.
     *
     * @param area     The area to enter.
     * @param position The position in the area.
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * Leaves the current area.
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }


}

