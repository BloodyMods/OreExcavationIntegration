package atm.bloodworkxgaming.oeintegration.enchantments

import atm.bloodworkxgaming.oeintegration.OreExcavationIntegration
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Created by jonas on 19.06.2017.
 */
object ModEnchantments {
    val REGISTRY = KDeferredRegister(ForgeRegistries.ENCHANTMENTS, OreExcavationIntegration.MOD_ID)

    val ENCHANTMENT_EXCAVATION by REGISTRY.registerObject("oreexcavation") {
        ExcavationEnchantment()
    }
}
