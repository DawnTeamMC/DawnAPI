package fr.hugman.dawn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import fr.hugman.dawn.debug.DataList;
import fr.hugman.dawn.debug.DataSerialization;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExportCommand {
	private static final String REGISTRY_ENTRIES_DIRECTORY = "registry_entries";
	private static final String DYNAMIC_CONTENT_DIRECTORY = "dynamic_content";

	public static final int PERMISSION_LEVEL = 3;

	public static final String NAME = "export";
	public static final String REGISTRIES_ARG = "registries";
	public static final String EXPANDED_ARG = "expanded";
	public static final String DYNAMIC_ARG = "dynamic";
	public static final String BUILTIN_ARG = "builtin";

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal(NAME)
				.requires(sc -> sc.hasPermissionLevel(PERMISSION_LEVEL))
				.then(CommandManager.literal(REGISTRIES_ARG)
						.executes(cc -> exportRegistries(cc.getSource(), false))
						.then(CommandManager.argument(EXPANDED_ARG, BoolArgumentType.bool())
								.executes(cc -> exportRegistries(cc.getSource(), BoolArgumentType.getBool(cc, EXPANDED_ARG)))))
				.then(CommandManager.literal(DYNAMIC_ARG)
						.executes(cc -> exportDynamic(cc.getSource(), false))
						.then(CommandManager.argument(BUILTIN_ARG, BoolArgumentType.bool())
								.executes(cc -> exportDynamic(cc.getSource(), BoolArgumentType.getBool(cc, BUILTIN_ARG))))));
	}

	public static int exportRegistries(ServerCommandSource source, boolean expanded) {
		Path exportPath = getExportFolder(source, REGISTRY_ENTRIES_DIRECTORY);
		Text exportFileComponent = getFileComponent(exportPath);

		if(exportPath.toFile().exists()) {
			source.sendError(Text.translatable("commands." + NAME + ".fail.already_exists", exportFileComponent));
			return 0;
		}
		source.sendFeedback(Text.translatable("commands." + NAME + ".start"), true);

		try {
			for(Registry<?> registry : Registries.REGISTRIES) {
				exportRegistry(registry, expanded, exportPath);
			}
			// TODO: where are built-in registries?
			source.sendFeedback(Text.translatable("commands." + NAME + ".success", exportFileComponent), true);
		} catch(IOException e) {
			source.sendError(Text.translatable("commands." + NAME + ".fail.unknown"));
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public static int exportDynamic(ServerCommandSource source, boolean builtin) {
		if(!source.getServer().isSingleplayer() && source.getServer().getCurrentPlayerCount() > 1) {
			source.sendError(Text.translatable("commands." + NAME + "." + DYNAMIC_ARG + ".fail.multiplayer"));
			return 0;
		}

		Path exportPath = getExportFolder(source, DYNAMIC_CONTENT_DIRECTORY);
		Text exportFileComponent = getFileComponent(exportPath);

		if(exportPath.toFile().exists()) {
			source.sendError(Text.translatable("commands." + NAME + ".fail.already_exists", exportFileComponent));
			return 0;
		}
		source.sendFeedback(Text.translatable("commands." + NAME + ".start"), true);

		try {
			throw new UnsupportedOperationException("Not implemented yet");

			//generateDynamicFiles(builtin, source, exportPath);
			//source.sendFeedback(Text.translatable("commands." + NAME + ".success", exportFileComponent), true);
			//return 1;
		} catch(Exception e) {
			source.sendError(Text.translatable("commands." + NAME + ".fail.unknown"));
			e.printStackTrace();
			return 0;
		}
	}

	public static <T> void exportRegistry(Registry<T> registry, boolean expanded, Path exportPath) throws IOException {
		Identifier registryName = registry.getKey().getValue();
		String[] modIds = registry.getIds().stream().map(Identifier::getNamespace).distinct().toArray(String[]::new);

		for(String modId : modIds) {
			Path path = exportPath
					.resolve(modId)
					.resolve(registryName.getNamespace())
					.resolve(registryName.getPath() + ".json");
			Files.createDirectories(path.getParent());

			Set<Map.Entry<RegistryKey<T>, T>> entries =
					registry.getEntrySet().stream()
							.filter(entry -> entry.getKey().getValue().getNamespace().equals(modId))
							.collect(Collectors.toSet());

			DataList<?> dataList = new DataList<>(entries.stream().map(DataSerialization.getMapperFromRegistry(expanded ? registry : null)).toList());
			DataSerialization.saveToFile(path.toFile(), dataList.getClass(), dataList);
		}
	}

	/*

	private static void generateDynamicFiles(boolean builtin, ServerCommandSource source, Path exportPath) throws IOException {
		Files.createDirectories(exportPath);
		DataWriter writer = new DataCache.CachedDataWriter("", SharedConstants.getGameVersion().getName(), DataCache.parseOrCreateCache(exportPath, exportPath.resolve("reports")));
		DynamicRegistryManager manager = builtin ? DynamicRegistryManager.createAndLoad() : source.getWorld().getRegistryManager();

		DynamicOps<JsonElement> ops = RegistryOps.of(JsonOps.INSTANCE, manager);

		manager.streamAllRegistries().forEach(entry -> dumpRegistryCap(exportPath, writer, manager, ops, entry));
	}

	private static <T> void dumpRegistryCap(Path exportPath, DataWriter writer, DynamicRegistryManager manager, DynamicOps<JsonElement> ops, DynamicRegistryManager.Entry<T> entry) {
		RegistryKey<? extends Registry<T>> registryKey = entry.key();
		Registry<T> registry = manager.get(registryKey);

		for(Map.Entry<RegistryKey<T>, T> entry2 : registry.getEntrySet()) {
			RegistryKey<T> key = entry2.getKey();
			Path path = exportPath
					.resolve(key.getValue().getNamespace())
					.resolve(registryKey.getValue().getNamespace())
					.resolve(registryKey.getValue().getPath())
					.resolve(key.getValue().getPath() + ".json");
			DynamicRegistriesProvider.writeToPath(path, writer, ops, entry.entryCodec(), entry2.getValue());
		}
	}
	 */

	public static Path getExportFolder(ServerCommandSource source, String subFolderName) {
		return FabricLoader.getInstance().getGameDir().resolve("debug").resolve("export").resolve(subFolderName);
	}

	public static Text getFileComponent(Path path) {
		return Text.literal(path.toString())
				.formatted(Formatting.BLUE, Formatting.UNDERLINE)
				.styled(text -> text
						.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, path.toString()))
						.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("chat.fileExplorer.click"))));
	}
}