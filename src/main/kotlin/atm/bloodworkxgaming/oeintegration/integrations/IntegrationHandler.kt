package atm.bloodworkxgaming.oeintegration.integrations

import atm.bloodworkxgaming.oeintegration.MainConfig
import atm.bloodworkxgaming.oeintegration.OreExcavationIntegration
import atm.bloodworkxgaming.oeintegration.enchantments.ModEnchantments
import atm.bloodworkxgaming.oeintegration.tconstruct.TiCModifiers
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.ModList
import oreexcavation.core.ExcavationSettings
import oreexcavation.handlers.MiningAgent
import oreexcavation.overrides.ToolOverride
import slimeknights.tconstruct.library.TinkerRegistries
import slimeknights.tconstruct.library.modifiers.Modifier
import slimeknights.tconstruct.library.modifiers.ModifierId
import slimeknights.tconstruct.shared.TinkerMaterials
import slimeknights.tconstruct.tools.TinkerModifiers
import slimeknights.tconstruct.tools.TinkerTools

inline fun runIfModLoaded(name: String,  function: (() -> Unit)) {
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

    fun checkCanMine(player: ServerPlayerEntity): IntegrationType {
        if (MainConfig.disableMod.get()) return IntegrationType.MOD_DISABLED
        val usedItem: ItemStack = player.mainHandItem

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

        runIfModLoaded("tconstruct") {
            val itemStack = Modifier.getHeldTool(player) ?: return@runIfModLoaded
            if (itemStack.isBroken) return@runIfModLoaded

            for (modifierEntry in itemStack.modifierList) {
                if (modifierEntry.modifier.registryName == ModifierId(OreExcavationIntegration.MOD_ID, "modifier_excavate"))
                    return IntegrationType.TINKERS_CONSTRUCT
            }
        }

        return IntegrationType.DISALLOWED
    }

    fun changeToolOverwriteEnchantment(agent: MiningAgent) {
        val held: ItemStack = agent.player.mainHandItem

        // enchantment data
        val enchantmentLevel: Int = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ENCHANTMENT_EXCAVATION, held)
        val maxLevel: Int = ModEnchantments.ENCHANTMENT_EXCAVATION.maxLevel


        // gets a new toolOverwrite
        val toolProps: ToolOverride = ToolOverride.readFromString("*")
        val modifier = enchantmentLevel.toFloat() / maxLevel.toFloat()
        toolProps.range = (ExcavationSettings.mineRange * modifier).toInt() + RangeModifer
        toolProps.limit = (ExcavationSettings.mineLimit * modifier).toInt() + LimitModifier
        agent.toolProps = toolProps
    }


    fun changeToolOverwriteTinkers(agent: MiningAgent) {
        val itemStack = Modifier.getHeldTool(agent.player) ?: return
        val level = itemStack.getModifierLevel(TiCModifiers.MODIFIER_EXCAVATE)
        val scaled = TiCModifiers.MODIFIER_EXCAVATE.getScaledLevel(itemStack, level)

        // gets a new toolOverwrite
        val toolProps: ToolOverride = ToolOverride.readFromString("*")
        toolProps.range = (ExcavationSettings.mineRange * scaled).toInt() + RangeModifer
        toolProps.limit = (ExcavationSettings.mineLimit * scaled).toInt() + LimitModifier
        agent.toolProps = toolProps
    }
}
