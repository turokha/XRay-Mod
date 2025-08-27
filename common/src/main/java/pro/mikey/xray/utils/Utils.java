package pro.mikey.xray.utils;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatComponentText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Miscellaneous utility methods for the mod
 */
public class Utils {
    public static IChatComponent safeItemStackName(ItemStack stack) {
        try {
            @Nullable var hoverName = stack.getHoverName();
            if (hoverName != null) {
                return hoverName;
            }

            var displayName = stack.getDisplayName();
            if (displayName != null) {
                return displayName;
            }

            return new ChatComponentTranslation(stack.getItem().getDescriptionId());
        } catch (Exception e) {
            return new ChatComponentText("Unknown...");
        }
    }
}
