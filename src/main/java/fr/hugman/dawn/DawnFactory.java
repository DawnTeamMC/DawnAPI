package fr.hugman.dawn;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import fr.hugman.dawn.block.DawnFungusBlock;
import fr.hugman.dawn.block.DawnSaplingBlock;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.shape.Shape;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessor;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
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

/**
 * A factory for creating all sorts of features in the game.
 *
 * @author Hugman
 * @see Registrar
 * @since 4.0.0
 */
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

	public static <P extends TrunkPlacer> TrunkPlacerType<P> trunkPlacer(Codec<P> codec) {
		return new TrunkPlacerType<>(codec);
	}

	public static <P extends FoliagePlacer> FoliagePlacerType<P> foliagePlacer(Codec<P> codec) {
		return new FoliagePlacerType<>(codec);
	}

	public static TagKey<Block> blockTag(Identifier id) {
		return TagKey.of(RegistryKeys.BLOCK, id);
	}

	public static TagKey<Item> itemTag(Identifier id) {
		return TagKey.of(RegistryKeys.ITEM, id);
	}

	public static TagKey<Biome> biomeTag(Identifier id) {
		return TagKey.of(RegistryKeys.BIOME, id);
	}

	public static TagKey<Fluid> fluidTag(Identifier id) {
		return TagKey.of(RegistryKeys.FLUID, id);
	}



	/*==========*/
	/*   WOOD   */
	/*==========*/

	public static Block planks(MapColor color, BlockSoundGroup sounds, boolean flammable) {
		return new Block(DawnFactory.planksSettings(color, sounds, flammable));
	}

	public static AbstractBlock.Settings planksSettings(MapColor color, BlockSoundGroup sounds, boolean flammable) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.create()
				.mapColor(color)
				.instrument(Instrument.BASS)
				.item()
				.strength(2.0f, 3.0f)
				.sounds(sounds);
		if(flammable) settings.burnable(5, 20);
		return settings;
	}

	public static AbstractBlock.Settings logSettings(MapColor woodColor, MapColor barkColor, BlockSoundGroup sounds, boolean flammable) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.create()
				.mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor)
				.instrument(Instrument.BASS)
				.item()
				.strength(2.0F)
				.sounds(sounds);
		if(flammable) settings.burnable(5, 5);
		return settings;
	}

	public static AbstractBlock.Settings logSettings(MapColor color, BlockSoundGroup sounds, boolean flammable) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.create()
				.mapColor(color)
				.instrument(Instrument.BASS)
				.item()
				.strength(2.0F)
				.sounds(sounds);
		if(flammable) settings.burnable(5, 5);
		return settings;
	}

	public static StairsBlock stairs(Block baseBlock) {
		return new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock));
	}

	public static SlabBlock slab(Block baseBlock) {
		return new SlabBlock(AbstractBlock.Settings.copy(baseBlock));
	}

	public static PressurePlateBlock pressurePlate(Block baseBlock, BlockSetType setType) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(baseBlock)
				.strength(0.5f)
				.pistonBehavior(PistonBehavior.DESTROY)
				.requiresTool()
				.noCollision();
		return new PressurePlateBlock(setType, settings);
	}

	public static ButtonBlock woodenButton(Block baseBlock, BlockSetType setType) {
		return new ButtonBlock(setType, 30, AbstractBlock.Settings.create()
                .item()
                .strength(0.5f)
                .noCollision()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(baseBlock.getDefaultState().getSoundGroup()));
	}

	public static FenceBlock fence(Block baseBlock) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(baseBlock);
		return new FenceBlock(settings
				.solid()
				.item(new Item.Settings().fuelTime(settings.getFlameBurn() > 0 ? 300 : 0))
		);
	}

	public static FenceGateBlock fenceGate(Block baseBlock, WoodType woodType) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(baseBlock);
		return new FenceGateBlock(woodType, settings.solid()
                        .item(new Item.Settings().fuelTime(settings.getFlameBurn() > 0 ? 300 : 0)));
	}

	public static WallBlock wall(Block baseBlock) {
		return new WallBlock(AbstractBlock.Settings.copy(baseBlock).solid());
	}

	public static TrapdoorBlock trapdoor(Block baseBlock, BlockSetType setType) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(baseBlock)
				.strength(3.0f)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> false);
		return new TrapdoorBlock(setType, settings);
	}

	public static DoorBlock door(Block baseBlock, BlockSetType setType) {
		return new DoorBlock(setType, AbstractBlock.Settings.copy(baseBlock)
                        .strength(3.0f)
                        .nonOpaque()
                        .pistonBehavior(PistonBehavior.DESTROY));
	}

	public static SaplingBlock sapling(MapColor mapColor, SaplingGenerator generator) {
		return new SaplingBlock(generator, AbstractBlock.Settings.create()
				.item(new Item.Settings().compostingChance(0.3f))
				.mapColor(mapColor)
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly()
				.noCollision()
				.ticksRandomly()
				.pistonBehavior(PistonBehavior.DESTROY));
	}

	public static DawnSaplingBlock sapling(MapColor mapColor, SaplingGenerator generator, Predicate<BlockState> saplingSoilPredicate) {
		return new DawnSaplingBlock(generator, saplingSoilPredicate, AbstractBlock.Settings.create()
				.item(new Item.Settings().compostingChance(0.3f))
				.mapColor(mapColor)
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly()
				.noCollision()
				.ticksRandomly()
				.pistonBehavior(PistonBehavior.DESTROY));
	}

	public static DawnFungusBlock fungus(MapColor mapColor, RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn) {
		return new DawnFungusBlock(featureKey, canPlantOn, canGrowOn, AbstractBlock.Settings.create()
				.item(new Item.Settings().compostingChance(0.65f))
				.mapColor(mapColor)
				.sounds(BlockSoundGroup.FUNGUS)
				.pistonBehavior(PistonBehavior.DESTROY)
				.breakInstantly()
				.noCollision());
	}

	public static FlowerPotBlock potted(Block content) {
		return new FlowerPotBlock(content, AbstractBlock.Settings.create()
				.breakInstantly()
				.nonOpaque()
				.luminance(state -> content.getDefaultState().getLuminance())
				.pistonBehavior(PistonBehavior.DESTROY));
	}

	public static LeavesBlock leaves(MapColor mapColor) {
		return leaves(mapColor, BlockSoundGroup.GRASS);
	}

	public static LeavesBlock leaves(MapColor mapColor, BlockSoundGroup soundGroup) {
		return new LeavesBlock(leavesSettings(mapColor, soundGroup));
	}

	public static AbstractBlock.Settings leavesSettings(MapColor mapColor, BlockSoundGroup soundGroup) {
		return AbstractBlock.Settings.create()
				.item(new Item.Settings().compostingChance(0.3f))
				.mapColor(mapColor)
				.strength(0.2f)
				.ticksRandomly()
				.sounds(soundGroup)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
				.suffocates((state, world, pos) -> false)
				.blockVision((state, world, pos) -> false)
				.burnable(30, 60)
				.pistonBehavior(PistonBehavior.DESTROY)
				.solidBlock((state, world, pos) -> false);
	}

	public static SignBlocks signs(Identifier texturePath, Block basePlanks) {
		BlockSoundGroup soundGroup = basePlanks.getDefaultState().getSoundGroup();
		BlockSoundGroup hangingSoundGroup = BlockSoundGroup.WOOD;
		if(soundGroup == BlockSoundGroup.CHERRY_WOOD) {
			hangingSoundGroup = BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN;
		}
		else if(soundGroup == BlockSoundGroup.BAMBOO_WOOD) {
			hangingSoundGroup = BlockSoundGroup.BAMBOO_WOOD_HANGING_SIGN;
		}
		else if(soundGroup == BlockSoundGroup.NETHER_WOOD) {
			hangingSoundGroup = BlockSoundGroup.NETHER_WOOD_HANGING_SIGN;
		}

		return signs(texturePath, basePlanks, soundGroup, hangingSoundGroup);
	}

	public static SignBlocks signs(Identifier texturePath, Block basePlanks, BlockSoundGroup normalSounds, BlockSoundGroup hangingSounds) {
		var signTexture = Identifier.of(texturePath.getNamespace(), "entity/signs/" + texturePath.getPath());
		var hangingSignTexture = Identifier.of(texturePath.getNamespace(), "entity/signs/hanging/" + texturePath.getPath());
		var hangingSignGuiTexture = Identifier.of(texturePath.getNamespace(), "textures/gui/hanging_signs/" + texturePath.getPath());

		var sign = new TerraformSignBlock(signTexture, signSettings(basePlanks, normalSounds));
		var wallSign = new TerraformWallSignBlock(signTexture, signSettings(basePlanks, normalSounds));
		var hangingSign = new TerraformHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, signSettings(basePlanks, hangingSounds));
		var wallHangingSign = new TerraformWallHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, signSettings(basePlanks, hangingSounds));
		var signItem = new SignItem(new Item.Settings().maxCount(16), sign, wallSign);
		var hangingSignItem = new HangingSignItem(hangingSign, wallHangingSign, new Item.Settings().maxCount(16));

		return new SignBlocks(sign, wallSign, hangingSign, wallHangingSign, signItem, hangingSignItem);
	}

	public static AbstractBlock.Settings signSettings(Block basePlanks, BlockSoundGroup soundGroup) {
		return AbstractBlock.Settings.create()
				.sounds(soundGroup)
				.mapColor(basePlanks.getDefaultMapColor())
				.solid()
				.instrument(Instrument.BASS)
				.noCollision()
				.strength(1.0F)
				.burnable();
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
		return new SpawnEggItem(entity, primaryColor, secondaryColor, new Item.Settings());
	}
}
