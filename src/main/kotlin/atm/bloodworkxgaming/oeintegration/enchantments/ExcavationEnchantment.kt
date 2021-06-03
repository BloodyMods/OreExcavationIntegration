package atm.bloodworkxgaming.oeintegration.enchantments

import atm.bloodworkxgaming.oeintegration.MainConfig
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentType
import net.minecraft.enchantment.Enchantments
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack

class ExcavationEnchantment : Enchantment(
    Rarity.VERY_RARE,
    EnchantmentType.DIGGER,
    arrayOf(EquipmentSlotType.MAINHAND)
) {

    /**
     * Returns the maximum level that the enchantment can have.
     */
    override fun getMaxLevel(): Int {
        return MainConfig.maxEnchantmentLevel.get()
    }

    override fun canApplyAtEnchantingTable(stack: ItemStack): Boolean {
        val name = stack.item.registryName.toString()
        return !MainConfig.enchantmentBlacklist.get().contains(name)
                || (super.canApplyAtEnchantingTable(stack) && MainConfig.enchantmentWhitelist.get().contains(name))
    }


    override fun checkCompatibility(other: Enchantment): Boolean {
        return if (MainConfig.allowMendingEnchantment.get()) {
            super.checkCompatibility(other)
        } else {
            super.checkCompatibility(other) && other !== Enchantments.MENDING
        }
    }
}
