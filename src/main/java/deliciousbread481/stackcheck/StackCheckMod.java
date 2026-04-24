package deliciousbread481.stackcheck;  
  
import net.minecraftforge.fml.common.Mod;  
import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  
  
@Mod("stackcheck")  
public class StackCheckMod {  
    public static final Logger LOGGER = LogManager.getLogger("StackCheck");  
  
    public StackCheckMod() {  
        LOGGER.info("[StackCheck] Loaded. Creative tab stack count fix is active.");  
    }  
}