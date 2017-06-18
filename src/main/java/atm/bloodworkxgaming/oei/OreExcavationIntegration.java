package atm.bloodworkxgaming.oei;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = OreExcavationIntegration.MOD_ID, name = OreExcavationIntegration.MOD_NAME, version = OreExcavationIntegration.VERSION, dependencies = OreExcavationIntegration.DEPENDENCIES)
public class OreExcavationIntegration
{
    public static final String MOD_ID = "oei";
    public static final String VERSION = "1.0";
    public static final String MOD_NAME = "OreExcavationIntegration";
    public static final String DEPENDENCIES = "after:oreexcavation";


    public static final Logger logger = LogManager.getLogger(MOD_ID);


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Removing the BlockBreak event from OreExcavation");
        MinecraftForge.EVENT_BUS.register(new atm.bloodworkxgaming.oei.Handler.EventHandler());

        GameRegistry.register(new ExcavationEnchantment(), new ResourceLocation("oreexcavation"));


        /*
        try {

            Field field = MinecraftForge.EVENT_BUS.getClass().getDeclaredField("listeners");
            field.setAccessible(true);

            ConcurrentHashMap<Object, ArrayList<IEventListener>> listenerMap = (ConcurrentHashMap<Object, ArrayList<IEventListener>>) field.get(MinecraftForge.EVENT_BUS);


            System.out.println("all registered Listeners");
            System.out.println(listenerMap.toString());
            listenerMap.entrySet().removeIf(entry -> entry.getKey() instanceof oreexcavation.handlers.EventHandler);


            listenerMap = (ConcurrentHashMap<Object, ArrayList<IEventListener>>) field.get(MinecraftForge.EVENT_BUS);
            System.out.println(listenerMap.toString());


            /*
                listenerMap.forEach((k, v) -> {
                if (k instanceof oreexcavation.handlers.EventHandler){

                    System.out.println(v.toString());
                    for (int i = v.size() - 1; i >= 0; i--){
                        if (v.get(i).toString().contains("onBlockBreak(Lnet/minecraftforge/event/world/BlockEvent$BreakEvent;)V")){
                            v.remove(i);
                        }
                    }

                    System.out.println(v.toString());
                }
            });
            */


            /*
            for (Map.Entry<Object, ArrayList<IEventListener>> entry : listenerMap.entrySet())
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
            */

        /*
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        */

    }
}
