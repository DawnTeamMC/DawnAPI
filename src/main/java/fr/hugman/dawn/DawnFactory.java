package fr.hugman.dawn;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.block.DawnFungusBlock;
import fr.hugman.dawn.block.DawnSaplingBlock;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.dawn.registry.DawnRegistryKeys;
import fr.hugman.dawn.shape.ConfiguredShape;
import fr.hugman.dawn.shape.Shape;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessor;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.function.Predicate;

public final class DawnFactory {
	/*=============*/
	/*   GENERIC   */
	/*=============*/

	public static Block block(AbstractBlock.Settings settings) {
		return new Block(settings);
	}

	public static Item item(Item.Settings settings) {
		return new Item(settings);
	}

	public static RegistryKey<Biome> biome(Identifier id) {
		return RegistryKey.of(RegistryKeys.BIOME, id);
	}

	public static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
	}

	public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id);
	}

	public static <S extends Shape> ShapeType<S> shapeType(Codec<S> codec) {
		return new ShapeType<>(codec);
	}

	public static <P extends ShapeProcessor> ShapeProcessorType<P> shapeProcessorType(Codec<P> codec) {
		return new ShapeProcessorType<>(codec);
	}

	public static RegistryKey<ConfiguredShape> configuredShape(Identifier id) {
		return RegistryKey.of(DawnRegistryKeys.CONFIGURED_SHAPE, id);
	}

	public static <P extends TrunkPlacer> TrunkPlacerType<P> trunkPlacer(Codec<P> codec) {
		return new TrunkPlacerType<>(codec);
	}

	public static <P extends FoliagePlacer> FoliagePlacerType<P> foliagePlacer(Codec<P> codec) {
		return new FoliagePlacerType<>(codec);
	}



	/*==========*/
	/*   WOOD   */
	/*==========*/

	public static Block planks(boolean isNether, MapColor color) {
		return new Block(DawnFactory.planksSettings(isNether, color));
	}

	public static DawnBlockSettings planksSettings(boolean isNether, MapColor color) {
		DawnBlockSettings settings = DawnBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, color)
				.item()
				.strength(2.0f, 3.0f)
				.sounds(isNether ? BlockSoundGroup.NETHER_WOOD : BlockSoundGroup.WOOD);
		if(isNether) settings.flammability(5, 20);
		return settings;
	}

	public static DawnBlockSettings logSettings(boolean isNether, MapColor woodColor, MapColor barkColor) {
		DawnBlockSettings settings = DawnBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor)
				.item()
				.strength(2.0F)
				.sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD);
		if(isNether) settings.flammability(5, 5);
		return settings;
	}

	public static DawnBlockSettings logSettings(boolean isNether, MapColor color) {
		DawnBlockSettings settings = DawnBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, color)
				.item()
				.strength(2.0F)
				.sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD);
		if(isNether) settings.flammability(5, 5);
		return settings;
	}

	public static StairsBlock stairs(Block baseBlock) {
		return new StairsBlock(baseBlock.getDefaultState(), DawnBlockSettings.copy(baseBlock));
	}

	public static SlabBlock slab(Block baseBlock) {
		return new SlabBlock(DawnBlockSettings.copy(baseBlock));
	}

	public static PressurePlateBlock pressurePlate(Block baseBlock, PressurePlateBlock.ActivationRule activationRule, SoundEvent depressSound, SoundEvent pressSound) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock)
				.strength(0.5f)
				.noCollision();
		return new PressurePlateBlock(activationRule, settings, depressSound, pressSound);
	}

	public static PressurePlateBlock pressurePlate(Block baseBlock) {
		return pressurePlate(baseBlock, PressurePlateBlock.ActivationRule.MOBS, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON);
	}

	public static ButtonBlock woodenButton(boolean isNether) {
		return new ButtonBlock(DawnBlockSettings.of(Material.DECORATION)
				.item()
				.strength(0.5f)
				.noCollision()
				.sounds(isNether ? BlockSoundGroup.NETHER_WOOD : BlockSoundGroup.WOOD),
				30, true,
				isNether ? SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_OFF : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF,
				isNether ? SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
	}

	public static FenceBlock fence(boolean isNether, Block baseBlock) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock).item(new DawnItemSettings().fuelTime(isNether ? 0 : 300));
		if(isNether) settings.flammability(5, 20);
		return new FenceBlock(settings);
	}


	public static FenceGateBlock fenceGate(boolean isNether, Block baseBlock, SoundEvent closeSound, SoundEvent openSound) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock).item(new DawnItemSettings().fuelTime(isNether ? 0 : 300));
		if(isNether) settings.flammability(5, 20);
		return new FenceGateBlock(settings, closeSound, openSound);
	}

	public static FenceGateBlock fenceGate(boolean isNether, Block baseBlock) {
		return fenceGate(isNether, baseBlock, SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundEvents.BLOCK_FENCE_GATE_OPEN);
	}

	public static WallBlock wall(Block baseBlock) {
		return new WallBlock(DawnBlockSettings.copy(baseBlock));
	}

	public static TrapdoorBlock trapdoor(Block baseBlock, SoundEvent closeSound, SoundEvent openSound) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock)
				.strength(3.0f)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> false);
		return new TrapdoorBlock(settings, closeSound, openSound);
	}

	public static TrapdoorBlock woodenTrapdoor(Block baseBlock) {
		return trapdoor(baseBlock, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN);
	}

	public static DoorBlock door(Block baseBlock, SoundEvent openSound, SoundEvent closeSound) {
		return new DoorBlock(DawnBlockSettings.copy(baseBlock).strength(3.0f).nonOpaque(), openSound, closeSound);
	}

	public static DoorBlock woodenDoor(Block baseBlock) {
		return door(baseBlock, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE);
	}

	public static SaplingBlock sapling(SaplingGenerator generator) {
		return new SaplingBlock(generator, DawnBlockSettings.of(Material.PLANT)
				.item(new DawnItemSettings().compostingChance(0.3f))
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly()
				.noCollision()
				.ticksRandomly());
	}

	public static DawnSaplingBlock sapling(SaplingGenerator generator, Predicate<BlockState> saplingSoilPredicate) {
		return new DawnSaplingBlock(generator, saplingSoilPredicate, DawnBlockSettings.of(Material.PLANT)
				.item(new DawnItemSettings().compostingChance(0.3f))
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly()
				.noCollision()
				.ticksRandomly());
	}

	public static DawnFungusBlock fungus(RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn) {
		return new DawnFungusBlock(featureKey, canPlantOn, canGrowOn, DawnBlockSettings.of(Material.PLANT)
				.item(new DawnItemSettings().compostingChance(0.65f))
				.sounds(BlockSoundGroup.FUNGUS)
				.breakInstantly()
				.noCollision());
	}

	public static FlowerPotBlock potted(Block content) {
		return new FlowerPotBlock(content, DawnBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque().luminance(content.getDefaultState().getLuminance()));
	}

	public static LeavesBlock leaves() {
		return leaves(BlockSoundGroup.GRASS);
	}

	public static LeavesBlock leaves(BlockSoundGroup soundGroup) {
		return new LeavesBlock(DawnBlockSettings.of(Material.LEAVES)
				.item(new DawnItemSettings().compostingChance(0.3f))
				.strength(0.2f)
				.ticksRandomly()
				.sounds(soundGroup)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
				.suffocates((state, world, pos) -> false)
				.blockVision((state, world, pos) -> false)
				.flammability(30, 60));
	}

	public static SignBlocks signs(Identifier texturePath, Block basePlanks) {
		var signTexture = Identifier.of(texturePath.getNamespace(), "entity/signs/" + texturePath.getPath());
		var hangingSignTexture = Identifier.of(texturePath.getNamespace(), "entity/signs/hanging/" + texturePath.getPath());
		var hangingSignGuiTexture = Identifier.of(texturePath.getNamespace(), "textures/gui/hanging_signs/" + texturePath.getPath());

		var sign = new TerraformSignBlock(signTexture, signSettings(basePlanks));
		var wallSign = new TerraformWallSignBlock(signTexture, signSettings(basePlanks));
		var hangingSign = new TerraformHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, hangingSignSettings(basePlanks));
		var wallHangingSign = new TerraformWallHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, hangingSignSettings(basePlanks));
		var signItem = new SignItem(new DawnItemSettings().maxCount(16), sign, wallSign);
		var hangingSignItem = new HangingSignItem(hangingSign, wallHangingSign, new DawnItemSettings().maxCount(16).requires(FeatureFlags.UPDATE_1_20));

		return new SignBlocks(sign, wallSign, hangingSign, wallHangingSign, signItem, hangingSignItem);
	}

	public static DawnBlockSettings signSettings(Block basePlanks, boolean hanging) {
		var settings = DawnBlockSettings.of(basePlanks.getDefaultState().getMaterial(), basePlanks.getDefaultMapColor()).noCollision().strength(1.0F);
		if(hanging) {
			settings.sounds(BlockSoundGroup.HANGING_SIGN).requires(FeatureFlags.UPDATE_1_20);
		}
		else {
			settings.sounds(BlockSoundGroup.WOOD);
		}
		return settings;
	}

	public static DawnBlockSettings signSettings(Block basePlanks) {
		return signSettings(basePlanks, false);
	}

	public static DawnBlockSettings hangingSignSettings(Block basePlanks) {
		return signSettings(basePlanks, true);
	}

	public static TerraformBoatType boat(Identifier id, ItemConvertible planks, boolean raft) {
		RegistryKey<TerraformBoatType> key = TerraformBoatTypeRegistry.createKey(id);
		var builder = new TerraformBoatType.Builder()
				.item(new TerraformBoatItem(key, false, new Item.Settings().maxCount(1)))
				.chestItem(new TerraformBoatItem(key, true, new Item.Settings().maxCount(1)))
				.planks(planks.asItem());
		if(raft) builder.raft();
		return builder.build();
	}

	public static TerraformBoatType boat(Identifier id, ItemConvertible planks) {
		return boat(id, planks, false);
	}

	public static TerraformBoatType raft(Identifier id, ItemConvertible planks) {
		return boat(id, planks, true);
	}



	/*============*/
	/*   OTHERS   */
	/*============*/

	// TODO: add this to a new Entity Type builder
	public static SpawnEggItem spawnEgg(EntityType<? extends MobEntity> entity, int primaryColor, int secondaryColor) {
		return new SpawnEggItem(entity, primaryColor, secondaryColor, new DawnItemSettings());
	}
}
