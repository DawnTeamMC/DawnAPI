package com.hugman.dawn.api.creator;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;

public class StructurePieceCreator extends SimpleCreator<StructurePieceType> {
	public StructurePieceCreator(String name, StructurePieceType piece) {
		super(Registry.STRUCTURE_PIECE, name, piece);
	}
}
