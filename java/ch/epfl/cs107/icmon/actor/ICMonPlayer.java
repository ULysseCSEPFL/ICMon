package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.*;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;


import java.util.*;


/**
 * ???
 */


public final class ICMonPlayer extends ICMonActor implements Interactor {

    /**
     * ???
     */
    private final static int MOVE_DURATION = 8;
    private final static int ANIMATION_DURATION = 8;
    /**
     * ???
     */
    private OrientedAnimation orientedAnimation;
    /**
     * ???
     */
    private float hp;
    private ICMonPlayerInteractionHandler ICMonPlayerInteractionHandler;

    private OrientedAnimation orientedAnimationground = new OrientedAnimation("actors/player", ANIMATION_DURATION / 2, Orientation.UP, this);
    ;

    private OrientedAnimation orientedAnimationwater = new OrientedAnimation("actors/player_water", ANIMATION_DURATION / 2, Orientation.UP, this);
    ;

    private Keyboard keyboard;

    private final ICMon.ICMonGameState gameState;

    private final ICMon.ICMonEventManager eventManager;

    private Dialog dialog;

    private boolean isDialoguing;

    private boolean isInFight = false;

    private List<Pokemon> pokemonCollection = new ArrayList<>();

    private boolean wantsToAvoidFight;



    /**
     * Creates a new ICMonPlayer in the specified area with the given orientation, coordinates, sprite name,
     * game state, and event manager.
     *
     * @param owner        The area where the player is located.
     * @param orientation  The initial orientation of the player.
     * @param coordinates  The initial coordinates of the player.
     * @param spriteName   The name of the sprite used for the player.
     * @param gameState    The game state associated with the player.
     * @param eventManager The event manager associated with the player.
     */
    public ICMonPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager eventManager) {
        super(owner, orientation, coordinates);
        this.hp = 10;
        this.keyboard = owner.getKeyboard();
        this.ICMonPlayerInteractionHandler = new ICMonPlayerInteractionHandler();
        orientedAnimation = new OrientedAnimation(spriteName, ANIMATION_DURATION / 2, Orientation.UP, this);
        this.gameState = gameState;
        this.eventManager = eventManager;
        isDialoguing = false;
    }
    /**
     * Receives a Pokemon and adds it to the player's Pokemon collection.
     *
     * @param pokemon The Pokemon to be received.
     */
    public void receivePokemon(Pokemon pokemon) {
        pokemonCollection.add(pokemon);
    }

    /**
     * Updates the player's state and handles movement and dialogue interactions.
     *
     * @param deltaTime Elapsed time since the last update, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Check if the player is not currently in a dialogue or on a fight cell
        if (!isDialoguing || gameState.isCellInteractionWithFightCells(this)) {
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));

            if (isDisplacementOccurs()) {
                orientedAnimation.update(deltaTime);
            } else {
                orientedAnimation.reset();
            }
        }

        // Check if the space key is pressed and there is an ongoing dialogue
        if (isSpacePressed() && dialog != null) {
            isDialoguing = true;
            dialog.update(deltaTime);

            // Check if the dialogue is completed
            if (dialog != null && dialog.isCompleted()) {
                isDialoguing = false;
            }
        }
    }

    /**
     * Checks if the space key is pressed.
     *
     * @return True if the space key is pressed, false otherwise.
     */
    private boolean isSpacePressed() {
        return keyboard.get(Keyboard.SPACE).isPressed();
    }

    /**
     * Draws the player's oriented animation and ongoing dialogue (if any) on the canvas.
     *
     * @param canvas The canvas on which to draw.
     */
    @Override
    public void draw(Canvas canvas) {
        orientedAnimation.draw(canvas);

        // Draw the dialogue if the player is currently in a dialogue
        if (isDialoguing) {
            dialog.draw(canvas);
        }
    }

    /**
     * Indicates whether the player takes cell space.
     *
     * @return True, as the player takes cell space.
     */
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * Indicates whether the player is cell interactable.
     *
     * @return True if the player is not currently in a dialogue, false otherwise.
     */
    @Override
    public boolean isCellInteractable() {
        return !isDialoguing;
    }



    /**
     * Indicates whether the player is view interactable.
     *
     * @return True, as the player is view interactable.
     */
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * Gets the current cells occupied by the player.
     *
     * @return A list containing the current main cell coordinates of the player.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Moves the player in the specified orientation if the corresponding button is pressed.
     *
     * @param orientation The orientation in which to move the player.
     * @param b           The button corresponding to the specified orientation.
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(MOVE_DURATION);
                orientedAnimation.orientate(orientation);
            }
        }
    }

    /**
     * Leaves the current area by unregistering the player from it.
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * Enters a new area at the specified position by registering the player in that area.
     *
     * @param area     The new area to enter.
     * @param position The position in the new area where the player should be placed.
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }



    /**
     * Resets the player's HP to the maximum value.
     */
    public void strengthen() {
        hp = 10;
    }

    /**
     * Gets the cells in the field of view of the player.
     *
     * @return A list containing the cell in the direction the player is facing.
     */
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    /**
     * Indicates whether the player wants cell interaction.
     *
     * @return True if the player wants cell interaction, false otherwise.
     */
    @Override
    public boolean wantsCellInteraction() {
        return !isDialoguing;
    }

    /**
     * Indicates whether the player wants view interaction.
     *
     * @return True if the player wants view interaction, false otherwise.
     */
    @Override
    public boolean wantsViewInteraction() {
        return !isDialoguing && keyboard.get(Keyboard.L).isPressed();
    }

    /**
     * Interacts with another interactable entity, forwarding the interaction to both the player and the game state.
     *
     * @param other             The interactable entity to interact with.
     * @param isCellInteraction A boolean indicating whether the interaction is cell-based.
     */
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(ICMonPlayerInteractionHandler, isCellInteraction);
        gameState.acceptInteraction(other, isCellInteraction);
    }

    /**
     * Accepts an interaction from an area interaction visitor.
     *
     * @param v                 The area interaction visitor.
     * @param isCellInteraction A boolean indicating whether the interaction is cell-based.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        /**
         * Handles the interaction with an ICCell.
         *
         * @param cell              The ICCell to interact with.
         * @param isCellInteraction A boolean indicating whether the interaction is cell-based.
         */
        public void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
            if (isCellInteraction) {
                if (cell.type.getIsWalkable() == ICMonBehavior.AllowedWalkingType.FEET) {
                    orientedAnimation = orientedAnimationground;
                } else if (cell.type.getIsWalkable() == ICMonBehavior.AllowedWalkingType.SURF) {
                    orientedAnimation = orientedAnimationwater;
                }
            }
        }

        /**
         * Handles the interaction with an ICBall.
         *
         * @param ball              The ICBall to interact with.
         * @param isCellInteraction A boolean indicating whether the interaction is cell-based.
         */
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if (wantsViewInteraction()) {
                System.out.println("Player is interacting with ball and received a new Bulbizarre!");
                ICMonPlayer.this.receivePokemonCollection();
                ball.collect();
                openDialog("collect_pokemons");
            }
        }

        /**
         * Handles the interaction with a Door.
         *
         * @param door              The Door to interact with.
         * @param isCellInteraction A boolean indicating whether the interaction is cell-based.
         */
        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction) {
                gameState.send(new PassDoorMessage(door, gameState));
            }
        }

        /**
         * Handles the interaction with an ICMonFightableActor.
         *
         * @param icMonFightableActor The ICMonFightableActor to interact with.
         * @param isCellInteraction   A boolean indicating whether the interaction is cell-based.
         */
        @Override
        public void interactWith(ICMonFightableActor icMonFightableActor, boolean isCellInteraction) {
            if (!isInFight && !pokemonCollection.isEmpty()) {
                if (icMonFightableActor instanceof Pokemon) {
                    isInFight = true;
                    fight((Pokemon) icMonFightableActor);
                }
            }

            if (pokemonCollection.isEmpty() && !isDialoguing) {
                openDialog("you_cannot_fight_with_your_hands");
                wantsToAvoidFight = true;
            }
        }
    }


    /**
     * This method allows the player to cancel the battle and exit the combat zone if they don't have a Pokémon.
     * The player can only close the dialogue once they have exited the combat zone.
     *
     * @return True if the player is in a cell interaction with fight cells, false otherwise.
     */
    private boolean isCellInteractionWithFightCells() {
        if (getOwnerArea() instanceof Arena) {
            return ((Arena) getOwnerArea()).wantsToAvoidFight(this);
        } else {
            return false;
        }
    }

    /**
     * Opens a dialog with the specified content.
     *
     * @param dialog The content of the dialog.
     */
    public void openDialog(String dialog) {
        if (!isDialoguing) {
            isDialoguing = true;
            this.dialog = new Dialog(dialog);
        }
    }

    /**
     * Initiates a fight between the player and a Pokemon.
     *
     * @param opponent The Pokemon to fight against.
     */
    public void fight(Pokemon opponent) {
        Random random = new Random();
        int randomIndex = random.nextInt(pokemonCollection.size());

        // Event representing the fight, transmitted to the game through the start method using the EventManager attribute of the Player
        ICMonFight fight = new ICMonFight(pokemonCollection.get(randomIndex), opponent, this);
        PokemonFightEvent fightEvent = new PokemonFightEvent(eventManager, fight, this);

        // Sending the message to the GameState configured by the Combat event.
        gameState.send(new SuspendWithEventMessage(fightEvent));

        // Consequences of completing the event
        LeaveAreaAction leaveAreaAction = new LeaveAreaAction((ICMonActor) opponent, (ICMonArea) getOwnerArea());
        fightEvent.onComplete(leaveAreaAction);
    }

    /**
     * Sets the player's fight status.
     *
     * @param isInFight True if the player is in a fight, false otherwise.
     */
    public void setIsInFight(boolean isInFight) {
        this.isInFight = isInFight;
    }

    /**
     * Adds Pokemon to the player's collection.
     */
    private void receivePokemonCollection() {
        pokemonCollection.add(new Bulbizzare());
        pokemonCollection.add(new Nidoqueen());
        pokemonCollection.add(new Latios());
        pokemonCollection.add(new Pikachu());
        pokemonCollection.add(new Ampharos());
        pokemonCollection.add(new Aron());
        pokemonCollection.add(new Azumarill());
        pokemonCollection.add(new Qwilfish());
    }

    /**
     * Gets the player's Pokemon collection.
     *
     * @return The Pokemon collection of the player.
     */
    public List<Pokemon> getPokemonCollection() {
        return pokemonCollection;
    }
}













