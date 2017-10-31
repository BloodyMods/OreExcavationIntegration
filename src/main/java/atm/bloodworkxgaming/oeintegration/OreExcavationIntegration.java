package atm.bloodworkxgaming.oeintegration;

import atm.bloodworkxgaming.oeintegration.Crafting.ModCrafting;
import atm.bloodworkxgaming.oeintegration.Integrations.TiC_Modifiers;
import atm.bloodworkxgaming.oeintegration.Proxy.CommonProxy;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = OreExcavationIntegration.MOD_ID, name = OreExcavationIntegration.MOD_NAME, version = OreExcavationIntegration.VERSION, dependencies = OreExcavationIntegration.DEPENDENCIES, acceptedMinecraftVersions = "[1.12,1.13)")
@Mod.EventBusSubscriber
public class OreExcavationIntegration {
    public static final String MOD_ID = "oeintegration";
    public static final String VERSION = "2.3.2";
    public static final String MOD_NAME = "OreExcavationIntegration";
    public static final String DEPENDENCIES = "after:oreexcavation";
    public static final Logger logger = LogManager.getLogger(MOD_ID);
    @Mod.Instance
    public static OreExcavationIntegration instance;
    @SidedProxy(clientSide = "atm.bloodworkxgaming.oeintegration.Proxy.ClientProxy", serverSide = "atm.bloodworkxgaming.oeintegration.Proxy.ServerProxy")
    public static CommonProxy proxy;

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        proxy.registerModels(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();

        // Tinkers Integration
        if (Loader.isModLoaded("tconstruct")) {
            TiC_Modifiers.register();
        }

        MinecraftForge.EVENT_BUS.register(new atm.bloodworkxgaming.oeintegration.Handler.EventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        ModCrafting.initCrafting();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        proxy.postInit();
    }

}