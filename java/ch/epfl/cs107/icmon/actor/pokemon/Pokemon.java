package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.gamelogic.actions.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonFightableActor {


    PokemonProperties properties = new PokemonProperties(this);

    private RPGSprite sprite;

    private String name;

    private int hp;
    private int maxHp;

    private int damage;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Pokemon(Area area, Orientation orientation, DiscreteCoordinates position, String name,int maxHp, int damage) {
        super(area, orientation, position);
        sprite = new RPGSprite("pokemon/"+name,1,1,this);
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */

    public void sufferDamage(int damage){
        if (this.hp>=0){
            hp -= damage;
        }
    }

    /**
     * Checks if the Pokemon is dead by comparing its current HP to zero.
     *
     * @return True if the Pokemon is dead, false otherwise.
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * Draws the Pokemon on the specified canvas using its sprite.
     *
     * @param canvas The canvas on which to draw the Pokemon.
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /**
     * Gets the properties of the Pokemon.
     *
     * @return The properties of the Pokemon.
     */
    public PokemonProperties getProperties() {
        return properties;
    }


    /**
     * A class representing the properties of a Pokemon, providing read-only access to its attributes.
     */
    public final class PokemonProperties {

        private final Pokemon pokemon;

        /**
         * Creates PokemonProperties with the specified Pokemon.
         *
         * @param pokemon The Pokemon whose properties are being represented.
         */
        public PokemonProperties(Pokemon pokemon) {
            this.pokemon = pokemon;
        }

        /**
         * Gets the name of the Pokemon.
         *
         * @return The name of the Pokemon.
         */
        public String name() {
            return pokemon.name;
        }

        /**
         * Gets the current HP of the Pokemon.
         *
         * @return The current HP of the Pokemon.
         */
        public float hp() {
            return pokemon.hp;
        }

        /**
         * Gets the maximum HP of the Pokemon.
         *
         * @return The maximum HP of the Pokemon.
         */
        public float maxHp() {
            return pokemon.maxHp;
        }

        /**
         * Gets the damage value of the Pokemon.
         *
         * @return The damage value of the Pokemon.
         */
        public int damage() {
            return pokemon.damage;
        }
    }

    /**
     * Retrieves the list of fight actions associated with the Pokemon.
     *
     * @return The list of fight actions associated with the Pokemon.
     */
    public abstract List<ICMonFightAction> getActionsList();

    /**
     * Resets the HP of the Pokemon to its maximum HP value.
     */
    public void resetHp() {
        hp = maxHp;
    }

    /**
     * Sets the HP of the Pokemon with a bonus value.
     *
     * @param bonus The bonus value to add to the current HP.
     */
    public void setHp(int bonus) {
        hp += bonus;
    }

}