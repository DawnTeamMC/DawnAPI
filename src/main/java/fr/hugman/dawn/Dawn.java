package fr.hugman.dawn;

import com.mojang.logging.LogUtils;
import fr.hugman.dawn.registry.DawnCommands;
import fr.hugman.dawn.registry.DawnEntities;
import fr.hugman.dawn.registry.DawnFeatures;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

public class Dawn implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrar REGISTRAR = new Registrar("dawn");

    @Override
    public void onInitialize() {
        DawnRegistries.init(REGISTRAR);

        ShapeType.init(REGISTRAR);
        ShapeProcessorType.init(REGISTRAR);

        DawnCommands.init();
        DawnEntities.init(REGISTRAR);
        DawnFeatures.init(REGISTRAR);
    }

    public static Identifier id(String path) {
        return REGISTRAR.id(path);
    }
}
