package ch.epfl.cs107.icmon.gamelogic.actions;

public class LogAction implements Action{

    private final String message;

    /**
     * Constructor for LogAction.
     *
     * @param message The message to be logged.
     */
    public LogAction(String message){
        this.message = new String(message);
    }

    /**
     * Performs the action, logging the message to the console.
     */
    @Override
    public void perform() {
        System.out.println(message);
    }
}


