package atm.bloodworkxgaming.oeintegration

import atm.bloodworkxgaming.oeintegration.enchantments.ModEnchantments
import atm.bloodworkxgaming.oeintegration.handler.EventHandler
import atm.bloodworkxgaming.oeintegration.items.ModItems
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.registerConfig

@Mod(OreExcavationIntegration.MOD_ID) //  name = OreExcavationIntegration.MOD_NAME, version = OreExcavationIntegration.VERSION, dependencies = OreExcavationIntegration.DEPENDENCIES, acceptedMinecraftVersions = "[1.12,1.13)"
object OreExcavationIntegration {
    const val MOD_ID = "oeintegration"
    const val VERSION = "2.3.4"
    const val MOD_NAME = "OreExcavationIntegration"
    const val DEPENDENCIES = "required-after:oreexcavation"
    val LOGGER = LogManager.getLogger()

    init {
        LOGGER.info("Hello World from OreExcavationIntegration")

        ModItems.REGISTRY.register(MOD_BUS)
        ModEnchantments.REGISTRY.register(MOD_BUS)

        // MOD_BUS.addListener(atm.bloodworkxgaming.oeintegration.handler.EventHandler::onExcavateEvent)

        registerConfig(ModConfig.Type.COMMON, MainConfig.configSpec)

        FORGE_BUS.register(EventHandler)

        MOD_BUS.addListener(this::onClientSetup)
        FORGE_BUS.addListener(this::onServerAboutToStart)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    fun onServerAboutToStart(event: FMLServerAboutToStartEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }

    /*@atm.bloodworkxgaming.oeintegration.handler.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        proxy.preInit()

        // Tinkers Integration
        if (Loader.isModLoaded("tconstruct")) {
            TiC_Modifiers.register()
        }
        MinecraftForge.EVENT_BUS.register(atm.bloodworkxgaming.oeintegration.handler.EventHandler())
    }

    @atm.bloodworkxgaming.oeintegration.handler.EventHandler
    fun init(event: FMLInitializationEvent?) {
        proxy.init()
        ModCrafting.initCrafting()
    }

    @atm.bloodworkxgaming.oeintegration.handler.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        proxy.postInit()
    }




    @Mod.Instance
    var instance: OreExcavationIntegration? = null

    @SidedProxy(
        clientSide = "atm.bloodworkxgaming.atm.bloodworkxgaming.oeintegration.Proxy.ClientProxy",
        serverSide = "atm.bloodworkxgaming.atm.bloodworkxgaming.oeintegration.Proxy.ServerProxy"
    )
    var proxy: CommonProxy? = null
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent?) {
        proxy.registerModels(event)
    }*/

}
