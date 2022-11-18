package fr.hugman.dawn.debug;

import com.google.gson.annotations.Expose;
import fr.hugman.dawn.mixin.ItemAccessor;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
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
		protected Rarity rarity;
		@Expose
		protected int maxCount;
		@Expose
		protected int maxDamage;
		@Expose
		protected boolean fireproof;
		@Expose
		protected Identifier recipeRemainder;

		public Properties(Item item) {
			ItemAccessor accessor = (ItemAccessor) item;
			this.rarity = accessor.getRarity();
			this.maxCount = item.getMaxCount();
			this.maxDamage = item.getMaxDamage();
			this.fireproof = item.isFireproof();
			this.recipeRemainder = item.getRecipeRemainder() != null ? Registries.ITEM.getId(item.getRecipeRemainder()) : null;
		}
	}

}
