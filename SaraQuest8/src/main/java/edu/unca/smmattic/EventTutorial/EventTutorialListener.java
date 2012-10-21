package edu.unca.smmattic.EventTutorial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

/*
 * This is a sample event listener
 */
public class EventTutorialListener implements Listener {
	private final EventTutorial plugin;

	/*
	 * This listener needs to know about the plugin which it came from
	 */
	public EventTutorialListener(EventTutorial plugin) {
		// Register the listener
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
    
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage(
				this.plugin.getConfig().getString("sample.message"));
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onSandBreak(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.SAND) {
			event.getPlayer().sendMessage("Destroying sand? Really? That seems pointless.");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void flight(PlayerInteractEvent event){
		if (event.getPlayer().getItemInHand().getType() == Material.FEATHER) {
			event.getPlayer().setAllowFlight(true);
			event.getPlayer().sendMessage("Unlike Daedalus, you only need one feather to fly.");
		} else {
			if (!(event.getPlayer().getItemInHand().getType() == Material.FEATHER)){
			event.getPlayer().setAllowFlight(false);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace1(BlockPlaceEvent event) {
		if (event.getBlockPlaced().getType() == Material.DIRT) {	
			event.getBlockPlaced().setType(Material.STONE);
			event.getPlayer().sendMessage("Stop building with dirt. Have some dignity.");
		}
	}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    	event.getPlayer().sendMessage("Good job, you found a " + event.getRightClicked().getClass().getInterfaces()[0].getSimpleName() + ". Are you going to kill it now and show us what a good hunter you are?");
    }
}
