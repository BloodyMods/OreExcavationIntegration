package atm.bloodworkxgaming.oei.Crafting;

import atm.bloodworkxgaming.oei.ModEnchantments;
import atm.bloodworkxgaming.oei.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.tconstruct.TinkerIntegration;
import slimeknights.tconstruct.library.TinkerAPIException;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.events.TinkerCastingEvent;

/**
 * Created by jonas on 19.06.2017.
 */
public class ModCrafting {

    public static void initCrafting(){

        ticCrafting();

    }


    public static void ticCrafting(){

        GameRegistry.addRecipe(new ItemStack(ModItems.itemExcavateModifier),
                "AAA",
                         "AAA",
                         "AAA",
                'A', Items.APPLE);
        //TinkerUtil.getMaterialFromStack(new ItemStack(Items.IRON_INGOT))

        ItemStack enderrod = new ItemStack(Item.getByNameOrId("tconstruct:tough_tool_rod"));
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Material", "endstone");
        enderrod.setTagCompound(nbt);


        GameRegistry.addRecipe(new NBTShapedRecipe(3, 3, new ItemStack[]{
                enderrod, enderrod, enderrod,
                enderrod, getTinkersPart("tough_binding", "knightslime"), enderrod,
                enderrod, getTinkersPart("tough_tool_rod", "endstone"), enderrod
                }, new ItemStack(ModItems.itemExcavateModifier)));


        // exchange recipe between book and modifier
        ItemStack magicBook = new ItemStack(Items.ENCHANTED_BOOK);
        Items.ENCHANTED_BOOK.addEnchantment(magicBook, new EnchantmentData(ModEnchantments.excavationEnchantment, 1));

        GameRegistry.addRecipe(new NBTShapedRecipe(1, 1, new ItemStack[]{new ItemStack(ModItems.itemExcavateModifier)}, magicBook));
        GameRegistry.addRecipe(new NBTShapedRecipe(1, 1, new ItemStack[]{magicBook}, new ItemStack(ModItems.itemExcavateModifier)));

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
