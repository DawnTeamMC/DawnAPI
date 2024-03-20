package fr.hugman.dawn.debug;

import com.google.gson.annotations.Expose;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

public class ItemData {
	@Expose
	protected Identifier name;
	@Expose
	protected Properties properties;

	public ItemData(Identifier id, Item item) {
		this.name = id;
		this.properties = new Properties(item);
	}

	public static class Properties {
		@Expose
		protected int maxCount;
		@Expose
		protected Identifier recipeRemainder;

		public Properties(Item item) {
			this.maxCount = item.getMaxCount();
			this.recipeRemainder = item.getRecipeRemainder() != null ? Registries.ITEM.getId(item.getRecipeRemainder()) : null;
		}
	}

}
