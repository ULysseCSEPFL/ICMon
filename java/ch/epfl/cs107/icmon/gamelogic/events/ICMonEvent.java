package ch.epfl.cs107.icmon.gamelogic.events;
import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnRegisterEventAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Entity;
import ch.epfl.cs107.play.math.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {


    private boolean started = false;
    private boolean completed = false;
    private boolean suspended = false;

    private ICMon.ICMonEventManager eventManager;

    private List<Action> startActions = new ArrayList<>();
    private List<Action> completeActions = new ArrayList<>();
    private List<Action> suspendActions = new ArrayList<>();
    private List<Action> resumeActions = new ArrayList<>();



    /**
     * Constructor for ICMonEvent.
     *
     * @param eventManager The event manager handling this event.
     */
    public ICMonEvent(ICMon.ICMonEventManager eventManager) {
        this.eventManager = eventManager;
    }


    // We create a private static method in order to modularize the code. The iteration depends on the attribut of the object.
    /**
     * Performs a list of actions.
     *
     * @param listActions The list of actions to perform.
     */
    private static void performActions(List<Action> listActions) {
        for (Action action : listActions) {
            action.perform();
        }
    }

    /**
     * Starts the event.
     */
    public final void start() {
        if (!started) {
            onStart(new RegisterEventAction(this, eventManager));
            performActions(startActions);
        }
        started = true;
    }

    /**
     * Completes the event.
     */
    public final void complete() {
        if (!completed || started) {
            onComplete(new UnRegisterEventAction(this, eventManager));
            performActions(completeActions);
            started = false;
            completed = true;
        }
    }

    /**
     * Suspends the event.
     */
    public final void suspend() {
        if (!completed || !suspended || started) {
            performActions(suspendActions);
            suspended = true;
        }
    }

    /**
     * Resumes the event.
     */
    public final void resume() {
        if (!completed || suspended || started) {
            performActions(resumeActions);
            suspended = false;
        }
    }

    /**
     * Adds an action to be performed when the event starts.
     *
     * @param action The action to be performed on start.
     */
    public final void onStart(Action action) {
        startActions.add(action);
    }

    /**
     * Adds an action to be performed when the event completes.
     *
     * @param action The action to be performed on completion.
     */
    public final void onComplete(Action action) {
        completeActions.add(action);
    }

    /**
     * Adds an action to be performed when the event is suspended.
     *
     * @param action The action to be performed on suspension.
     */
    public final void onSuspension(Action action) {
        suspendActions.add(action);
    }

    /**
     * Adds an action to be performed when the event resumes.
     *
     * @param action The action to be performed on resumption.
     */
    public final void onResume(Action action) {
        resumeActions.add(action);
    }

    /**
     * Checks if the event has completed.
     *
     * @return True if the event has completed, false otherwise.
     */
    public final boolean isCompleted() {
        return completed;
    }

    /**
     * Checks if the event has started.
     *
     * @return True if the event has started, false otherwise.
     */
    public final boolean isStarted() {
        return started;
    }

    /**
     * Checks if the event has been suspended.
     *
     * @return True if the event has been suspended, false otherwise.
     */
    public final boolean isSuspended() {
        return suspended;
    }



}




