package fr.hugman.dawn;

import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ConfiguredShape;
import fr.hugman.dawn.shape.Shape;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;

public class Registrar {
	public static void add(Identifier id, Block block) {
		Registry.register(Registries.BLOCK, id, block);
	}

	public static void add(Identifier id, AbstractBlock.Settings settings) {
		add(id, new Block(settings));
	}

	public static void add(Identifier id, Item item) {
		Registry.register(Registries.ITEM, id, item);
	}

	public static void add(Identifier id, Item.Settings settings) {
		add(id, new Item(settings));
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

	public static void add(Identifier id, Feature<?> feature) {
		Registry.register(Registries.FEATURE, id, feature);
	}

	public static <S extends Shape> void add(Identifier id, ShapeType<S> shapeType) {
		Registry.register(DawnRegistries.SHAPE_TYPE, id, shapeType);
	}

	public static void add(Identifier id, ShapeProcessorType<?> shapeProcessorType) {
		Registry.register(DawnRegistries.SHAPE_PROCESSOR_TYPE, id, shapeProcessorType);
	}
}
