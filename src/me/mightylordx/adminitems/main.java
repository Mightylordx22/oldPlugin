package me.mightylordx.adminitems;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private SignMenu signMenu;
	private static Main instance;
	public Permission banGuiBan = new Permission("bangui.ban");
	public Permission banGuiTempban = new Permission("bangui.tempban");
	public Permission banGuiAll = new Permission("bangui.*");

	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getConsoleSender().sendMessage("Starting");
		getCommand("tempban").setExecutor(new GUI());
		getCommand("ban").setExecutor(new GUI());
		Bukkit.getPluginManager().registerEvents(new GUIClick(), this);
		Bukkit.getPluginManager().addPermission(banGuiAll);
		Bukkit.getPluginManager().addPermission(banGuiBan);
		Bukkit.getPluginManager().addPermission(banGuiTempban);
		this.saveDefaultConfig();
		this.signMenu = new SignMenu(this);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("Stopping");
	}

	public static Main getInstance() {
		return instance;
	}
	
    public SignMenu getSignMenu() {
        return this.signMenu;
   }

}
