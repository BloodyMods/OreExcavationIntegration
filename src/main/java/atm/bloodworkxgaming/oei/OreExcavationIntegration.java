package atm.bloodworkxgaming.oei;

import atm.bloodworkxgaming.oei.Integrations.TiC_Modifiers;
import atm.bloodworkxgaming.oei.Proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

    @Mod.Instance
    public static OreExcavationIntegration instance;


    @SidedProxy(clientSide = "atm.bloodworkxgaming.oei.Proxy.ClientProxy", serverSide = "atm.bloodworkxgaming.oei.Proxy.ServerProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger(MOD_ID);



    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();

        TiC_Modifiers.register();

        MinecraftForge.EVENT_BUS.register(new atm.bloodworkxgaming.oei.Handler.EventHandler());

        GameRegistry.register(new ExcavationEnchantment(), new ResourceLocation("oei:oreexcavation"));


    }



    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }

}