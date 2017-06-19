package atm.bloodworkxgaming.oei.Common.Items;

import atm.bloodworkxgaming.oei.OreExcavationIntegration;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by jonas on 19.06.2017.
 */
public class ItemExcavateModifier extends Item {
    public ItemExcavateModifier(){
        setRegistryName("excavatemodifier");
        setUnlocalizedName(OreExcavationIntegration.MOD_ID + ".excavatemodifier");
        GameRegistry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(this, 0,new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
