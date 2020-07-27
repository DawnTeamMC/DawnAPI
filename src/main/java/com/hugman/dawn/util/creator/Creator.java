package com.hugman.dawn.util.creator;

public abstract class Creator<V> {
	protected final String name;
	protected V value;
	protected final ModData modData;

	protected Creator(ModData modData, String name) {
		this.name = name;
		this.modData = modData;
	}

	public abstract V register();

	public void clientRegister() {
	}

	public void serverRegister() {
	}

	public String getName() {
		return name;
	}

	public V getValue() {
		return value;
	}

	public static abstract class Builder<V> {
		protected final String name;
		protected final V value;
		protected ModData modData;

		public Builder(String name, V value) {
			this.name = name;
			this.value = value;
		}

		public Builder<V> setModData(ModData modData) {
			this.modData = modData;
			return this;
		}

		public abstract <C extends Creator<V>> C build();
	}
}
