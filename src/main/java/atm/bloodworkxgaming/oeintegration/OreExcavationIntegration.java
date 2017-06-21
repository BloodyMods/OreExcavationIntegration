package atm.bloodworkxgaming.oeintegration;

import atm.bloodworkxgaming.oeintegration.Crafting.ModCrafting;
import atm.bloodworkxgaming.oeintegration.Integrations.TiC_Modifiers;
import atm.bloodworkxgaming.oeintegration.Proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = OreExcavationIntegration.MOD_ID, name = OreExcavationIntegration.MOD_NAME, version = OreExcavationIntegration.VERSION, dependencies = OreExcavationIntegration.DEPENDENCIES)
public class OreExcavationIntegration
{
    public static final String MOD_ID = "oeintegration";
    public static final String VERSION = "1.0";
    public static final String MOD_NAME = "OreExcavationIntegration";
    public static final String DEPENDENCIES = "after:oreexcavation";

    @Mod.Instance
    public static OreExcavationIntegration instance;


    @SidedProxy(clientSide = "atm.bloodworkxgaming.oeintegration.Proxy.ClientProxy", serverSide = "atm.bloodworkxgaming.oeintegration.Proxy.ServerProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger(MOD_ID);



    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();

        // Tinkers Integration
        if (Loader.isModLoaded("tconstruct")){
            TiC_Modifiers.register();
        }

        ModEnchantments.registerEnchantments();
        MinecraftForge.EVENT_BUS.register(new atm.bloodworkxgaming.oeintegration.Handler.EventHandler());
    }



    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
        ModCrafting.initCrafting();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        proxy.postInit();
    }

}