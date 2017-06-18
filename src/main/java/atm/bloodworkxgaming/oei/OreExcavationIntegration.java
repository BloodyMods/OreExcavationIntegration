package atm.bloodworkxgaming.oei;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = OreExcavationIntegration.MOD_ID, name = OreExcavationIntegration.MOD_NAME, version = OreExcavationIntegration.VERSION, dependencies = OreExcavationIntegration.DEPENDENCIES)
public class OreExcavationIntegration
{
    public static final String MOD_ID = "oei";
    public static final String VERSION = "1.0";
    public static final String MOD_NAME = "OreExcavationIntegration";
    public static final String DEPENDENCIES = "after:oreexcavation";


    public static final Logger logger = LogManager.getLogger(MOD_ID);


    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        logger.info("Removing the BlockBreak event from OreExcavation");
        MinecraftForge.EVENT_BUS.register(new atm.bloodworkxgaming.oei.Handler.EventHandler());

        GameRegistry.register(new ExcavationEnchantment(), new ResourceLocation("oreexcavation"));

    }
}
