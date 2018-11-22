package me.mightylordx.adminitems;

import java.util.Date;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIClick implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		ItemStack tar = inv.getItem(14);
		ItemStack banBy = inv.getItem(12);
		String bannedBy = banBy.getItemMeta().getDisplayName();
		String playerName = tar.getItemMeta().getDisplayName();
		Player target = Bukkit.getServer().getPlayer(playerName);
		if (event.getInventory().getTitle().equals("§4§lTempban Gui")) {
			event.setCancelled(true);
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fSpeed Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lSpeed Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Speed Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fPvP Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lPvP Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"staff Member");
				target.kickPlayer("§4§lYou have been banned for PvP Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fFly Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lFly Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Fly Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fOther")) {
				getReason((Player) event.getWhoClicked(), reason -> {
					Bukkit.getScheduler().runTask(Main.getInstance(),
							() -> target.kickPlayer("§4§lYou have been banned for " + reason + "!\n§f§lBy: "
									+ ChatColor.BOLD + bannedBy
									+ "\nContact a staff member for more information."));
					Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
							"§c§l" + reason + "\n§fBy: " + ChatColor.BOLD + bannedBy
									+ "\nContact a staff member for more information.",
							new Date(System.currentTimeMillis()
									+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
							"Staff Member");
					event.getWhoClicked().closeInventory();
				});

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fXray Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lXray Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Xray Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fJesus")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lJesus Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Jesus Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fChestESP")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lChestESP Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for ChestESP Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fBhop Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lBhop Hacking\n§f§lBy: " + ChatColor.BOLD + bannedBy,
						new Date(System.currentTimeMillis()
								+ (Main.getInstance().getConfig().getInt("tempban-time") * 1000 * 60 * 60 * 24)),
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Bhop Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
		} else if (event.getInventory().getTitle().equals("§4§lBan Gui")) {
			event.setCancelled(true);
			if (event.getCurrentItem() == null) {
				return;
			}
			if (!event.getCurrentItem().hasItemMeta()) {
				return;
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fSpeed Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lSpeed Hacking\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Speed Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fPvP Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lPvP Hacking\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"staff Member");
				target.kickPlayer("§4§lYou have been banned for PvP Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fFly Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lFly Hacking\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Fly Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fOther")) {

				getReason((Player) event.getWhoClicked(), reason -> {
					Bukkit.getScheduler().runTask(Main.getInstance(),
							() -> target.kickPlayer("§4§lYou have been banned for " + reason + "!\n§f§lBy: "
									+ ChatColor.BOLD + bannedBy
									+ "\nContact a staff member for more information."));
					Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
							"§c§l" + reason + "\n§fBy: " + ChatColor.BOLD + bannedBy
									+ "\nContact a staff member for more information.",
							null, "Staff Member");
					event.getWhoClicked().closeInventory();
				});
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fXray Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lXray Hacking\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Xray Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fJesus Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lJesus Hacking\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Jesus Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fChestESP")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lChestESP\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for ChestESP Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fBhop Hacking")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lBhop Hacking\n§fBy: " + ChatColor.BOLD + bannedBy, null,
						"Staff Member");
				target.kickPlayer("§4§lYou have been banned for Bhop Hacking!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§fDuping")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,
						"§c§lDuping\n§fBy: " + ChatColor.BOLD + bannedBy, null, "Staff Member");
				target.kickPlayer("§4§lYou have been banned for Duping!\n§f§lBy: " + ChatColor.BOLD
						+ bannedBy);
				event.getWhoClicked().closeInventory();
			}
		}
	}

	public void getReason(Player player, SignCallback callback) {
		SignMenu signMenu = new SignMenu(Main.getInstance());
		signMenu.open(player, new String[] { "", "^ ^ ^ ^", "Enter a reason", "" }, (awd, lines) -> {
			String reason = lines[0];
			callback.callback(reason);
		});
	}

}