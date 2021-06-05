package atm.bloodworkxgaming.oeintegration.handler

import atm.bloodworkxgaming.oeintegration.MainConfig
import atm.bloodworkxgaming.oeintegration.OreExcavationIntegration.LOGGER
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationHandler
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.DISALLOWED
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.ENCHANTMENT
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.MOD_DISABLED
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.TINKERS_CONSTRUCT
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.WHITELIST
import atm.bloodworkxgaming.oeintegration.integrations.IntegrationType.WHITELISTED_PACKMODE
import net.minecraft.util.text.ChatType
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.eventbus.api.SubscribeEvent
import oreexcavation.events.EventExcavate
import java.util.*

object EventHandler {
    @SubscribeEvent
    fun onExcavateEvent(eventExcavate: EventExcavate.Pre) {
        val agent = eventExcavate.agent

        fun cancelEvent() {
            eventExcavate.isCanceled = true
            if (!MainConfig.disableChatNotification.get()) {
                agent.player.sendMessage(
                    TranslationTextComponent("chat.oeintegration.excavate.canceled"),
                    ChatType.GAME_INFO,
                    UUID.randomUUID()
                )
            }
        }

        when (IntegrationHandler.checkCanMine(agent.player)) {
            TINKERS_CONSTRUCT -> IntegrationHandler.changeToolOverwriteTinkers(agent)
            ENCHANTMENT -> IntegrationHandler.changeToolOverwriteEnchantment(agent)
            DISALLOWED -> cancelEvent()
            MOD_DISABLED, WHITELISTED_PACKMODE, WHITELIST -> {/* All fine, continue mining */
            }
        }
    }
}
