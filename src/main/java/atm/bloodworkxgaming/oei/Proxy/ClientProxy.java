package atm.bloodworkxgaming.oei.Proxy;


import atm.bloodworkxgaming.oei.ModBlocks;
import atm.bloodworkxgaming.oei.ModItems;
import atm.bloodworkxgaming.oei.OreExcavationIntegration;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void preInit() {
        super.preInit();

        OBJLoader.INSTANCE.addDomain(OreExcavationIntegration.MOD_ID);

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
