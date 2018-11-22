package me.mightylordx.adminitems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUI implements CommandExecutor, Listener {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Sorry you must be a player to use this command!");
			return true;
		}

		Player p = (Player) sender;
		if (label.equalsIgnoreCase("tempban")) {
			if (p.isOp() || p.hasPermission("bangui.tempban") || p.hasPermission("bangui.*")) {
				if (args.length == 0) {
					p.sendMessage(
							"§cYou must specify a player!\n/tempban <name> \"<date>\" \"<reason>\" \"<>\" == Optional.");
					return true;
				}
				if (args.length == 3) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						p.sendMessage("§cTarget isn't online!");
						return true;
					}
					if (args[1].length() == 2) {
						String time = args[1].substring(1,2);
						String d = args[1].substring(0,1);
						if (!d.matches("[0-9]+")) {
							p.sendMessage("§cPlease specify a time!");
							return true;
						}
						int duration = Integer.parseInt(d);
						String targetToBan = target.getName();
						String reason = args[2];
						if (time.equals("d")) {
							Bukkit.getBanList(BanList.Type.NAME).addBan(targetToBan, ChatColor.RED + "§l" + reason,
									new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * duration),
									"§4§l" + p.getDisplayName());
							target.kickPlayer(
									"§4§lYou have been banned for " + reason + "!\n§f§lBy: " + p.getDisplayName());
						} else if (time.equals("h")) {
							Bukkit.getBanList(BanList.Type.NAME).addBan(targetToBan, ChatColor.RED + "§l" + reason,
									new Date(System.currentTimeMillis() + 1000 * 60 * 60 * duration),
									"§4§l" + p.getDisplayName());
							target.kickPlayer(
									"§4§lYou have been banned for " + reason + "!\n§f§lBy: " + p.getDisplayName());
						} else if (time.equals("m")) {
							Bukkit.getBanList(BanList.Type.NAME).addBan(targetToBan, ChatColor.RED + "§l" + reason,
									new Date(System.currentTimeMillis() + 1000 * 60 * duration),
									"§4§l" + p.getDisplayName());
							target.kickPlayer(
									"§4§lYou have been banned for " + reason + "!\n§f§lBy: " + p.getDisplayName());
						}
						else {
							p.sendMessage("§cPlease specify a time [time,duration] e.g 14d!\nd: day h: hour m: minute");
							return true;
						}
					} else if (args[1].length() == 3) {
						String time = args[1].substring(2,3);
						String d = args[1].substring(0,2);
						if(!d.matches("[0-99]+")) {
							p.sendMessage("§cPlease specify a time!");
							return true;
						}
						int duration = Integer.parseInt(d);
						String targetToBan = target.getName();
						String reason = args[2];
						if (time.equals("d")) {
							Bukkit.getBanList(BanList.Type.NAME).addBan(targetToBan, ChatColor.RED + "§l" + reason,
									new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * duration),
									"§4§l" + p.getDisplayName());
							target.kickPlayer(
									"§4§lYou have been banned for " + reason + "!\n§f§lBy: " + p.getDisplayName());
						} else if (time.equals("h")) {
							Bukkit.getBanList(BanList.Type.NAME).addBan(targetToBan, ChatColor.RED + "§l" + reason,
									new Date(System.currentTimeMillis() + 1000 * 60 * 60 * duration),
									"§4§l" + p.getDisplayName());
							target.kickPlayer(
									"§4§lYou have been banned for " + reason + "!\n§f§lBy: " + p.getDisplayName());
						} else if (time.equals("m")) {
							Bukkit.getBanList(BanList.Type.NAME).addBan(targetToBan, ChatColor.RED + "§l" + reason,
									new Date(System.currentTimeMillis() + 1000 * 60 * duration),
									"§4§l" + p.getDisplayName());
							target.kickPlayer(
									"§4§lYou have been banned for " + reason + "!\n§f§lBy: " + p.getDisplayName());
						}
						else {
							p.sendMessage("§cPlease specify a time [time,duration] e.g 14d!\nd: day h: hour m: minute");
							return true;
						}
					}
					return true;
				}

				if (args.length == 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						p.sendMessage("§cTarget isn't online!");
						return true;
					}
					Inventory inv = Bukkit.createInventory(null, 45, "§4§lTempban Gui");

					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
					SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
					skullMeta.setOwner(target.getName());
					skullMeta.setDisplayName(target.getName());
					List<String> skullLore = new ArrayList<String>();
					skullLore.add(target.getDisplayName());
					skullMeta.setLore(skullLore);
					skull.setItemMeta(skullMeta);
					inv.setItem(14, skull);

					ItemStack bannedBySkull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
					SkullMeta bannedBySkullMeta = (SkullMeta) bannedBySkull.getItemMeta();
					bannedBySkullMeta.setOwner(p.getName());
					bannedBySkullMeta.setDisplayName(p.getDisplayName());
					List<String> bannedBySkullLore = new ArrayList<String>();
					bannedBySkullLore.add(p.getDisplayName());
					bannedBySkullMeta.setLore(bannedBySkullLore);
					bannedBySkull.setItemMeta(bannedBySkullMeta);
					inv.setItem(12, bannedBySkull);

					ItemStack jesus = new ItemStack(Material.WATER_BUCKET);
					ItemMeta jesusMeta = jesus.getItemMeta();
					List<String> jesusLore = new ArrayList<String>();
					jesusLore.add("§cTempban " + target.getDisplayName() + " §cfor jesus hacking");
					jesusMeta.setLore(jesusLore);
					jesusMeta.setDisplayName("§fJesus");
					jesus.setItemMeta(jesusMeta);
					inv.setItem(19, jesus);

					ItemStack speedHack = new ItemStack(Material.SUGAR);
					ItemMeta speedHackMeta = speedHack.getItemMeta();
					speedHackMeta.setDisplayName("§fSpeed Hacking");
					List<String> speedHackLore = new ArrayList<String>();
					speedHackLore.add("§cTempban " + target.getDisplayName() + "§c for speed hacking");
					speedHackMeta.setLore(speedHackLore);
					speedHack.setItemMeta(speedHackMeta);
					inv.setItem(20, speedHack);

					ItemStack pvpHacking = new ItemStack(Material.IRON_SWORD);
					ItemMeta pvpHackingMeta = pvpHacking.getItemMeta();
					List<String> pvpHackingLore = new ArrayList<String>();
					pvpHackingLore.add("§cTempban " + target.getDisplayName() + " §cfor PvP hacking");
					pvpHackingMeta.setDisplayName("§fPvP Hacking");
					pvpHackingMeta.setLore(pvpHackingLore);
					pvpHacking.setItemMeta(pvpHackingMeta);
					inv.setItem(21, pvpHacking);

					ItemStack flyHack = new ItemStack(Material.FEATHER);
					ItemMeta flyHackMeta = flyHack.getItemMeta();
					List<String> flyHackLore = new ArrayList<String>();
					flyHackLore.add("§cTempban " + target.getDisplayName() + " §cfor fly hacking");
					flyHackMeta.setLore(flyHackLore);
					flyHackMeta.setDisplayName("§fFly Hacking");
					flyHack.setItemMeta(flyHackMeta);
					inv.setItem(22, flyHack);

					ItemStack xray = new ItemStack(Material.DIAMOND_ORE);
					ItemMeta xrayMeta = xray.getItemMeta();
					List<String> xrayLore = new ArrayList<String>();
					xrayLore.add("§cTempban " + target.getDisplayName() + " §cfor xray hacking");
					xrayMeta.setLore(xrayLore);
					xrayMeta.setDisplayName("§fXray Hacking");
					xray.setItemMeta(xrayMeta);
					inv.setItem(23, xray);

					ItemStack bhop = new ItemStack(Material.RABBIT_FOOT);
					ItemMeta bhopMeta = bhop.getItemMeta();
					List<String> bhopLore = new ArrayList<String>();
					bhopLore.add("§cTempban " + target.getDisplayName() + " §cfor bhop hacking");
					bhopMeta.setLore(bhopLore);
					bhopMeta.setDisplayName("§fBhop Hacking");
					bhop.setItemMeta(bhopMeta);
					inv.setItem(25, bhop);

					ItemStack chestESP = new ItemStack(Material.CHEST);
					ItemMeta chestESPMeta = chestESP.getItemMeta();
					List<String> chestESPLore = new ArrayList<String>();
					chestESPLore.add("§cTempan " + target.getDisplayName() + " §cfor ChestESP");
					chestESPMeta.setLore(chestESPLore);
					chestESPMeta.setDisplayName("§fChestESP");
					chestESP.setItemMeta(chestESPMeta);
					inv.setItem(24, chestESP);

					ItemStack otherHack = new ItemStack(Material.BARRIER);
					ItemMeta otherHackMeta = otherHack.getItemMeta();
					List<String> otherHackLore = new ArrayList<String>();
					otherHackLore.add("§cTempban " + target.getDisplayName() + " §cfor other");
					otherHackMeta.setLore(otherHackLore);
					otherHackMeta.setDisplayName("§fOther");
					otherHack.setItemMeta(otherHackMeta);
					inv.setItem(31, otherHack);

					ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
					ItemMeta blackGlassMeta = blackGlass.getItemMeta();
					blackGlassMeta.setDisplayName("§0");
					blackGlass.setItemMeta(blackGlassMeta);
					ItemStack whiteGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
					ItemMeta whiteGlassMeta = whiteGlass.getItemMeta();
					whiteGlassMeta.setDisplayName("§0");
					whiteGlass.setItemMeta(whiteGlassMeta);
					ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
					ItemMeta blueGlassMeta = blueGlass.getItemMeta();
					blueGlassMeta.setDisplayName("§0");
					blueGlass.setItemMeta(blueGlassMeta);

					inv.setItem(0, blackGlass);
					inv.setItem(1, blackGlass);
					inv.setItem(2, whiteGlass);
					inv.setItem(3, whiteGlass);
					inv.setItem(4, blueGlass);
					inv.setItem(5, whiteGlass);
					inv.setItem(6, whiteGlass);
					inv.setItem(7, blackGlass);
					inv.setItem(8, blackGlass);
					inv.setItem(9, blackGlass);
					inv.setItem(17, blackGlass);
					inv.setItem(18, blueGlass);
					inv.setItem(26, blueGlass);
					inv.setItem(27, blackGlass);
					inv.setItem(35, blackGlass);
					inv.setItem(36, blackGlass);
					inv.setItem(37, blackGlass);
					inv.setItem(38, whiteGlass);
					inv.setItem(39, whiteGlass);
					inv.setItem(40, blueGlass);
					inv.setItem(41, whiteGlass);
					inv.setItem(42, whiteGlass);
					inv.setItem(43, blackGlass);
					inv.setItem(44, blackGlass);

					p.openInventory(inv);

				}
			}

			else {
				p.sendMessage("§cYou don't have permission to use this command!");
			}
		} else if (label.equalsIgnoreCase("ban")) {
			if (p.isOp() || p.hasPermission("bangui.ban") || p.hasPermission("bangui.*")) {
				if (args.length == 0) {
					p.sendMessage("§cYou must specify a player!");
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					p.sendMessage("§cTarget isn't online!");
					return true;
				}
				Inventory inv = Bukkit.createInventory(null, 45, "§4§lBan Gui");

				ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
				SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
				skullMeta.setOwner(target.getName());
				skullMeta.setDisplayName(target.getName());
				List<String> skullLore = new ArrayList<String>();
				skullLore.add(target.getDisplayName());
				skullMeta.setLore(skullLore);
				skull.setItemMeta(skullMeta);
				inv.setItem(14, skull);

				ItemStack bannedBySkull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
				SkullMeta bannedBySkullMeta = (SkullMeta) bannedBySkull.getItemMeta();
				bannedBySkullMeta.setOwner(p.getName());
				bannedBySkullMeta.setDisplayName(p.getDisplayName());
				List<String> bannedBySkullLore = new ArrayList<String>();
				bannedBySkullLore.add(p.getDisplayName());
				bannedBySkullMeta.setLore(bannedBySkullLore);
				bannedBySkull.setItemMeta(bannedBySkullMeta);
				inv.setItem(12, bannedBySkull);

				ItemStack jesus = new ItemStack(Material.WATER_BUCKET);
				ItemMeta jesusMeta = jesus.getItemMeta();
				List<String> jesusLore = new ArrayList<String>();
				jesusLore.add("§cBan " + target.getDisplayName() + " §cfor jesus hacking");
				jesusMeta.setLore(jesusLore);
				jesusMeta.setDisplayName("§fJesus Hacking");
				jesus.setItemMeta(jesusMeta);
				inv.setItem(19, jesus);

				ItemStack speedHack = new ItemStack(Material.SUGAR);
				ItemMeta speedHackMeta = speedHack.getItemMeta();
				speedHackMeta.setDisplayName("§fSpeed Hacking");
				List<String> speedHackLore = new ArrayList<String>();
				speedHackLore.add("§cBan " + target.getDisplayName() + "§c for speed hacking");
				speedHackMeta.setLore(speedHackLore);
				speedHack.setItemMeta(speedHackMeta);
				inv.setItem(20, speedHack);

				ItemStack pvpHacking = new ItemStack(Material.IRON_SWORD);
				ItemMeta pvpHackingMeta = pvpHacking.getItemMeta();
				List<String> pvpHackingLore = new ArrayList<String>();
				pvpHackingLore.add("§cBan " + target.getDisplayName() + " §cfor PvP hacking");
				pvpHackingMeta.setDisplayName("§fPvP Hacking");
				pvpHackingMeta.setLore(pvpHackingLore);
				pvpHacking.setItemMeta(pvpHackingMeta);
				inv.setItem(21, pvpHacking);

				ItemStack flyHack = new ItemStack(Material.FEATHER);
				ItemMeta flyHackMeta = flyHack.getItemMeta();
				List<String> flyHackLore = new ArrayList<String>();
				flyHackLore.add("§cBan " + target.getDisplayName() + " §cfor fly hacking");
				flyHackMeta.setLore(flyHackLore);
				flyHackMeta.setDisplayName("§fFly Hacking");
				flyHack.setItemMeta(flyHackMeta);
				inv.setItem(22, flyHack);

				ItemStack xray = new ItemStack(Material.DIAMOND_ORE);
				ItemMeta xrayMeta = xray.getItemMeta();
				List<String> xrayLore = new ArrayList<String>();
				xrayLore.add("§cBan " + target.getDisplayName() + " §cfor xray");
				xrayMeta.setLore(xrayLore);
				xrayMeta.setDisplayName("§fXray Hacking");
				xray.setItemMeta(xrayMeta);
				inv.setItem(23, xray);

				ItemStack chestESP = new ItemStack(Material.CHEST);
				ItemMeta chestESPMeta = chestESP.getItemMeta();
				List<String> chestESPLore = new ArrayList<String>();
				chestESPLore.add("§cBan " + target.getDisplayName() + " §cfor ChestESP");
				chestESPMeta.setLore(chestESPLore);
				chestESPMeta.setDisplayName("§fChestESP");
				chestESP.setItemMeta(chestESPMeta);
				inv.setItem(24, chestESP);

				ItemStack bhop = new ItemStack(Material.RABBIT_FOOT);
				ItemMeta bhopMeta = bhop.getItemMeta();
				List<String> bhopLore = new ArrayList<String>();
				bhopLore.add("§cBan " + target.getDisplayName() + " §cfor bhop hacking");
				bhopMeta.setLore(bhopLore);
				bhopMeta.setDisplayName("§fBhop Hacking");
				bhop.setItemMeta(bhopMeta);
				inv.setItem(25, bhop);

				ItemStack otherHack = new ItemStack(Material.BARRIER);
				ItemMeta otherHackMeta = otherHack.getItemMeta();
				List<String> otherHackLore = new ArrayList<String>();
				otherHackLore.add("§cBan " + target.getDisplayName() + " §cfor other");
				otherHackMeta.setLore(otherHackLore);
				otherHackMeta.setDisplayName("§fOther");
				otherHack.setItemMeta(otherHackMeta);
				inv.setItem(32, otherHack);

				ItemStack duping = new ItemStack(Material.GOLD_BLOCK);
				ItemMeta dupingMeta = duping.getItemMeta();
				List<String> dupingLore = new ArrayList<String>();
				dupingLore.add("§cBan " + target.getDisplayName() + " §cfor duping");
				dupingMeta.setLore(dupingLore);
				dupingMeta.setDisplayName("§fDuping");
				duping.setItemMeta(dupingMeta);
				inv.setItem(30, duping);

				ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
				ItemMeta blackGlassMeta = blackGlass.getItemMeta();
				blackGlassMeta.setDisplayName("§0");
				blackGlass.setItemMeta(blackGlassMeta);
				ItemStack whiteGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
				ItemMeta whiteGlassMeta = whiteGlass.getItemMeta();
				whiteGlassMeta.setDisplayName("§0");
				whiteGlass.setItemMeta(whiteGlassMeta);
				ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
				ItemMeta blueGlassMeta = blueGlass.getItemMeta();
				blueGlassMeta.setDisplayName("§0");
				blueGlass.setItemMeta(blueGlassMeta);

				inv.setItem(0, blackGlass);
				inv.setItem(1, blackGlass);
				inv.setItem(2, whiteGlass);
				inv.setItem(3, whiteGlass);
				inv.setItem(4, blueGlass);
				inv.setItem(5, whiteGlass);
				inv.setItem(6, whiteGlass);
				inv.setItem(7, blackGlass);
				inv.setItem(8, blackGlass);
				inv.setItem(9, blackGlass);
				inv.setItem(17, blackGlass);
				inv.setItem(18, blueGlass);
				inv.setItem(26, blueGlass);
				inv.setItem(27, blackGlass);
				inv.setItem(35, blackGlass);
				inv.setItem(36, blackGlass);
				inv.setItem(37, blackGlass);
				inv.setItem(38, whiteGlass);
				inv.setItem(39, whiteGlass);
				inv.setItem(40, blueGlass);
				inv.setItem(41, whiteGlass);
				inv.setItem(42, whiteGlass);
				inv.setItem(43, blackGlass);
				inv.setItem(44, blackGlass);

				p.openInventory(inv);
			} else {
				p.sendMessage("§cYou don't have permission to use this command!");
				return true;
			}
		}

		return true;
	}
}