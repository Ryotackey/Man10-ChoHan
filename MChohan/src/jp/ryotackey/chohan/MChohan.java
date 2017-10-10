package jp.ryotackey.chohan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MChohan extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable(){
        //起動時
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("mch").setExecutor(this::onCommand);
    }

    @Override
    public void onDisable(){
        //終了時
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) return false;
        Player p = (Player)sender;

        Inventory inv = Bukkit.createInventory(null, 54, "§b§lMan10 丁半");
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName("§e§l部屋を作る");
        item.setItemMeta(itemmeta);
        inv.setItem(53, item);
        p.openInventory(inv);



        return true;

    }
   @EventHandler
    public void Inventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Inventory inv2 = Bukkit.createInventory(null, 54,  "§e§l掛け金を決めてください");

        ItemStack num[] = new ItemStack[10];
        ItemMeta nummeta[] = new ItemMeta[10];

        for (int i = 0; i < 10; i++) {
            num[i] = new ItemStack(Material.DIAMOND_HOE, 1, (short) (i + 924));
            nummeta[i] = num[i].getItemMeta();
            nummeta[i].setUnbreakable(true);
            nummeta[i].addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            nummeta[i].addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            int j = i * -1 + 9;
            String s = String.valueOf(j);
            nummeta[i].setDisplayName(ChatColor.BOLD + s);
            num[i].setItemMeta(nummeta[i]);
        }

       ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
       ItemMeta blankmeta = blank.getItemMeta();
       blankmeta.setDisplayName(" ");
       blank.setItemMeta(blankmeta);

       for(int i = 9; i < 54; i++){
           inv2.setItem(i, blank);
       }


       ItemStack clear = new ItemStack(Material.REDSTONE_BLOCK);
       ItemMeta clearmeta = clear.getItemMeta();
       clearmeta.setDisplayName("§c§lクリア");
       clear.setItemMeta(clearmeta);

       ItemStack ok = new ItemStack(Material.EMERALD_BLOCK);
       ItemMeta okmeta = ok.getItemMeta();
       okmeta.setDisplayName("§a§l確認");
       ok.setItemMeta(okmeta);

        inv2.setItem(21, num[0]);
        inv2.setItem(20, num[1]);
        inv2.setItem(19, num[2]);
        inv2.setItem(30, num[3]);
        inv2.setItem(29, num[4]);
        inv2.setItem(28, num[5]);
        inv2.setItem(39, num[6]);
        inv2.setItem(38, num[7]);
        inv2.setItem(37, num[8]);
        inv2.setItem(47, num[9]);

        inv2.setItem(46, clear);
        inv2.setItem(48, ok);


       if (e.getInventory().getName().equals("§b§lMan10 丁半")){
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.DIAMOND){

                p.openInventory(inv2);

            }
        }

        if (e.getInventory().getName().equals("§e§l" + "掛け金を決めてください")){

            int bet[] = new int[9];
            e.setCancelled(true);

            int slot = e.getSlot();

            switch (slot) {
                case 21:
                    int empty = inv2.firstEmpty();
                    bet[empty] = 9;
                    inv2.setItem(empty, num[0]);
                    p.openInventory(inv2);
                    break;

                case 20:
            }

        }
   }

}
