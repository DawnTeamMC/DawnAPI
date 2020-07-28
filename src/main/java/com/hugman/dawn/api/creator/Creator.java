package com.hugman.dawn.api.creator;

public abstract class Creator<V> {
	protected final String name;
	protected final ModData modData;
	protected V value;

	protected Creator(ModData modData, String name) {
		this.name = name;
		this.modData = modData;
	}

	public abstract V register();

	public void clientRegister() {
	}

	public void serverRegister(boolean isDedicated) {
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

		public Builder(String name, V value) {
			this.name = name;
			this.value = value;
		}

		public abstract <C extends Creator<V>> C build(ModData modData);
	}
}
