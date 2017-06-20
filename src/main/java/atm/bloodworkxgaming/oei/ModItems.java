package atm.bloodworkxgaming.oei;

import atm.bloodworkxgaming.oei.Common.Items.ItemExcavateModifier;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Jonas on 09.03.2017.
 */
public class ModItems {

    public static ItemExcavateModifier itemExcavateModifier;




    public static void init(){
        // init of all items
        itemExcavateModifier = new ItemExcavateModifier();
    }


    @SideOnly(Side.CLIENT)
    public static void initModels(){
        itemExcavateModifier.initModel();

    }
}
