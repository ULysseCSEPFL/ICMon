package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.area.maps.Lab;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ch.epfl.cs107.play.math.Orientation.UP;

public class Door extends AreaEntity {

    private final String areaDestination;

    private List<DiscreteCoordinates> occupationCells = new ArrayList<>();

    /**
     * Creates a Door with the specified attributes, including a main position.
     *
     * @param area                  The area the door belongs to.
     * @param orientation           The orientation of the door.
     * @param destinationArea       The name of the destination area.
     * @param coordinatesDestination The coordinates of the destination area.
     * @param mainPosition          The main position of the door.
     */
    public Door(Area area, Orientation orientation, String destinationArea, DiscreteCoordinates coordinatesDestination, DiscreteCoordinates mainPosition) {
        super(area, orientation, coordinatesDestination);
        occupationCells.add(mainPosition);
        this.areaDestination = destinationArea;
    }

    /**
     * Creates a Door with the specified attributes, including multiple positions.
     *
     * @param area                  The area the door belongs to.
     * @param orientation           The orientation of the door.
     * @param destinationArea       The name of the destination area.
     * @param coordinatesDestination The coordinates of the destination area.
     * @param position              The positions occupied by the door.
     */
    public Door(Area area, Orientation orientation, String destinationArea, DiscreteCoordinates coordinatesDestination, DiscreteCoordinates... position) {
        super(area, orientation, coordinatesDestination);
        occupationCells = List.of(position);
        this.areaDestination = destinationArea;
    }

    /**
     * Gets the current cells occupied by the door.
     *
     * @return The list of discrete coordinates representing the cells occupied by the door.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return occupationCells;
    }

    /**
     * Indicates whether the door takes cell space.
     *
     * @return False, as the door does not take cell space.
     */
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    /**
     * Indicates whether the door is interactable on the cell level.
     *
     * @return True, as the door is interactable on the cell level.
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * Indicates whether the door is interactable on the view level.
     *
     * @return False, as the door is not interactable on the view level.
     */
    @Override
    public boolean isViewInteractable() {
        return false;
    }

    /**
     * Accepts interaction with a visitor, either on the cell or view level.
     *
     * @param v                The visitor that interacts with the door.
     * @param isCellInteraction A boolean indicating whether the interaction occurs on the cell level.
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**
     *
     * @param canvas The canvas on which the door is to be drawn.
     */
    @Override
    public void draw(Canvas canvas) {

    }

    /**
     * Instead of fully incorporating this method into the game state, it is defined here.
     * This allows access to all the attributes of the door without having to define multiple getters.
     * It takes a player as a parameter, of which it is known that the game state has access.
     *
     * @param player      The player interacting with the door.
     * @param currentArea The current area of the player.
     */
    public void partialSwicthArea(ICMonPlayer player, ICMonArea currentArea){
        player.leaveArea();
        player.enterArea(currentArea,getCurrentMainCellCoordinates());
        player.strengthen();

        if (currentArea instanceof Town){
            player.openDialog("back_in_town");
        }
        if (currentArea instanceof Arena){
            player.openDialog("choose_your_opponent");

        }
        if (currentArea instanceof Lab){
            player.openDialog("welcome_to_the_lab");
        }
    }

    // Getter autorisé car String immutable

    /**
     * Retrieves the destination area of the door.
     *
     * @return The destination area to switch to.
     */
    public String getAreaDestination(){
        return areaDestination;
    }



}

