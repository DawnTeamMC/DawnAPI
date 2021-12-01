package com.hugman.dawn.api.util;

import com.hugman.dawn.api.object.block.AbstractLeveledCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

public final class CauldronUtil {
	public static boolean isNotFull(BlockState state) {
		return !isFull(state);
	}

	public static boolean isFull(BlockState state) {
		Block block = state.getBlock();
		if(block instanceof AbstractLeveledCauldronBlock abstractLeveledCauldronBlock) {
			return abstractLeveledCauldronBlock.isFull(state);
		}
		else if(block instanceof LeveledCauldronBlock leveledCauldronBlock) {
			return leveledCauldronBlock.isFull(state);
		}
		else {
			return false;
		}
	}

	public static int getLevel(BlockState state) {
		Block block = state.getBlock();
		if(block instanceof AbstractLeveledCauldronBlock abstractLeveledCauldronBlock) {
			return abstractLeveledCauldronBlock.getLevel(state);
		}
		else if(block instanceof LeveledCauldronBlock) {
			return state.get(LeveledCauldronBlock.LEVEL);
		}
		else {
			return 0;
		}
	}

	public static BlockState modifyCauldron(BlockState currentCauldronState, Block newCauldron, int level) {
		if(newCauldron instanceof AbstractLeveledCauldronBlock abstractLeveled) {
			return currentCauldronState.isOf(abstractLeveled) ? abstractLeveled.setLevel(currentCauldronState, level) : abstractLeveled.defaultWithLevel(level);
		}
		else if(newCauldron instanceof LeveledCauldronBlock leveled) {
			if(currentCauldronState.isOf(leveled)) {
				int i = currentCauldronState.get(LeveledCauldronBlock.LEVEL) + level;
				i = Math.min(i, 3);
				return i <= 0 ? Blocks.CAULDRON.getDefaultState() : currentCauldronState.with(LeveledCauldronBlock.LEVEL, i);
			}
			else {
				return leveled.getDefaultState().with(LeveledCauldronBlock.LEVEL, level);
			}
		}
		else {
			return newCauldron.getDefaultState();
		}
	}

	public static void addBottleInteractions(Map<Item, CauldronBehavior> map, Block cauldron, Item bottle) {
		CauldronBehavior pourBottleBehavior = CauldronInteractionBuilder.create().cauldron(cauldron).addLevel(1).item(Items.GLASS_BOTTLE).sound(SoundEvents.ITEM_BOTTLE_EMPTY).build();

		CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(bottle, pourBottleBehavior); // Pour bottle into empty cauldron
		map.put(bottle, pourBottleBehavior); // Pour bottle into same cauldron

		map.put(Items.GLASS_BOTTLE, CauldronInteractionBuilder.create().addLevel(-1).item(bottle).sound(SoundEvents.ITEM_BOTTLE_FILL).build()); // Fill bottle from cauldron
	}

	public static void addBucketInteractions(Map<Item, CauldronBehavior> map, Block cauldron, Item bucket) {
		CauldronBehavior pourBucketBehavior = CauldronInteractionBuilder.create().cauldron(cauldron).addLevel(3).item(Items.BUCKET).sound(SoundEvents.ITEM_BUCKET_EMPTY).build();

		CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(bucket, pourBucketBehavior); // Pour bucket into any empty cauldron
		map.put(bucket, pourBucketBehavior); // Pour bucket into same cauldron

		map.put(Items.BUCKET,CauldronInteractionBuilder.create().addLevel(-3).item(bucket).sound(SoundEvents.ITEM_BUCKET_FILL).build()); // Fill bucket from cauldron
	}
}
