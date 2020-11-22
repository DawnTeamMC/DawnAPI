package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;

public class StructurePieceCreator extends Creator<StructurePieceType> {
	private StructurePieceCreator(ModData modData, String name, StructurePieceType piece) {
		super(modData, name, piece);
	}

	@Override
	public void register() {
		Registry.register(Registry.STRUCTURE_PIECE, modData.id(name), value);
	}

	public static class Builder implements Creator.Builder<StructurePieceType> {
		protected final String name;
		protected final StructurePieceType piece;

		/**
		 * Creates a structure piece.
		 *
		 * @param name  The name of the structure piece.
		 * @param piece The structure piece itself.
		 */
		public Builder(String name, StructurePieceType piece) {
			this.name = name;
			this.piece = piece;
		}

		public StructurePieceCreator build(ModData modData) {
			return new StructurePieceCreator(modData, this.name, this.piece);
		}
	}
}
