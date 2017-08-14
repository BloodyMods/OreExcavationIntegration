package atm.bloodworkxgaming.oeintegration;

import atm.bloodworkxgaming.oeintegration.Common.Items.ItemExcavateModifier;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Jonas on 09.03.2017.
 */
public class ModItems {

    public static ItemExcavateModifier itemExcavateModifier;

    static {
        itemExcavateModifier = new ItemExcavateModifier();
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(itemExcavateModifier);
    }


    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemExcavateModifier.initModel();
    }
}
