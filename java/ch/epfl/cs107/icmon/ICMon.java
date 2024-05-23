package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.area.maps.Lab;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.EndOfTheGameEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.icmon.message.PauseMenuEventMessage;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public final class ICMon extends AreaGame {

    private ICMonGameState gameState = new ICMonGameState();
    /**
     * ???
     */
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    /**
     * ???
     */
    private final String[] areas = {"town","lab","arena"};
    /**
     * ???
     */
    private ICMonPlayer player;
    /**
     * ???
     */
    private int areaIndex;

    //private List<GamePlayMessage> letterBox;
    private GamePlayMessage letterBox;

    private List<ICMonEvent> eventList = new ArrayList<>();
    private List<ICMonEvent> eventsToBeginList = new ArrayList<>();
    private List<ICMonEvent> completedEventList = new ArrayList<>();

    private ICMonEventManager eventManager = new ICMonEventManager();

    // Allows the SuspendProcess to be put in the begin method.
    private boolean startSuspendProcess;

    /**
     * Creates and initializes the areas for the game.
     */
    private void createAreas() {
        addArea(new Town());
        addArea(new Lab());
        addArea(new Arena());
    }

    /**
     * Begins the game.
     *
     * @param window     The display context. Must not be null.
     * @param fileSystem The given file system. Must not be null.
     * @return True if the game begins successfully, false otherwise.
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {

            // Initialisation des zones de jeu
            createAreas();
            areaIndex = 0;
            initArea(areas[areaIndex]);
            player.openDialog("welcome_to_icmon");


            // Configuration et démarrage de l'événement CollectItemEvent
            ICBall ball = new ICBall(getCurrentArea(), Orientation.UP, new DiscreteCoordinates(6, 6), "items/icball");
            CollectItemEvent collectItemEvent = new CollectItemEvent(player, eventManager, ball);
            collectItemEvent.onStart(new LogAction("CollectItemEvent started!"));
            collectItemEvent.onStart(new RegisterinAreaAction(getCurrentArea(), ball));
            collectItemEvent.onComplete(new LogAction("CollectItemEvent completed!"));
            collectItemEvent.start();


            // Initialisation de l'événement EndOfTheGameEvent
            EndOfTheGameEvent endGameEvent = new EndOfTheGameEvent(player, eventManager);
            endGameEvent.onStart(new LogAction("EndOfTheGameEvent started!"));
            endGameEvent.onComplete(new LogAction("EndOfTheGameEvent completed!"));

            // Configuration pour démarrer EndOfTheGameEvent une fois CollectItemEvent terminé
            collectItemEvent.onComplete(new StartEventAction(endGameEvent));


            return true;
        }
        return false;
    }

    /**
     * Updates the game state.
     *
     * @param deltaTime Elapsed time since the last update, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        gameState.isCellInteractionWithFightCells(player);
        eventList.addAll(eventsToBeginList);


        List<ICMonEvent> currentEvents = new ArrayList<>(eventList);
        // pour éviter une erreur de compilation, on utilise une copie temporaire.
        for (ICMonEvent event : currentEvents) {
            event.update(deltaTime);
        }

        eventList.removeAll(completedEventList);
        completedEventList.clear();

        //Le switch-case n'est disponible que pour JAVA 21, pour ce cours il a été choisi d'utilisé JAVA 17. Nous utilisons donc if-else.

        if(letterBox instanceof PauseMenuEventMessage){

            // process des méthode de l'interface commune aux PauseMenuEventMessage
            ((PauseMenuEventMessage) letterBox).process(this, ((PauseMenuEventMessage) letterBox).getEvent());

            // nous sommes obligés car la méthode setPauseMenu est protected, le compilateur passe une seule fois au commencement

            // spécifique au message SuspendWithEventMessage.
            if (letterBox instanceof SuspendWithEventMessage){
                ((SuspendWithEventMessage) letterBox).process(eventList);
            }

            // une fois que l'event est commencé. Il est possible d'inviquer cette méthode pour tout les messages impliquants un Menu de pause
            this.setPauseMenu(((PauseMenuEventMessage) letterBox).getEvent().getPauseMenu());

            letterBox = null;
        }

        if(letterBox != null) {
            letterBox.process();
            letterBox = null;
        }

    }

    /**
     * Ends the game.
     */
    @Override
    public void end() {

    }

    /**
     * Gets the title of the game.
     *
     * @return The title of the game.
     */
    @Override
    public String getTitle() {
        return "ICMon";
    }

    /**
     * Initializes the current game area based on the provided area key.
     *
     * @param areaKey The key of the area to initialize.
     */
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, false);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        player = new ICMonPlayer(area, Orientation.DOWN, coords, "actors/player", gameState, eventManager);
        player.enterArea(area, coords);
        player.centerCamera();
    }


    public class ICMonGameState {
        private ICMonGameState() {
        }

        /**
         * Accepts interaction with interactable objects.
         *
         * @param interactable      The interactable object.
         * @param isCellInteraction True if the interaction is cell-based, false otherwise.
         */
        public void acceptInteraction(Interactable interactable, boolean isCellInteraction) {
            for (ICMonEvent event : ICMon.this.eventList) {
                interactable.acceptInteraction(event, isCellInteraction);
            }
        }

        /**
         * Sends a game play message to the game state.
         *
         * @param message The game play message.
         */
        public void send(GamePlayMessage message) {

            letterBox = message;
        }

        /**
         * Switches the game area based on the provided door.
         *
         * @param door The door through which the area switch will occur.
         */
        public void switchArea(Door door) {
            ICMonArea currentArea = (ICMonArea)setCurrentArea(door.getAreaDestination(), true);
            door.partialSwicthArea(player, currentArea);
        }
        // méthode qui permet au player si il se trouve sur une zone de combat. Communiquée par le GameState pour ne pas casse l'encapsulation.

        /**
         * Checks if there is a cell interaction with fight cells in the current area.
         * If the current area is an instance of Arena, it invokes wantsToAvoidFight to determine if the player should avoid a fight.
         *
         * @param player The ICmon player involved in the interaction.
         * @return True if there is a cell interaction with fight cells, otherwise false.
         */
        public boolean isCellInteractionWithFightCells(ICMonPlayer player){
            if (getCurrentArea() instanceof Arena){
                if (((Arena) getCurrentArea()).wantsToAvoidFight(player)) return true;
            }
            return false;
        }

    }

    public class ICMonEventManager{

        /**
         * Registers an event to begin.
         *
         * @param event The event to register.
         */
        public void registerEvent(ICMonEvent event){
            eventsToBeginList.add(event);
        }

        /**
         * Unregisters an event, marking it as completed.
         *
         * @param event The event to unregister.
         */
        public void unregisterEvent(ICMonEvent event){
            completedEventList.add(event);
        }

    }

}