package atm.bloodworkxgaming.oei.Handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by jonas on 18.06.2017.
 */
public class IntegrationHandler {

    public static boolean checkCanMine(World world, EntityPlayerMP player, BlockPos pos, IBlockState state){

        ItemStack held = player.getHeldItemMainhand();
        if (held.isItemEnchanted()) {
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByLocation("oreexcavation"), held) > 0){
                return true;
            }
        }

        /*if (player.getHeldItemMainhand().getItem() == Items.APPLE || player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE){
            return true;
        }*/

        return false;
    }


}
