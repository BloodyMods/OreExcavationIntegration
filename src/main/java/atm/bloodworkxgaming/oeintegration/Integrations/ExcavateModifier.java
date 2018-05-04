package atm.bloodworkxgaming.oeintegration.Integrations;

import atm.bloodworkxgaming.oeintegration.MainConfig;
import atm.bloodworkxgaming.oeintegration.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.ToolModifier;

/**
 * Created by jonas on 19.06.2017.
 */
public class ExcavateModifier extends ToolModifier {

    public ExcavateModifier() {
        super("oreexcavate", 0xCC6600);


        this.addAspects(ModifierAspect.toolOnly,
                new ModifierAspect.MultiAspect(this, 1, MainConfig.maxTinkersModifersCount, 1));

        this.addItem(ModItems.itemExcavateModifier, 1, 1);

    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return MainConfig.allowMendingTinkers || enchantment != Enchantments.MENDING;
    }

    @Override
    public boolean canApplyTogether(IToolMod otherModifier) {
        return MainConfig.allowMendingTinkers || !otherModifier.getIdentifier().equals(TinkerModifiers.modMendingMoss.getIdentifier());
    }

    @Override
    public void applyEffect(NBTTagCompound nbtTagCompound, NBTTagCompound nbtTagCompound1) {
        // no extra data needed
    }

    @Override
    public String getTooltip(NBTTagCompound modifierTag, boolean detailed) {
        return getLeveledTooltip(modifierTag, detailed);
    }
}



