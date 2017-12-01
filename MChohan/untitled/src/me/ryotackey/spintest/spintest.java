package me.ryotackey.spintest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class spintest extends JavaPlugin implements CommandExecutor{

    @Override
    public void onEnable(){

        getCommand("test").setExecutor(this::onCommand);

    }

    @Override
    public void onDisable(){}

    public Inventory placeMenu = Bukkit.createInventory(null, 54, "TEST");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        spin(p);
        spin2(p);
        spin3(p);

        return true;
    }

    public void spin(Player p) {

        int[] slot2 = {20, 21, 22, 23, 24, 29, 30, 31, 32, 33, 38, 39, 40, 41, 42, 48, 49, 50};

        ItemStack cup = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 12);
        ItemMeta cupmeta = cup.getItemMeta();
        cupmeta.setDisplayName(" ");
        cup.setItemMeta(cupmeta);

        for (int i = 0; i < 18; i++){

            placeMenu.setItem(slot2[i], cup);

        }

        ItemStack nul = new ItemStack(Material.AIR);

        p.openInventory(placeMenu);
        Bukkit.getServer().broadcastMessage("Inventory created & opened");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            // your code

            int count = 0;
            int[] slot = {20, 21, 22, 23, 24, 29, 30, 31, 32, 33, 38, 39, 40, 41, 42, 48, 49, 50};

            @Override
            public void run() {

                if (count != 0 && count <= 2) {
                    for (int i = 0; i < 54; i++) {
                        placeMenu.setItem(i, nul);
                    }
                }

                if (count != 0 && count <= 2) {
                    for (int i = 0; i < 18; i++) {
                        placeMenu.setItem(slot[i]-9*count, cup);
                    }
                }

                count++;

                if (count == 4){
                    return;
                }
            }

        }, 0, 5);
    }
    public void spin2(Player p){

        ItemStack nul = new ItemStack(Material.AIR);

        ItemStack cup = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 12);
        ItemMeta cupmeta = cup.getItemMeta();
        cupmeta.setDisplayName(" ");
        cup.setItemMeta(cupmeta);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            int[] slot = {3, 4, 5, 11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 29, 30, 31, 32, 33};

            int count = 0;

            @Override
            public void run() {

                if (count == 4) {
                    placeMenu.setItem(2, nul);
                    placeMenu.setItem(6, nul);

                    placeMenu.setItem(29, cup);
                    placeMenu.setItem(33, cup);
                }

                if (count > 4 && count <= 6){
                    for (int i = 0; i < 54; i++) {
                        placeMenu.setItem(i, nul);
                    }
                }

                if (count > 4 && count <= 6){
                    for (int i = 0; i < 18; i++) {
                        placeMenu.setItem(slot[i]+9*(count-4), cup);
                    }
                }

                count++;

                if (count == 20) {
                    return;
                }
            }

        }, 0, 5);
    }

    public void spin3(Player p){

        ItemStack nul = new ItemStack(Material.AIR);

        ItemStack cup = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 12);
        ItemMeta cupmeta = cup.getItemMeta();
        cupmeta.setDisplayName(" ");
        cup.setItemMeta(cupmeta);

        ItemStack[] diceitem = new ItemStack[6];
        ItemMeta[] dicemeta = new ItemMeta[6];

        for (int i = 0; i < 6; i++) {
            diceitem[i] = new ItemStack(Material.DIAMOND_HOE, 1, (short) (i + 863));
            dicemeta[i] = diceitem[i].getItemMeta();
            dicemeta[i].setUnbreakable(true);
            dicemeta[i].addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            dicemeta[i].addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            String s = String.valueOf(i + 1);
            dicemeta[i].setDisplayName(ChatColor.BOLD + s);
            diceitem[i].setItemMeta(dicemeta[i]);
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            int count = 0;

            int[] slot = {15, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 51};
            int[] slot2 = {6, 14, 15, 16, 22, 23, 24, 25, 26, 32, 33, 34, 42};

            Random dice = new Random();
            int spindice = dice.nextInt(6);

            @Override
            public void run() {

                if (count >= 8 && count <= 9){
                    for (int i = 0; i < 54; i++) {
                        placeMenu.setItem(i, nul);
                        placeMenu.setItem(49, diceitem[spindice]);
                    }
                }

                if (count == 8){
                    for (int i = 0; i < 13; i++){
                        placeMenu.setItem(slot[i], cup);

                    }
                }

                if (count == 9){
                    for (int i = 0; i < 13; i++){
                        placeMenu.setItem(slot2[i], cup);
                    }
                }

                count++;
                if (count == 20){
                    for (int i = 0; i < 54; i++) {
                        placeMenu.setItem(i, nul);
                    }
                    p.closeInventory();
                    return;
                }
            }

        }, 0, 5);

    }
}




