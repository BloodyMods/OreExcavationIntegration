package atm.bloodworkxgaming.oei.Handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oreexcavation.core.ExcavationSettings;
import oreexcavation.core.OreExcavation;
import oreexcavation.groups.ItemBlacklist;
import oreexcavation.groups.ItemEntry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static oreexcavation.handlers.EventHandler.captureAgent;

/**
 * Created by jonas on 18.06.2017.
 */
public class EventHandler {

    // Hacks into the blacklist
    private static List<ItemEntry> banList = null;

    // original values
    private static List<ItemEntry> originalBanList = new ArrayList<>();
    private static boolean originalInvertBlacklist = ExcavationSettings.invertTBlacklist;

    static {
        try {
            Field fieldBanList = ItemBlacklist.INSTANCE.getClass().getDeclaredField("banList");
            fieldBanList.setAccessible(true);
            banList = (List<ItemEntry>) fieldBanList.get(ItemBlacklist.INSTANCE);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        originalBanList.addAll(banList);
        banList.clear();

        ExcavationSettings.invertTBlacklist = true;
    }




    @SubscribeEvent(priority = EventPriority.LOW)
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equals(OreExcavation.MODID))
        {
            // System.out.println("Reloaded settings");
            // ConfigHandler.config.save();
            // ConfigHandler.initConfigs();
            // System.out.println(banList.toString());

            reloadSettings();
        }
    }

    /**
     * This functions disables everything by default when reading the configs but keeps the actual values of the lists
     */
    private void reloadSettings(){
        // gets the actual ban list and resets the one in OE
        originalBanList.clear();
        originalBanList.addAll(banList);

        System.out.println("originalBanList: " + originalBanList.toString());
        banList.clear();

        // gets the right invertblacklist flag and resets it
        originalInvertBlacklist = ExcavationSettings.invertTBlacklist;
        ExcavationSettings.invertTBlacklist = true;

    }




    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBlockBreakBeforeOE(BlockEvent.BreakEvent event)
    {
        // System.out.println("OB: " + System.identityHashCode(originalBanList));
        // System.out.println("BL: " + System.identityHashCode(banList));


        //region >>> Prevents useless checks
        if(event.getWorld().isRemote)
        {
            return;
        }

        if(captureAgent != null && !captureAgent.hasMinedPosition(event.getPos()))
        {
            return; // Prevent unnecessary checks if an agent was responsible
        }

        if(!(event.getPlayer() instanceof EntityPlayerMP) || event.getPlayer() instanceof FakePlayer)
        {
            return;
        }
        //endregion

        // returns when tool is blacklisted
        if (isToolBlacklisted(event.getPlayer().getHeldItemMainhand())) return;

        // System.out.println("Blacklisted tool:  " + isToolBlacklisted(event.getPlayer().getHeldItemMainhand()));
        // System.out.println("Banlist: " + banList);
        EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
        if (IntegrationHandler.checkCanMine(event.getWorld(), player, event.getPos(), event.getState())){
            allowExcavation();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onBlockBreakAfterOE(BlockEvent.BreakEvent event){

        //region >>> Prevents useless checks
        if(event.getWorld().isRemote)
        {
            return;
        }

        if(captureAgent != null && !captureAgent.hasMinedPosition(event.getPos()))
        {
            return; // Prevent unnecessary checks if an agent was responsible
        }

        if(!(event.getPlayer() instanceof EntityPlayerMP) || event.getPlayer() instanceof FakePlayer)
        {
            return;
        }
        //endregion

        // resets to the default state
        prohibitExcavation();

    }


    public void allowExcavation(){
        ExcavationSettings.invertTBlacklist = false;
    }


    public void prohibitExcavation(){
        ExcavationSettings.invertTBlacklist = true;
    }

    public boolean isToolBlacklisted(ItemStack stack)
    {
        // checks for empty hand
        if(stack == null || stack.isEmpty())
        {
            return false;
        }

        // checks for an originally banned item
        Iterator var2 = originalBanList.iterator();
        ItemEntry entry;
        do {
            if(!var2.hasNext()) {
                return originalInvertBlacklist;
            }

            entry = (ItemEntry)var2.next();
        } while(!entry.checkMatch(stack));

        return !originalInvertBlacklist;
    }

}
