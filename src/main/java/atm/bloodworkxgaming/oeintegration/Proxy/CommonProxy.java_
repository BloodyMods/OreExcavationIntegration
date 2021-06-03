package atm.bloodworkxgaming.oeintegration.Proxy;


import atm.bloodworkxgaming.oeintegration.Enchantments.ModEnchantments;
import atm.bloodworkxgaming.oeintegration.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public abstract class CommonProxy {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        ModEnchantments.registerEnchantments(event.getRegistry());
    }

    public void registerModels(ModelRegistryEvent event) {
    }

    public void preInit() {
    }

    public void init() {
    }

    public void postInit() {
    }
}
