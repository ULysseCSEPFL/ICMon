package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class ICMonArea extends Area {

    /**
     * Creates the area by registering actors and defining the layout.
     * Subclasses must implement this method to specify the area's configuration.
     */
    protected abstract void createArea();

    /**
     * Gets the player's spawn position in the area.
     *
     * @return The player's spawn position.
     */
    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    /**
     * Initializes the area by setting its behavior, creating the area, and checking for successful initialization.
     *
     * @param window      The window for display context.
     * @param fileSystem  The file system.
     * @return True if initialization is successful, false otherwise.
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new ICMonBehavior(window, getTitle()));
            createArea();
            return true;
        }
        return false;
    }

    /**
     * Gets the camera scale factor for the area.
     *
     * @return The camera scale factor.
     */
    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }

}

