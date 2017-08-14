package atm.bloodworkxgaming.oeintegration.Handler;

import atm.bloodworkxgaming.oeintegration.Enchantments.ModEnchantments;
import atm.bloodworkxgaming.oeintegration.MainConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import oreexcavation.handlers.MiningAgent;
import oreexcavation.overrides.ToolOverride;
import org.apache.commons.lang3.ArrayUtils;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

/**
 * Created by jonas on 18.06.2017.
 */
public class IntegrationHandler {

    public static final int RangeModifer = 1;
    public static final int LimitModifier = 10;


    public static IntegrationType checkCanMine(ItemStack usedItem) {

        if (usedItem.getItem().getRegistryName() != null && ArrayUtils.contains(MainConfig.toolWhitelistOverride, usedItem.getItem().getRegistryName().toString())) {
            return IntegrationType.WHITELIST;
        }

        if (usedItem.isItemEnchanted()) {
            if (getEnchantmentLevel(ModEnchantments.excavationEnchantment, usedItem) > 0) {
                return IntegrationType.ENCHANTMENT;
            }
        }

        if (Loader.isModLoaded("tconstruct") && usedItem.hasTagCompound() && TinkerUtil.hasModifier(usedItem.getTagCompound(), "oreexcavate")) {
            if (!ToolHelper.isBroken(usedItem)) {
                return IntegrationType.TINKERS_CONSTRUCT;
            }
        }

        return IntegrationType.DISALLOWED;
    }

    public static void changeToolOverwriteEnchantment(MiningAgent agent) {
        ItemStack held = agent.player.getHeldItemMainhand();

        // enchantment data
        int enchantmentLevel = getEnchantmentLevel(ModEnchantments.excavationEnchantment, held);
        int maxLevel = ModEnchantments.excavationEnchantment.getMaxLevel();


        // gets a new toolOverwrite
        ToolOverride toolProps = ToolOverride.readFromString("*");

        float modifier = ((float) enchantmentLevel / (float) maxLevel);

        // System.out.println("limit: " + (int)(ToolOverride.DEFAULT.getLimit() * modifier) );

        toolProps.setRange((int) (ToolOverride.DEFAULT.getRange() * modifier) + RangeModifer);
        toolProps.setLimit((int) (ToolOverride.DEFAULT.getLimit() * modifier) + LimitModifier);

        agent.toolProps = toolProps;
    }


    public static void changeToolOverwriteTinkers(MiningAgent agent) {
        ItemStack held = agent.player.getHeldItemMainhand();

        NBTTagCompound modifier = TinkerUtil.getModifierTag(held, "oreexcavate");
        // System.out.println(modifier);

        int enchantmentLevel = modifier.getInteger("current");
        int maxLevel = modifier.getInteger("max");


        // gets a new toolOverwrite
        ToolOverride toolProps = ToolOverride.readFromString("*");

        float modifierModifier = ((float) enchantmentLevel / (float) maxLevel);

        toolProps.setRange((int) Math.ceil(ToolOverride.DEFAULT.getRange() * modifierModifier) + RangeModifer);
        toolProps.setLimit((int) Math.ceil(ToolOverride.DEFAULT.getLimit() * modifierModifier) + LimitModifier);

        // System.out.println("limit: " + (toolProps.getLimit()) );
        // System.out.println("range: " + (toolProps.getRange()) );

        agent.toolProps = toolProps;
    }

}
