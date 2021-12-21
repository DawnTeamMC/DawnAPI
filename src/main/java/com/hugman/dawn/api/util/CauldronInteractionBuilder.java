package com.hugman.dawn.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.world.event.GameEvent;

import java.util.function.Predicate;

public class CauldronInteractionBuilder {
	private Predicate<BlockState> predicate;
	private ItemStack stack;
	private Block cauldron;
	private int level;
	private boolean overwriteLevel;
	private SoundEvent sound;

	private CauldronInteractionBuilder(Predicate<BlockState> predicate, ItemStack stack, Block cauldron, int level, boolean overwriteLevel, SoundEvent sound) {
		this.predicate = predicate;
		this.stack = stack;
		this.cauldron = cauldron;
		this.level = level;
		this.overwriteLevel = overwriteLevel;
		this.sound = sound;
	}

	public static CauldronInteractionBuilder create() {
		return new CauldronInteractionBuilder(state -> true, null, null, 0, false, null);
	}

	/**
	 * Sets the predicate that the current cauldron must pass in order for the interaction to occur.
	 *
	 * @param predicate a predicate
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder test(Predicate<BlockState> predicate) {
		this.predicate = predicate;
		return this;
	}

	/**
	 * Sets the predicate that the level of the current cauldron's level must pass in order for the interaction to occur.
	 * <p>Note: This is a shortcut method for {@link #test}.</p>
	 *
	 * @param predicate a predicate
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder testLevel(Predicate<Integer> predicate) {
		return test(state -> predicate.test(CauldronUtil.getLevel(state)));
	}

	/**
	 * Sets the item stack that will result from the interaction.
	 *
	 * @param newStack an item stack
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder stack(ItemStack newStack) {
		this.stack = newStack;
		return this;
	}

	/**
	 * Sets the item that will result from the interaction.
	 * <p>Note: This is a shortcut method for {@link #stack}.</p>
	 *
	 * @param item an item
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder item(Item item) {
		this.stack = item.getDefaultStack();
		return this;
	}

	/**
	 * Sets the cauldron that will result from the interaction.
	 *
	 * @param cauldron a cauldron. If set to <code>null</code>, then the cauldron will try to stay the same.
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder cauldron(Block cauldron) {
		this.cauldron = cauldron;
		return this;
	}

	/**
	 * Makes the cauldron try to stay the same after the interaction.
	 * <p>Note: This is a shortcut method for {@link #cauldron}.</p>
	 *
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder sameCauldron() {
		return cauldron(null);
	}

	/**
	 * Sets the level to add to the cauldron after the interaction. Also sets a test to verify if the interaction is possible with the current level.
	 *
	 * @param level a level
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder addLevel(int level) {
		this.level = level;
		this.overwriteLevel = false;
		if(level > 0) return test(CauldronUtil::isNotFull);
		else if(level < 0) return testLevel(i -> i >= level * -1);
		else return this;
	}

	/**
	 * Sets the level of the cauldron after the interaction.
	 *
	 * @param level a level
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder setLevel(int level) {
		this.level = level;
		this.overwriteLevel = true;
		return this;
	}

	/**
	 * Sets the sound that will play upon the interaction.
	 *
	 * @param sound a sound event
	 * @return this builder for chaining
	 */
	public CauldronInteractionBuilder sound(SoundEvent sound) {
		this.sound = sound;
		return this;
	}

	/**
	 * Creates a cauldron interaction from the properties of this builder.
	 */
	public CauldronBehavior build() {
		return (state, world, pos, player, hand, stack) -> {
			if(predicate.test(state)) {
				if(cauldron == null) cauldron = state.getBlock();
				int newLevel = !overwriteLevel ? CauldronUtil.getLevel(state) + level : level;
				if(!world.isClient) {
					BlockState returnedState = CauldronUtil.modifyCauldron(state, cauldron, newLevel);

					player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, this.stack.copy()));
					player.incrementStat(CauldronUtil.isFull(returnedState) ? Stats.FILL_CAULDRON : Stats.USE_CAULDRON);
					player.incrementStat(Stats.USED.getOrCreateStat(this.stack.getItem()));
					world.setBlockState(pos, returnedState);
					world.emitGameEvent(null, newLevel < 0 ? GameEvent.FLUID_PICKUP : GameEvent.FLUID_PLACE, pos);
					if(sound != null) world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}
				return ActionResult.success(world.isClient);
			}
			return ActionResult.PASS;
		};
	}

	/**
	 * Creates a new builder with the same properties as this builder.
	 *
	 * @return the new builder
	 */
	public CauldronInteractionBuilder copy() {
		return new CauldronInteractionBuilder(this.predicate, this.stack, this.cauldron, this.level, this.overwriteLevel, this.sound);
	}
}
