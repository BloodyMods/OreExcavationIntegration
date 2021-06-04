package atm.bloodworkxgaming.oeintegration.tconstruct

import atm.bloodworkxgaming.oeintegration.OreExcavationIntegration
import slimeknights.tconstruct.library.TinkerRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Created by jonas on 19.06.2017.
 */
object TiCModifiers {
    init {
        println("Tinkers Reg: ")
        println(TinkerRegistries.MODIFIERS)
    }

    val REGISTRY = KDeferredRegister(TinkerRegistries.MODIFIERS, OreExcavationIntegration.MOD_ID) /*
    public static Modifier excavateModifier;


    public static void register() {
        excavateModifier = new ExcavateModifier();

    }
*/

    val MODIFIER_EXCAVATE by REGISTRY.registerObject("modifier_excavate") {
        println("registering Modifier!")
        ExcavateModifier()
    }
}
