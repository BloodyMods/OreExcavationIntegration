package atm.bloodworkxgaming.oeintegration.Handler;

import atm.bloodworkxgaming.oeintegration.MainConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.lang3.ArrayUtils;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

/**
 * Created by jonas on 18.06.2017.
 */
public class IntegrationHandler {

    public static boolean checkCanMine(World world, EntityPlayerMP player, BlockPos pos, IBlockState state){
        ItemStack held = player.getHeldItemMainhand();

        if (held.getItem().getRegistryName() != null && ArrayUtils.contains(MainConfig.toolWhitelistOverride, held.getItem().getRegistryName().toString())){
            return true;
        }

        if (held.isItemEnchanted()) {
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByLocation("oeintegration:oreexcavation"), held) > 0){
                return true;
            }
        }



        if (Loader.isModLoaded("tconstruct") && held.hasTagCompound() && TinkerUtil.hasModifier(held.getTagCompound(), "oreexcavate")){
            return !ToolHelper.isBroken(held);
        }



        /*if (player.getHeldItemMainhand().getItem() == Items.APPLE || player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE){
            return true;
        }*/

        return false;
    }


}
