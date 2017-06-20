package atm.bloodworkxgaming.oeintegration.Integrations;

import atm.bloodworkxgaming.oeintegration.ModItems;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;

/**
 * Created by jonas on 19.06.2017.
 */
public class ExcavateModifier extends Modifier {

    public ExcavateModifier() {
        super("oreexcavate");
        this.addAspects(ModifierAspect.toolOnly,
                new ModifierAspect.SingleAspect(this),
                new ModifierAspect.DataAspect(this, 0xCC6600));

        this.addItem(ModItems.itemExcavateModifier, 1, 1);

    }

    @Override
    public void applyEffect(NBTTagCompound nbtTagCompound, NBTTagCompound nbtTagCompound1) {

    }
}
