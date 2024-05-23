package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;


public interface ICMonInteractionVisitor extends AreaInteractionVisitor {


    default void interactWith(ICMonPlayer player, boolean isCellInteraction) {

    }

    default void interactWith(ICBall ball, boolean isCellInteraction) {

    }

    default void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {

    }


    default void interactWith(ICShopAssistant other, boolean isCellInteraction) {
    }
    default void interactWith(Door door, boolean isCellInteraction) {

    }
    default void interactWith(ICMonFightableActor icMonFightableActor, boolean isCellInteraction){

    }
}
