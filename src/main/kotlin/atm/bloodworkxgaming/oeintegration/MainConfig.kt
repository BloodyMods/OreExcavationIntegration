package atm.bloodworkxgaming.oeintegration

import net.minecraftforge.common.ForgeConfigSpec

/**
 * Created by Jonas on 09.05.2017.
 */
// @Config(modid = OreExcavationIntegration.MOD_ID)
object MainConfig {
    val disableMod: ForgeConfigSpec.BooleanValue

    val disableChatNotification: ForgeConfigSpec.BooleanValue


    val packModesWhitelistToBlacklistToggle: ForgeConfigSpec.BooleanValue

    val packModesWhitelist: ForgeConfigSpec.ConfigValue<List<String>>

    val toolWhitelistOverride: ForgeConfigSpec.ConfigValue<List<String>>

    val enchantmentWhitelist: ForgeConfigSpec.ConfigValue<List<String>>

    val enchantmentBlacklist: ForgeConfigSpec.ConfigValue<List<String>>

    val allowMendingEnchantment: ForgeConfigSpec.BooleanValue


    val allowMendingTinkers: ForgeConfigSpec.BooleanValue

    val maxTinkersModifersCount: ForgeConfigSpec.IntValue


    val maxEnchantmentLevel: ForgeConfigSpec.IntValue


    /*
        @Config.Comment("This will disable the chat notification for all Players on the server")
        var disableChatNotification = false

        @Config.Comment("Setting this to true toggels the whitelist to a blacklist")
        var packModesWhitelistToBlacklistToggle = false

        @Config.Comment("If the current packmode is in this list then you will be able to mine without the modifier")
        var packModesWhitelist = arrayOf<String>()

        @Config.Comment("A tool in this list won't require the enchantment")
        var toolWhitelistOverride = arrayOf<String>()

        @Config.Comment("A tool in this list will be allowed to get the enchantment as well as any tool")
        var enchantmentWhitelist = arrayOf(
            "minecraft:shears"
        )

        @Config.Comment("This will take away the ability to enchant any item that is a tool")
        var enchantmentBlacklist = arrayOf<String>()

        @Config.Comment("true allows Mending and Excavate to be on the same Tool with Enchantments")
        var allowMendingEnchantment = true

        @Config.Comment("true allows Mending and Excavate to be on the same Tool with Tinkers")
        var allowMendingTinkers = true

        @Config.Comment("The amount of tinkers modifers needed to reach full power.")
        var maxTinkersModifersCount = 25

        @Config.Comment("The max level of enchantment needed to full power of the tool.")
        var maxEnchantmentLevel = 5
    */
    val configSpec: ForgeConfigSpec = ForgeConfigSpec.Builder().apply {
        comment(
            "Setting this config to true will fully disable the mod and you can use OreExcavation normally.",
            "This is better than ripping out the JAR from the mods folder"
        )
        disableMod = define("disableMod", false)

        comment("This will disable the chat notification for all Players on the server")
        disableChatNotification = define("disableChatNotification", false)

        comment("Setting this to true toggles the packModes whitelist to a blacklist")
        packModesWhitelistToBlacklistToggle = define("packModesWhitelistToBlacklistToggle", false)

        comment("If the current packmode is in this list then you will be able to mine without the modifier")
        packModesWhitelist = defineList("packModesWhitelist", emptyList()) { true }

        comment("A tool in this list won't require the enchantment")
        toolWhitelistOverride = defineList("toolWhitelistOverride", emptyList()) { true }

        comment("A tool in this list will be allowed to get the enchantment (in addition to any tool)")
        enchantmentWhitelist = defineList("enchantmentWhitelist", listOf("minecraft:shears")) { true }

        comment("This will take away the ability to enchant any item that is a tool")
        enchantmentBlacklist = defineList("enchantmentBlacklist", emptyList()) { true }

        comment("true allows Mending and Excavate to be on the same Tool with Enchantments")
        allowMendingEnchantment = define("allowMendingEnchantment", true)

        comment("true allows Mending and Excavate to be on the same Tool with Tinkers")
        allowMendingTinkers = define("allowMendingTinkers", true)

        comment("The amount of tinkers modifers needed to reach full power.")
        maxTinkersModifersCount = defineInRange("maxTinkersModifersCount", 25, 0, 200)

        comment("The max level of enchantment needed to full power of the tool.")
        maxEnchantmentLevel = defineInRange("maxEnchantmentLevel", 5, 0, 20)
    }.build()

/*
    @EventBusSubscriber
    internal object ConfigurationHolder {
        @SubscribeEvent
        fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
            if (event.getModID().equals(OreExcavationIntegration.MOD_ID)) {
                ConfigManager.load(OreExcavationIntegration.MOD_ID, Config.Type.INSTANCE)
            }
        }
    }*/
}
