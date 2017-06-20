package atm.bloodworkxgaming.oeintegration.Crafting;

import atm.bloodworkxgaming.oeintegration.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * Created by jonas on 19.06.2017.
 */
public class ModCrafting {


    @ObjectHolder("tconstruct:materials")
    public final static Item tinkersMaterials = null;

    public static void initCrafting(){
            ticCrafting();
    }


    public static void ticCrafting(){
        if (Loader.isModLoaded("tconstruct")) {

            GameRegistry.addRecipe(new NBTShapedRecipe(3, 3, new ItemStack[]{
                    new ItemStack(tinkersMaterials, 1, 12), getTinkersPart("broad_axe_head", "manyullyn"), new ItemStack(tinkersMaterials, 1, 12),
                    getTinkersPart("excavator_head", "prismarine"), getTinkersPart("tough_binding", "knightslime"), getTinkersPart("hammer_head", "pigiron"),
                    new ItemStack(tinkersMaterials, 1, 13), getTinkersPart("tough_tool_rod", "endstone"), new ItemStack(tinkersMaterials, 1, 13)
            }, new ItemStack(ModItems.itemExcavateModifier)));


            // exchange recipe between book and modifier
            ItemStack magicBook = new ItemStack(Items.ENCHANTED_BOOK);
            Items.ENCHANTED_BOOK.addEnchantment(magicBook, new EnchantmentData(Enchantment.getEnchantmentByLocation("oeintegration:oreexcavation"), 1));

            GameRegistry.addRecipe(new NBTShapedRecipe(1, 1, new ItemStack[]{new ItemStack(ModItems.itemExcavateModifier)}, magicBook));
            GameRegistry.addRecipe(new NBTShapedRecipe(1, 1, new ItemStack[]{magicBook}, new ItemStack(ModItems.itemExcavateModifier)));
        }
    }


    public static ItemStack getTinkersPart(String itemName, String material){

        Item item = Item.getByNameOrId("tconstruct:" + itemName);
        if (item != null){
            ItemStack itemStack = new ItemStack(item);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("Material", material);
            itemStack.setTagCompound(nbt);

            return itemStack;
        }

        return ItemStack.EMPTY;
    }

}
