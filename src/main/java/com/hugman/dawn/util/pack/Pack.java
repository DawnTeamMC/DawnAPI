package com.hugman.dawn.util.pack;

import com.hugman.dawn.util.creator.Creator;

import java.util.List;

public class Pack<V> {
	protected final List<Creator<V>> creators;
	protected final ModData modData;

	protected Pack(ModData modData, List<Creator<V>> creators) {
		this.creators = creators;
		this.modData = modData;
	}

	public List<Creator<V>> getCreators() {
		return creators;
	}

	public static class Builder<V> {
		protected List<Creator<V>> creators;
		protected ModData modData;

		public <C extends Creator<V>> C add(C.Builder<V> creatorBuilder) {
			C creator = creatorBuilder.setModData(modData).build();
			creators.add(creator);
			return creator;
		}

		public <P extends Pack<V>> P add(P.Builder<V> packBuilder) {
			P pack = packBuilder.setModData(modData).build();
			pack.getCreators().forEach(b -> creators.add(b));
			return pack;
		}

		private Builder<V> setModData(ModData modData) {
			this.modData = modData;
			return this;
		}

		public <P extends Pack<V>> P build() {
			return (P) new Pack<>(this.modData, this.creators);
		}
	}
}
