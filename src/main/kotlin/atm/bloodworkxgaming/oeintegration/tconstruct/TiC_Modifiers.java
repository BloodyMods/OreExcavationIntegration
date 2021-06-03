package atm.bloodworkxgaming.atm.bloodworkxgaming.oeintegration.atm.bloodworkxgaming.oeintegration.integrations;

import slimeknights.tconstruct.library.modifiers.Modifier;

/**
 * Created by jonas on 19.06.2017.
 */
public class TiC_Modifiers {

    public static Modifier excavateModifier;


    public static void register() {
        excavateModifier = new ExcavateModifier();

    }


}
