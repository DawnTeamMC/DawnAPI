package com.hugman.dawn.api.creator;

public abstract class Creator<V> {
	protected final String name;
	protected V value;

	protected Creator(String name) {
		this.name = name;
	}

	public abstract V register(ModData modData);

	public void clientRegister() {
	}

	public void serverRegister(boolean isDedicated) {
	}

	public String getName() {
		return name;
	}
}
