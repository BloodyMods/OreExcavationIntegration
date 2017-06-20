package atm.bloodworkxgaming.oeintegration.Proxy;


import atm.bloodworkxgaming.oeintegration.ModBlocks;
import atm.bloodworkxgaming.oeintegration.ModItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void preInit() {
        super.preInit();

        ModBlocks.initModels();
        ModItems.initModels();
    }

    public void init() {
        super.init();
    }


    public void postInit() {
        super.postInit();
    }

}
