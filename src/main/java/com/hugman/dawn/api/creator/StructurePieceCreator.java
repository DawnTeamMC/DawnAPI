package com.hugman.dawn.api.creator;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a structure piece type to be created.
 */
public class StructurePieceCreator extends SimpleCreator<StructurePieceType> {
	/**
	 * Creates a structure piece type.
	 *
	 * @param name  the name of the structure piece type
	 * @param piece the structure piece type itself
	 */
	public StructurePieceCreator(String name, StructurePieceType piece) {
		super(Registry.STRUCTURE_PIECE, name, piece);
	}

	/**
	 * Creates a structure piece type.
	 *
	 * @param name  the name of the structure piece type
	 * @param piece the structure piece type itself
	 */
	public StructurePieceCreator(String name, StructurePieceType.Simple piece) {
		this(name, (StructurePieceType) piece);
	}

	/**
	 * Creates a structure piece type.
	 *
	 * @param name  the name of the structure piece type
	 * @param piece the structure piece type itself
	 */
	public StructurePieceCreator(String name, StructurePieceType.ManagerAware piece) {
		this(name, (StructurePieceType) piece);
	}
}
