package mod.azure.wowweapons.config;

import java.io.File;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import mod.azure.wowweapons.WoWWeaponsMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class Config {

	public static final ServerConfig SERVER;
	public static final ForgeConfigSpec SERVER_SPEC;

	static {
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		SERVER_SPEC = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	public static class ServerConfig {
		public final BooleanValue USE_COMPATIBILITY_ON_ITEMS;
		public final BooleanValue USE_CHESTLOOTSYSTEM;
		public final BooleanValue USE_MINESLASHLOOTSYSTEM;
		public ConfigValue<Integer> SWORD_MAXDAMAGE;
		public ConfigValue<Integer> STAFF_MAXDAMAGE;
		public ConfigValue<Integer> BOW_MAXDAMAGE;

		ServerConfig(ForgeConfigSpec.Builder builder) {
			builder.push("general");
			USE_COMPATIBILITY_ON_ITEMS = builder.comment("Mine and Slash Compatibility")
					.translation(WoWWeaponsMod.MODID + ".config.use_compatibility_on_items")
					.define("USE_COMPATIBILITY_ON_ITEMS", true);
			USE_CHESTLOOTSYSTEM = builder.comment("Add loot to Chest loot system")
					.translation(WoWWeaponsMod.MODID + ".config.use_chestlootsystem")
					.define("USE_CHESTLOOTSYSTEM", true);
			USE_MINESLASHLOOTSYSTEM = builder.comment("Add loot to Mine and Slash Loot System")
					.translation(WoWWeaponsMod.MODID + ".config.use_mineslashlootsystem")
					.define("USE_MINESLASHLOOTSYSTEM", true);
			builder.pop();
			builder.push("gear");
			STAFF_MAXDAMAGE = builder.comment("Staff Max Damage")
					.translation(WoWWeaponsMod.MODID + ".config.staff_maxdamage").define("STAFF_MAXDAMAGE", 1000);
			SWORD_MAXDAMAGE = builder.comment("Sword Max Damage")
					.translation(WoWWeaponsMod.MODID + ".config.sword_maxdamage").define("SWORD_MAXDAMAGE", 1000);
			BOW_MAXDAMAGE = builder.comment("Bow Max Damage").translation(WoWWeaponsMod.MODID + ".config.bow_maxdamage")
					.define("BOW_MAXDAMAGE", 1000);
			builder.pop();
		}
	}

	public static void loadConfig(ForgeConfigSpec config, String path) {
		WoWWeaponsMod.LOGGER.info("Loading config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();
		WoWWeaponsMod.LOGGER.info("Built config: " + path);
		file.load();
		WoWWeaponsMod.LOGGER.info("Loaded config: " + path);
		config.setConfig(file);
	}
}