package atm.bloodworkxgaming.oeintegration.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by jonas on 19.06.2017.
 */
public class ModEnchantments {


    public static Enchantment excavationEnchantment = new ExcavationEnchantment();

    public static void registerEnchantments(){

        GameRegistry.register(excavationEnchantment , new ResourceLocation("oeintegration:oreexcavation"));
    }

}
