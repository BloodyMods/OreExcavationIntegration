package atm.bloodworkxgaming.oei.Proxy;


import atm.bloodworkxgaming.oei.ModBlocks;
import atm.bloodworkxgaming.oei.ModItems;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public abstract class CommonProxy {

    public void preInit() {
        ModBlocks.init();
        ModItems.init();
    }

    public void init() {
        // ModCrafting.init();

    }


    public void postInit() {

    }

    // leaving out isCreative and isDedicatedServer for now


}
