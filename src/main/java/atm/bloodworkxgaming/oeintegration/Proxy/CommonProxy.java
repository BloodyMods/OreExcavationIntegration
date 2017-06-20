package atm.bloodworkxgaming.oeintegration.Proxy;


import atm.bloodworkxgaming.oeintegration.ModBlocks;
import atm.bloodworkxgaming.oeintegration.ModItems;

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
