package atm.bloodworkxgaming.oeintegration.Handler;

import atm.bloodworkxgaming.oeintegration.MainConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import oreexcavation.events.EventExcavate;
import oreexcavation.handlers.MiningAgent;

/**
 * Created by jonas on 18.06.2017.
 */
@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void onExcavateEvent(EventExcavate.Pre eventExcavate) {
        MiningAgent agent = eventExcavate.getAgent();
        ItemStack held = agent.player.getHeldItemMainhand();

        switch (IntegrationHandler.checkCanMine(held)){
            case WHITELIST:
            case MOD_DISABLED:
                break;
            case ENCHANTMENT:
                IntegrationHandler.changeToolOverwriteEnchantment(agent);
                break;
            case TINKERS_CONSTRUCT:
                IntegrationHandler.changeToolOverwriteTinkers(agent);
                break;
            case DISALLOWED:
            default:
                eventExcavate.setCanceled(true);
                if (!MainConfig.disableChatNotification){
                    agent.player.sendMessage(new TextComponentString("You need the \u00A7eExcavate Modifier \u00A7ror the \u00A76Enchantment \u00A7ron your tool to be able to excavate!"));
                }
                break;

        }
    }
}
