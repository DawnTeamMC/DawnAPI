package com.hugman.dawn.util.creator;

public abstract class Creator<V> {
	protected V value;
	protected String name;

	protected Creator(String name) {
		this.name = name;
	}

	public abstract V register(CreatorRegister creatorRegister);
	public void clientRegister(CreatorRegister creatorRegister) {};
	public void serverRegister(CreatorRegister creatorRegister) {};

	public String getName() {
		return name;
	}

	public V getValue() {
		return value;
	}
}
