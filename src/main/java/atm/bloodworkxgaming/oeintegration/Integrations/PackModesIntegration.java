package atm.bloodworkxgaming.oeintegration.Integrations;

import atm.bloodworkxgaming.oeintegration.MainConfig;
import io.sommers.packmode.api.PackModeAPI;

public class PackModesIntegration {
    public static boolean checkIsCorrectPackmode(){
        String currentMode = PackModeAPI.getInstance().getCurrentPackMode();
        if (currentMode == null) return false;


        for (String s : MainConfig.packModesWhitelist) {
            if (currentMode.equals(s)) return !MainConfig.packModesWhitelistToBlacklistToggle;
        }

        return MainConfig.packModesWhitelistToBlacklistToggle;
    }
}
