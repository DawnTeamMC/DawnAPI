package com.hugman.dawn.util.creator.pack;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.ModData;

import java.util.ArrayList;
import java.util.List;

public class Pack<V> {
	private final ModData modData;
	private final List<Creator<V>> creators;

	protected Pack(ModData modData, List<Creator<V>> creators) {
		this.modData = modData;
		this.creators = creators;
	}

	public List<Creator<V>> getCreators() {
		return creators;
	}

	public static class Builder<V> {
		protected final List<Creator<V>> creators = new ArrayList<>();
		protected ModData modData;

		public <C extends Creator<V>> C add(C.Builder<V> creatorBuilder) {
			C creator = creatorBuilder.setModData(modData).build();
			creator.register();
			creators.add(creator);
			return creator;
		}

		public <P extends Pack<V>> P.Builder<V> add(P.Builder<V> packBuilder) {
			P pack = packBuilder.setModData(modData).build();
			pack.getCreators().forEach(b -> creators.add(b));
			return packBuilder;
		}

		public Builder<V> setModData(ModData modData) {
			this.modData = modData;
			return this;
		}

		public <P extends Pack<V>> P build() {
			return (P) new Pack<>(this.modData, this.creators);
		}
	}
}
