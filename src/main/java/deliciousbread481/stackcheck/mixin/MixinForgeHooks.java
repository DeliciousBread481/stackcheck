package deliciousbread481.stackcheck.mixin;  
  
import java.util.Iterator;  
import java.util.Map;  
  
import net.minecraft.world.item.CreativeModeTab;  
import net.minecraft.world.item.ItemStack;  
import net.minecraftforge.common.ForgeHooks;  
import net.minecraftforge.common.util.MutableHashedLinkedMap;  
import net.minecraftforge.registries.ForgeRegistries;  
  
import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  
import org.spongepowered.asm.mixin.Mixin;  
import org.spongepowered.asm.mixin.injection.At;  
import org.spongepowered.asm.mixin.injection.Redirect;  
  
@Mixin(value = ForgeHooks.class, remap = false)  
public abstract class MixinForgeHooks {  
  
    private static final Logger STACKCHECK_LOGGER = LogManager.getLogger("StackCheck");  

    @Redirect(  
        method = "onCreativeModeTabBuildContents",  
        at = @At(  
            value = "INVOKE",  
            target = "Lnet/minecraftforge/common/util/MutableHashedLinkedMap;iterator()Ljava/util/Iterator;"  
        )  
    )  
    private static Iterator<Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> stackcheck$fixStackCounts(  
            MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries) {  
  
        for (Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries) {  
            ItemStack stack = entry.getKey();  
            if (stack.getCount() != 1) {  
                STACKCHECK_LOGGER.warn("[StackCheck] ItemStack with count {} detected! Item: {}. Auto-fixing to 1.",  
                        stack.getCount(),  
                        ForgeRegistries.ITEMS.getKey(stack.getItem()));  
                stack.setCount(1);  
            }  
        }  
  
        return entries.iterator();  
    }  
}