package pro.mikey.xray;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.Minecraft;
import pro.mikey.xray.core.ScanController;
import pro.mikey.xray.core.OutlineRender;

@Mod(modid = XRay.MOD_ID)
public class XRayForge1710 {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        XRay.INSTANCE.init();
        ClientRegistry.registerKeyBinding(XRay.TOGGLE_KEY);
        ClientRegistry.registerKeyBinding(XRay.OPEN_GUI_KEY);
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.thePlayer != null && mc.theWorld != null) {
                ScanController.INSTANCE.requestBlockFinder(false);
            }
        }
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        OutlineRender.renderBlocks(null);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || mc.currentScreen != null || mc.theWorld == null) {
            return;
        }
        while (XRay.TOGGLE_KEY.isPressed()) {
            XRay.INSTANCE.onToggleKeyPressed();
        }
        while (XRay.OPEN_GUI_KEY.isPressed()) {
            XRay.INSTANCE.onOpenGuiKeyPressed();
        }
    }
}
