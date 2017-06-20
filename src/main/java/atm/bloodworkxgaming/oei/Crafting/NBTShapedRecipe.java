package atm.bloodworkxgaming.oei.Crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;

/**
 * Created by jonas on 20.06.2017.
 */
public class NBTShapedRecipe extends ShapedRecipes{


    public NBTShapedRecipe(int width, int height, ItemStack[] IngredientArray, ItemStack output)
    {
        super(width, height, IngredientArray, output);
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        for (int i = 0; i <= 3 - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= 3 - this.recipeHeight; ++j)
            {
                if (this.checkMatch(inv, i, j, true))
                {
                    return true;
                }

                if (this.checkMatch(inv, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting inventoryCrafting, int horizontal, int vertical, boolean p_77573_4_)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                int k = i - horizontal;
                int l = j - vertical;
                ItemStack itemStackRecipe = ItemStack.EMPTY;

                if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight)
                {
                    if (p_77573_4_)
                    {
                        itemStackRecipe = this.recipeItems[this.recipeWidth - k - 1 + l * this.recipeWidth];
                    }
                    else
                    {
                        itemStackRecipe = this.recipeItems[k + l * this.recipeWidth];
                    }
                }

                ItemStack input = inventoryCrafting.getStackInRowAndColumn(i, j);

                if (!input.isEmpty() || !itemStackRecipe.isEmpty())
                {
                    if (input.isEmpty() != itemStackRecipe.isEmpty())
                    {
                        return false;
                    }

                    if (itemStackRecipe.getItem() != input.getItem())
                    {
                        return false;
                    }

                    if (itemStackRecipe.getMetadata() != 32767 && itemStackRecipe.getMetadata() != input.getMetadata())
                    {
                        return false;
                    }

                    if (itemStackRecipe.hasTagCompound() && input.hasTagCompound()){
                        if (!itemStackRecipe.getTagCompound().equals(input.getTagCompound())){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}
