package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ICMonFightAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RunAwayAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuperAttackAction;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.List;
import java.util.Random;

public class ICMonFight extends PauseMenu {


    private final double ATTACK_ACTION_PROBABILITY = 0.9;
    private final double SUPER_ATTACK_PROBABILITY = 0.18;

    private final Pokemon playerPokemon;

    private final Pokemon opponentPokemon;

    private final ICMonFightArenaGraphics arena;

    private State currentState;

    private boolean isRunning = false;

    private final ICMonPlayer player;

    private ICMonFightActionSelectionGraphics actionSelectionGraphics;

    /**
     * ICMonFight represents a Pokemon fight in the game, managing the interaction between
     * a player's Pokemon and an opponent's Pokemon.
     *
     * @param playerPokemon     The Pokemon controlled by the player.
     * @param opponentPokemon   The opponent's Pokemon.
     * @param player            The player involved in the fight.
     */
    public ICMonFight(Pokemon playerPokemon, Pokemon opponentPokemon, ICMonPlayer player) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.player = player;
        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, opponentPokemon.getProperties(), playerPokemon.getProperties());
        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "hello world"));
        currentState = State.INTRODUCTION;
    }

    /**
     * Enumeration representing the possible states of the Pokemon fight.
     */
    public enum State {
        INTRODUCTION, ACTION_SELECTION, ACTION_EXECUTION, OPPONENT_ACTION, CONCLUSION
    }

    /**
     * Draw the fight menu on the canvas, displaying the fight arena graphics.
     *
     * @param c The canvas on which to draw the fight menu.
     */
    @Override
    protected void drawMenu(Canvas c) {
        arena.draw(c);
    }

    // on réduit les chances d'avoir une action de type RunAway de la part de l'adversaire :) Pour avoir un minimum d'action !

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Finite State Machine.
        switch (currentState) {

            case INTRODUCTION:
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "Welcome to the fight ! "));
                if (isSpacePressed()) {
                    actionSelectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR,getKeyboard(),playerPokemon.getActionsList());
                    currentState = State.ACTION_SELECTION;

                }
                break;

            case ACTION_SELECTION:


                actionSelectionGraphics.resetChoice();

                arena.setInteractionGraphics(actionSelectionGraphics);
                actionSelectionGraphics.update(deltaTime);

                if (actionSelectionGraphics.choice() != null){
                    currentState = State.ACTION_EXECUTION;
                }

                break;


            case ACTION_EXECUTION:

                if(actionSelectionGraphics.choice().doAction(playerPokemon,opponentPokemon)) {

                    if (opponentPokemon.isDead()) {
                        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The Player has won the Game"));
                        currentState = State.CONCLUSION;
                    } else {
                        currentState = State.OPPONENT_ACTION;
                    }
                }
                else{
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player decided not to continue the fight"));
                    currentState = State.CONCLUSION;
                }
                break;

            /*This case is not handled by the Player, so let's make the game a bit more entertaining by choosing randomly!
              Using the Super attack becomes even more interesting since the opponent has a chance of leaving before the player kills them!
              The player may also be tempted to Run Away if they see their health bar decreasing, knowing that the opponent has a chance of using their super!*/
            case OPPONENT_ACTION:

                List<ICMonFightAction> actions = opponentPokemon.getActionsList();
                Random random = new Random();
                double randomValue = random.nextDouble(); // Random value between 0.0 and 1.0

                // Selects an AttackAction or a SuperAttackAction based on probability.
                if (randomValue <= ATTACK_ACTION_PROBABILITY) {

                    // Choice between SuperAttackAction and AttackAction
                    double actionTypeRandomValue = random.nextDouble();
                    ICMonFightAction chosenAction = null;

                    // Choose a SuperAttackAction if possible
                    if (actionTypeRandomValue <= SUPER_ATTACK_PROBABILITY) {
                        for (ICMonFightAction action : actions) {
                            if (action instanceof SuperAttackAction) {
                                chosenAction = action;
                                break;
                            }
                        }
                    }

                    // If no SuperAttackAction is chosen or if the probability is not in favor, choose an AttackAction
                    if (chosenAction == null) {
                        for (ICMonFightAction action : actions) {
                            if (action instanceof AttackAction) {
                                chosenAction = action;
                                break;
                            }
                        }
                    }

                    // If an action has been chosen, execute it
                    if (chosenAction != null) {
                        executeOpponentAction(chosenAction);
                        break;
                    }
                }

                // If no AttackAction or SuperAttackAction is chosen, choose another action randomly
                ICMonFightAction fallbackAction = actions.get(random.nextInt(actions.size()));
                executeOpponentAction(fallbackAction);
                break;

            case CONCLUSION:
                if (isSpacePressed()) {
                    isRunning = false;
                    resetFightersHp();
                    currentState = State.INTRODUCTION; // Reset the state for the next battle
                }
                break;
        }
    }

    /**
     * Check if the space key is pressed.
     *
     * @return True if the space key is pressed, false otherwise.
     */
    private boolean isSpacePressed() {
        return getKeyboard().get(Keyboard.SPACE).isPressed();
    }

    /**
     * Reset the HP of the player's and opponent's Pokemon after each fight.
     */
    private void resetFightersHp(){
        playerPokemon.resetHp();
        opponentPokemon.resetHp();
    }

    /**
     * Get the running status of the Pokemon fight.
     *
     * @return True if the fight is running, false otherwise.
     */
    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Set the running status of the Pokemon fight.
     *
     * @param isRunning True to indicate the fight is running, false otherwise.
     */
    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    /**
     * Execute the opponent's action during the fight.
     *
     * @param action The action to be executed by the opponent.
     */
    private void executeOpponentAction(ICMonFightAction action) {
        if (action.doAction(opponentPokemon, playerPokemon)) {
            if (playerPokemon.isDead()) {
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent has won the fight !"));
                player.getPokemonCollection().remove(playerPokemon);
                currentState = State.CONCLUSION;
            } else {
                currentState = State.ACTION_SELECTION;
            }
        } else {
            arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent decided not to continue the fight"));
            currentState = State.CONCLUSION;
        }
    }



}
