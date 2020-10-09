package com.hugman.dawn.api.util.debug.data;

import com.google.gson.annotations.Expose;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleEntryData extends EntryData {
	@Expose
	protected List<Identifier> entries;

	public SimpleEntryData(String namespace, Registry<?> registry, Set<Identifier> set) {
		super(namespace, registry);
		this.entries = new ArrayList<>(set);
	}
}
