package atm.bloodworkxgaming.oeintegration.integrations

import atm.bloodworkxgaming.oeintegration.MainConfig
import atm.bloodworkxgaming.oeintegration.enchantments.ModEnchantments
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.ModList
import slimeknights.tconstruct.shared.TinkerMaterials
import slimeknights.tconstruct.tools.TinkerModifiers
import slimeknights.tconstruct.tools.TinkerTools

fun runIfModLoaded(name: String, function: () -> Unit) {
    if (ModList.get().isLoaded(name)) {
        function()
    }
}

/**
 * Created by jonas on 18.06.2017.
 */
object IntegrationHandler {
    const val RangeModifer = 1
    const val LimitModifier = 10

    fun checkCanMine(usedItem: ItemStack): IntegrationType {
        if (MainConfig.disableMod.get()) return IntegrationType.MOD_DISABLED

        runIfModLoaded("packmode") {
            // if (PackModesIntegration.checkIsCorrectPackmode()) return IntegrationType.WHITELISTED_PACKMODE
        }

        if (usedItem.item.registryName != null && MainConfig.toolWhitelistOverride.get().contains(
                usedItem.item.registryName.toString()
            )
        ) {
            return IntegrationType.WHITELIST
        }

        if (usedItem.isEnchanted) {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ENCHANTMENT_EXCAVATION, usedItem) > 0) {
                return IntegrationType.ENCHANTMENT
            }
        }

        /*runIfModLoaded("tconstruct") {

            if (usedItem.hasTag() && TinkerUtil.hasModifier(
                    usedItem.getTagCompound(),
                    "oreexcavate"
                )
            ) {
                if (!ToolHelper.isBroken(usedItem)) {
                    return IntegrationType.TINKERS_CONSTRUCT
                }
            }
        }*/

        /*if (Loader.isModLoaded("tconstruct") && usedItem.hasTagCompound() && TinkerUtil.hasModifier(
                usedItem.getTagCompound(),
                "oreexcavate"
            )
        ) {
            if (!ToolHelper.isBroken(usedItem)) {
                return IntegrationType.TINKERS_CONSTRUCT
            }
        }*/
        return IntegrationType.DISALLOWED
    }

    /*fun changeToolOverwriteEnchantment(agent: MiningAgent) {
        val held: ItemStack = agent.player.getHeldItemMainhand()

        // enchantment data
        val enchantmentLevel: Int = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.excavationEnchantment, held)
        val maxLevel: Int = ModEnchantments.excavationEnchantment.getMaxLevel()


        // gets a new toolOverwrite
        val toolProps: ToolOverride = ToolOverride.readFromString("*")
        val modifier = enchantmentLevel.toFloat() / maxLevel.toFloat()
        toolProps.setRange((ExcavationSettings.mineRange * modifier) as Int + RangeModifer)
        toolProps.setLimit((ExcavationSettings.mineLimit * modifier) as Int + LimitModifier)
        agent.toolProps = toolProps
    }

    fun changeToolOverwriteTinkers(agent: MiningAgent) {
        val held: ItemStack = agent.player.getHeldItemMainhand()
        val modifier: NBTTagCompound = TinkerUtil.getModifierTag(held, "oreexcavate")
        val enchantmentLevel: Int = modifier.getInteger("current")
        val maxLevel: Int = modifier.getInteger("max")


        // gets a new toolOverwrite
        val toolProps: ToolOverride = ToolOverride.readFromString("*")
        val modifierModifier = enchantmentLevel.toFloat() / maxLevel.toFloat()
        toolProps.setRange(
            Math.ceil((ExcavationSettings.mineRange * modifierModifier).toDouble()).toInt() + RangeModifer
        )
        toolProps.setLimit(
            Math.ceil((ExcavationSettings.mineLimit * modifierModifier).toDouble()).toInt() + LimitModifier
        )
        agent.toolProps = toolProps
    }*/
}
