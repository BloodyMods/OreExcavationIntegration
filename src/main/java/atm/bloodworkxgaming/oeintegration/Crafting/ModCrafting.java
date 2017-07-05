package atm.bloodworkxgaming.oeintegration.Crafting;

import atm.bloodworkxgaming.oeintegration.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by jonas on 19.06.2017.
 */
public class ModCrafting {

    @ObjectHolder("tconstruct:ingots")
    public final static Item ingots = null;


    public static void initCrafting(){
            ticCrafting();
    }


    public static void ticCrafting(){
        if (Loader.isModLoaded("tconstruct")) {

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemExcavateModifier),
                    new Object[]{"ASA",
                                 "RMR",
                                 "ASA",
                    'S', "slimeball", 'M', new ItemStack(ingots, 1, 2), 'R', "dustRedstone", 'A', "ingotIron"}
            ));
        }
    }
}
