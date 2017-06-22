package atm.bloodworkxgaming.oeintegration;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by jonas on 18.06.2017.
 */
public class ExcavationEnchantment extends Enchantment {


    protected ExcavationEnchantment() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("oreexcavation");
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 20;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return ( super.canApply(stack) && !(stack.getItem().getRegistryName() != null && ArrayUtils.contains(MainConfig.enchantmentBlacklist, stack.getItem().getRegistryName().toString())))
                || (stack.getItem().getRegistryName() != null && ArrayUtils.contains(MainConfig.enchantmentWhitelist, stack.getItem().getRegistryName().toString()));
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        if (MainConfig.allowMendingEnchantment){
            return super.canApplyTogether(ench);
        } else {
            return super.canApplyTogether(ench) && ench != Enchantments.MENDING;
        }
    }
}
