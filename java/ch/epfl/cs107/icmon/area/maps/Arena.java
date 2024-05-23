package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.*;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Arena extends ICMonArea {

    /**
     * Returns the title of the area.
     *
     * @return The title of the area.
     */
    @Override
    public String getTitle() {
        return "arena";
    }

    /**
     * Attribute specific to the Arena class. This list will allow us to check if the Player is on a Pokémon position.
     */
    List<ICMonFightableActor> fightableActorList= new ArrayList<>();

    /**
     * Creates the elements present in the arena, such as background, foreground, doors, and Pokémon.
     */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this, Orientation.UP,"town",new DiscreteCoordinates(20,15),new DiscreteCoordinates(4,1), new DiscreteCoordinates(5,1)));

        // Creating Pokémon in the arena
        Bulbizzare bulbizzare = new Bulbizzare(this,Orientation.DOWN,new DiscreteCoordinates(2,2));
        Latios latios = new Latios(this,Orientation.DOWN,new DiscreteCoordinates(2,3));
        Nidoqueen nidoqueen = new Nidoqueen(this,Orientation.DOWN,new DiscreteCoordinates(2,4));
        Ampharos ampharos1 = new Ampharos(this,Orientation.DOWN,new DiscreteCoordinates(2,5));
        Aron aron = new Aron(this, Orientation.DOWN, new DiscreteCoordinates(2,6));
        Azumarill azumarill = new Azumarill(this,Orientation.DOWN, new DiscreteCoordinates(6,2));
        Pikachu pikachu1 = new Pikachu(this,Orientation.UP, new DiscreteCoordinates(6,3));
        Pikachu  pikachu2 = new Pikachu(this,Orientation.DOWN, new DiscreteCoordinates(6,4));
        Qwilfish qwilfish = new Qwilfish(this,Orientation.DOWN, new DiscreteCoordinates(6,5));
        Ampharos ampharos2 = new Ampharos(this, Orientation.DOWN, new DiscreteCoordinates(6,6));


        // We record the Pokémon in the map
        registerActor(bulbizzare);
        registerActor(latios);
        registerActor(nidoqueen);
        registerActor(ampharos1);
        registerActor(aron);
        registerActor(ampharos2);
        registerActor(azumarill);
        registerActor(pikachu1);
        registerActor(pikachu2);
        registerActor(qwilfish);

        // We add them to this list, which will later be used to determine if the player is in a combat zone.
        fightableActorList.add(bulbizzare);
        fightableActorList.add(latios);
        fightableActorList.add(nidoqueen);
        fightableActorList.add(ampharos1);
        fightableActorList.add(azumarill);
        fightableActorList.add(pikachu1);
        fightableActorList.add(pikachu2);
        fightableActorList.add(qwilfish);
        fightableActorList.add(ampharos2);
        fightableActorList.add(aron);
    }

    /**
     * Returns the spawn position for the player in the arena.
     * In this case, the method returns null, indicating that the player's spawn position is not defined.
     * This method needs to be implemented to specify the actual spawn position.
     *
     * @return The spawn position for the player in the arena.
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }

    /**
     * Checks if the player wants to avoid a fight based on their position.
     * This method is used in the ICMonPlayer class; it determines whether the player is on the position of a Pokémon.
     * If so, the player is instructed to 1) exit the combat zone, and 2) after that,
     * they are allowed to close the instruction (dialogue) informing them that it is impossible to battle without a Pokémon (they need to have picked up a Pokémon beforehand).
     * It is initially invoked in the GameState, updated by the Game, and called by ICMon. This ensures that encapsulation is not broken.
     *
     * @param player The player whose position is checked.
     * @return True if the player wants to avoid a fight, false otherwise.
     */
    public boolean wantsToAvoidFight(ICMonPlayer player) {
        for (ICMonFightableActor actor : fightableActorList) {
            if (player.getPosition().equals(actor.getPosition())) {
                return true;
            }
        }
        return false;
    }
}
