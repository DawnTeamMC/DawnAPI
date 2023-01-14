package fr.hugman.dawn;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.registry.ReloadableResourceManager;
import fr.hugman.dawn.shape.Shape;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

/**
 * This class is used to register all sorts of features in the game.
 * It can also be used as an instance to fasten the registration process by taking care of the mod ID once and then passing the object around to use it.
 *
 * @author Hugman
 * @see DawnFactory
 * @since 4.0.0
 */
public record Registrar(String modId) {
	/*==================*/
	/*  STATIC METHODS  */
	/*==================*/

	public static void add(Identifier id, Block block) {
		Registry.register(Registries.BLOCK, id, block);
	}

	public static void add(Identifier id, Item item) {
		Registry.register(Registries.ITEM, id, item);
	}

	public static void add(SoundEvent soundEvent) {
		Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent);
	}

	public static void add(Identifier id, DefaultParticleType particleType) {
		Registry.register(Registries.PARTICLE_TYPE, id, particleType);
	}

	public static <T extends Entity> void add(Identifier id, EntityType<T> type) {
		Registry.register(Registries.ENTITY_TYPE, id, type);
	}

	public static <T extends BlockEntity> void add(Identifier id, BlockEntityType<T> blockEntity) {
		Registry.register(Registries.BLOCK_ENTITY_TYPE, id, blockEntity);
	}

	public static void add(Identifier id, Feature<?> feature) {
		Registry.register(Registries.FEATURE, id, feature);
	}

	public static <S extends Shape> void add(Identifier id, ShapeType<S> shapeType) {
		Registry.register(DawnRegistries.SHAPE_TYPE, id, shapeType);
	}

	public static void add(Identifier id, ShapeProcessorType<?> shapeProcessorType) {
		Registry.register(DawnRegistries.SHAPE_PROCESSOR_TYPE, id, shapeProcessorType);
	}

	public static void add(Identifier id, TrunkPlacerType<?> trunkPlacerType) {
		Registry.register(Registries.TRUNK_PLACER_TYPE, id, trunkPlacerType);
	}

	public static void add(Identifier id, FoliagePlacerType<?> foliagePlacerType) {
		Registry.register(Registries.FOLIAGE_PLACER_TYPE, id, foliagePlacerType);
	}

	public static void add(Identifier id, PlacementModifierType<?> placementModifierType) {
		Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, id, placementModifierType);
	}

	public static void add(Identifier id, SignBlocks signs) {
		if(id != null) {
			Registrar.add(Identifier.of(id.getNamespace(), id.getPath() + "_sign"), signs.sign());
			Registrar.add(Identifier.of(id.getNamespace(), id.getPath() + "_wall_sign"), signs.wallSign());
			Registrar.add(Identifier.of(id.getNamespace(), id.getPath() + "_hanging_sign"), signs.hangingSign());
			Registrar.add(Identifier.of(id.getNamespace(), id.getPath() + "_wall_hanging_sign"), signs.wallHangingSign());

			Registrar.add(Identifier.of(id.getNamespace(), id.getPath() + "_sign"), signs.signItem());
			Registrar.add(Identifier.of(id.getNamespace(), id.getPath() + "_hanging_sign"), signs.hangingSignItem());
		}
		else {
			throw new InvalidIdentifierException("The identifier cannot be null.");
		}
	}

	public static void add(Identifier id, TerraformBoatType boatType) {
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, id, boatType);
		var opt = TerraformBoatTypeRegistry.INSTANCE.getKey(boatType);
		if(opt.isEmpty()) throw new RuntimeException("Failed to retrieve boat type " + id + " from the registry. That should actually never happen. WTF is going on?");
		else {
			RegistryKey<TerraformBoatType> key = opt.get();
			Item boatItem = boatType.getItem();
			Item chestBoatItem = boatType.getChestItem();

			if(boatItem != null) {
				Identifier itemId = new Identifier(key.getValue().getNamespace(), key.getValue().getPath() + (boatType.isRaft() ? "_raft" : "_boat"));
				Registrar.add(itemId, boatItem);
				TerraformBoatItemHelper.registerBoatDispenserBehavior(boatItem, key, false);
			}
			if(chestBoatItem != null) {
				Identifier itemId = new Identifier(key.getValue().getNamespace(), key.getValue().getPath() + (boatType.isRaft() ? "_chest_raft" : "_chest_boat"));
				Registrar.add(itemId, chestBoatItem);
				TerraformBoatItemHelper.registerBoatDispenserBehavior(chestBoatItem, key, true);
			}
		}
	}

	public static void add(Identifier id, ReloadableResourceManager<?> reloadableResourceManager) {
		reloadableResourceManager.register(id);
	}

	/*====================*/
	/*  INSTANCE METHODS  */
	/*====================*/

	public Identifier id(String path) {
		return Identifier.of(this.modId, path);
	}

	public void add(String name, Block block) {
		add(Identifier.of(this.modId, name), block);
	}

	public void add(String name, Item item) {
		add(Identifier.of(this.modId, name), item);
	}

	public void add(String name, DefaultParticleType particleType) {
		add(Identifier.of(this.modId, name), particleType);
	}

	public <T extends Entity> void add(String name, EntityType<T> type) {
		add(Identifier.of(this.modId, name), type);
	}

	public <T extends BlockEntity> void add(String name, BlockEntityType<T> blockEntity) {
		add(Identifier.of(this.modId, name), blockEntity);
	}

	public void add(String name, Feature<?> feature) {
		add(Identifier.of(this.modId, name), feature);
	}

	public <S extends Shape> void add(String name, ShapeType<S> shapeType) {
		add(Identifier.of(this.modId, name), shapeType);
	}

	public void add(String name, ShapeProcessorType<?> shapeProcessorType) {
		add(Identifier.of(this.modId, name), shapeProcessorType);
	}

	public void add(String name, TrunkPlacerType<?> trunkPlacerType) {
		add(Identifier.of(this.modId, name), trunkPlacerType);
	}

	public void add(String name, FoliagePlacerType<?> foliagePlacerType) {
		add(Identifier.of(this.modId, name), foliagePlacerType);
	}

	public void add(String name, PlacementModifierType<?> placementModifierType) {
		add(Identifier.of(this.modId, name), placementModifierType);
	}

	public void add(String name, SignBlocks signs) {
		add(Identifier.of(this.modId, name), signs);
	}

	public void add(String name, TerraformBoatType boatType) {
		add(Identifier.of(this.modId, name), boatType);
	}

	public void add(String name, ReloadableResourceManager<?> reloadableResourceManager) {
		add(Identifier.of(this.modId, name), reloadableResourceManager);
	}
}
