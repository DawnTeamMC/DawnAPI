package com.hugman.dawn.util.creator;

import com.hugman.dawn.util.creator.pack.Pack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public abstract class CreatorRegister<V> {
	public final String modName;
	private final List<Creator<V>> creators = new ArrayList<>();

	public CreatorRegister(String modName) {
		this.modName = modName;
		CreatorHelper.CREATOR_REGISTERS.add(this);
	}

	protected V add(Creator<V> creator) {
		this.creators.add(creator);
		return creator.getValue();
	}

	protected void add(Pack<V> pack) {
		this.creators.addAll(pack.getCreators());
	}

	public List<Creator<V>> getCreators() {
		return creators;
	}

	public Identifier id(String s) {
		return new Identifier(modName, s);
	}
}
