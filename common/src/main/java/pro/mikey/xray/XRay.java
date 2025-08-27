package pro.mikey.xray;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.ClientRegistry;
import pro.mikey.xray.screens.ScanManageScreen;
import pro.mikey.xray.utils.XPlatShim;
import pro.mikey.xray.core.ScanController;

import java.util.ServiceLoader;

public enum XRay {
	INSTANCE;

	public static final String MOD_ID = "xray";
	private static final Logger LOGGER = LogManager.getLogger();

        public static final XPlatShim XPLAT = ServiceLoader.load(XPlatShim.class).findFirst().orElseThrow();

        public static final KeyBinding TOGGLE_KEY = new KeyBinding(I18n.get("xray.config.toggle"), Keyboard.KEY_BACKSLASH, "xray.mod_name");
        public static final KeyBinding OPEN_GUI_KEY = new KeyBinding(I18n.get("xray.config.open"), Keyboard.KEY_G, "xray.mod_name");

        public void init() {
                ClientRegistry.registerKeyBinding(TOGGLE_KEY);
                ClientRegistry.registerKeyBinding(OPEN_GUI_KEY);
        }

	public void onToggleKeyPressed() {
		if (minecraftNotReady()) {
			LOGGER.warn("Cannot toggle X-Ray, Minecraft is not ready.");
			return;
		}

		ScanController.INSTANCE.toggleXRay();
	}

	public void onOpenGuiKeyPressed() {
		if (minecraftNotReady()) {
			LOGGER.warn("Cannot open GUI, Minecraft is not ready.");
			return;
		}

		Minecraft.getInstance().setScreen(new ScanManageScreen());
	}

	private boolean minecraftNotReady() {
		Minecraft mc = Minecraft.getInstance();

		return mc.player == null || Minecraft.getInstance().screen != null || Minecraft.getInstance().level == null;
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public static ResourceLocation assetLocation(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/" + path);
	}

	public static Configuration config() {
		return Configuration.INSTANCE;
	}
}
