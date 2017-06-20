package atm.bloodworkxgaming.oeintegration;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Jonas on 09.05.2017.
 */

@Config(modid = OreExcavationIntegration.MOD_ID)
public class MainConfig {

    @Config.Comment("A tool in this list won't require the enchantment")
    public static String[] toolWhitelistOverride = new String[]{

    };

    @Config.Comment("A tool in this list will be allowed to get the enchantment as well as any tool")
    public static String[] enchantmentWhitelist = new String[]{
            "minecraft:shears"
    };

    @Config.Comment("This will take away the ability to enchant any item that is a tool")
    public static String[] enchantmentBlacklist = new String[]{

    };



    @Mod.EventBusSubscriber
    static class ConfigurationHolder {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
            if (event.getModID().equals(OreExcavationIntegration.MOD_ID)){
                ConfigManager.load(OreExcavationIntegration.MOD_ID, Config.Type.INSTANCE);
            }
        }

    }
}

// <https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/b3d71dfddf7f212f0d86ef36e6ae1d06b8493ebc/src/main/java/choonster/testmod3/config/ModConfig.java>