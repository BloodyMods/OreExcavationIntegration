package atm.bloodworkxgaming.oeintegration.Handler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oreexcavation.events.EventExcavate;
import oreexcavation.handlers.MiningAgent;

/**
 * Created by jonas on 18.06.2017.
 */
public class EventHandler {
    @SubscribeEvent
    public void onExcavateEvent(EventExcavate.Pre eventExcavate) {

        MiningAgent agent = eventExcavate.getAgent();

        ItemStack held = agent.player.getHeldItemMainhand();

        switch (IntegrationHandler.checkCanMine(held)){
            case WHITELIST:
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
                agent.player.sendMessage(new TextComponentString("You need the \u00A7eExcavate Modifier \u00A7ror the \u00A76Enchantment \u00A7ron your tool to be able to excavate!"));
                break;

        }
    }
}
