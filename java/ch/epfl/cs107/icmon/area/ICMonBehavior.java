package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class ICMonBehavior extends AreaBehavior {

    /**
     * Initializes the behavior of the ICMon game, setting cell types based on the provided image.
     *
     * @param window The window for display context.
     * @param name   The name of the behavior.
     */
    public ICMonBehavior(Window window, String name) {
        super(window, name);
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ICMonCellType color = ICMonCellType.toType(getRGB(height - 1 - y, x));
                setCell(x, y, new ICMonCell(x, y, color));
            }
        }
    }

    /**
     * Enumerates the allowed walking types for different cell types.
     */
    public enum AllowedWalkingType {
        NONE, // None
        SURF, // Only with surf
        FEET, // Only with feet
        ALL // All previous
    }

    /**
     * Enumerates the types of cells in the ICMon game.
     */
    public enum ICMonCellType {
        //https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
        NULL(0, AllowedWalkingType.NONE),
        WALL(-16777216, AllowedWalkingType.NONE),
        BUILDING(-8750470, AllowedWalkingType.NONE),
        INTERACT(-256, AllowedWalkingType.NONE),
        DOOR(-195580, AllowedWalkingType.ALL),
        INDOOR_WALKABLE(-1, AllowedWalkingType.FEET),
        OUTDOOR_WALKABLE(-14112955, AllowedWalkingType.FEET),
        WATER(-16776961, AllowedWalkingType.SURF),
        GRASS(-16743680, AllowedWalkingType.FEET)
        ;


        final int type;
        final AllowedWalkingType isWalkable;

        /**
         * Constructor for ICMonCellType.
         *
         * @param type       The color type of the cell.
         * @param isWalkable The allowed walking type for the cell.
         */
        ICMonCellType(int type, AllowedWalkingType isWalkable) {
            this.type = type;
            this.isWalkable = isWalkable;
        }

        /**
         * Converts an integer color type to the corresponding ICMonCellType.
         *
         * @param type The color type to be converted.
         * @return The ICMonCellType corresponding to the given color type.
         */
        public static ICMonCellType toType(int type) {
            for (ICMonCellType ict : ICMonCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assigning it to a type
            System.out.println(type);
            return NULL;
        }

        /**
         * Gets the allowed walking type for the cell.
         *
         * @return The allowed walking type for the cell.
         */
        public AllowedWalkingType getIsWalkable() {
            return isWalkable;
        }
    }

    /**
     * Cell adapted to the Tuto2 game
     */
    public class ICMonCell extends AreaBehavior.Cell implements AreaInteractionVisitor, Interactable {
        /// Type of the cell following the enum
        public final ICMonCellType type;



        /**
         * Constructor for ICMonCell.
         *
         * @param x    The x-coordinate of the cell.
         * @param y    The y-coordinate of the cell.
         * @param type The type of the cell.
         */
        public ICMonCell(int x, int y, ICMonCellType type) {
            super(x, y);
            this.type = type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {

            if (!entity.takeCellSpace()) return true;

            switch (type) {
                case NULL:
                case WALL:
                case BUILDING:
                case INTERACT:
                    return false;
            }

            for (Interactable element : entities) {
                if (element.takeCellSpace()) return false;
            }
            return true;
        }


        @Override

        public boolean isCellInteractable() {
            return true;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
        }

    }
}
