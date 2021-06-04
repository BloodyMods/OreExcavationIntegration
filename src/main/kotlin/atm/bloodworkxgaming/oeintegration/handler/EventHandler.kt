package atm.bloodworkxgaming.oeintegration.handler

import atm.bloodworkxgaming.oeintegration.MainConfig
import atm.bloodworkxgaming.oeintegration.OreExcavationIntegration
import atm.bloodworkxgaming.oeintegration.OreExcavationIntegration.LOGGER
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationHandler
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.*
import net.minecraft.util.text.ChatType
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.eventbus.api.SubscribeEvent
import oreexcavation.events.EventExcavate
import java.util.*

object EventHandler {
    @SubscribeEvent
    fun onExcavateEvent(eventExcavate: EventExcavate.Pre) {
        val agent = eventExcavate.agent

        LOGGER.info(eventExcavate)

        fun cancelEvent(){
            eventExcavate.isCanceled = true
            if (!MainConfig.disableChatNotification.get()) {
                LOGGER.warn("should be canceled!")
                // ("You need the \u00A7eExcavate Modifier \u00A7ror the \u00A76Enchantment \u00A7ron your tool to be able to excavate!")
                agent.player.sendMessage(TranslationTextComponent("lang.translation.event.canceled"), ChatType.GAME_INFO, UUID.randomUUID())
            }
        }

        val canMine = IntegrationHandler.checkCanMine(agent.player)
        println(canMine)
        when (canMine) {
            TINKERS_CONSTRUCT -> println("should work!")
            ENCHANTMENT -> println("should work!")
            DISALLOWED -> cancelEvent()
            MOD_DISABLED, WHITELISTED_PACKMODE, WHITELIST -> {/* All fine, continue mining */}
        }
    }

    /*switch (IntegrationHandler.checkCanMine(held))
    {
        case WHITELIST :
        case MOD_DISABLED :
        case WHITELISTED_PACKMODE :
        break;
        case ENCHANTMENT :
        IntegrationHandler.changeToolOverwriteEnchantment(agent);
        break;
        case TINKERS_CONSTRUCT :
        IntegrationHandler.changeToolOverwriteTinkers(agent);
        break;
        case DISALLOWED :
        default:
        eventExcavate.setCanceled(true);
        if (!atm.bloodworkxgaming.oeintegration.MainConfig.disableChatNotification) {
            agent.player.sendMessage(new TextComponentString ("You need the \u00A7eExcavate Modifier \u00A7ror the \u00A76Enchantment \u00A7ron your tool to be able to excavate!"));
        }
        break;

    }*/


}
