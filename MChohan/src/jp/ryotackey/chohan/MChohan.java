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
    public void onEnable() {
        //起動時
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("mch").setExecutor(this::onCommand);
    }

    @Override
    public void onDisable() {
        //終了時
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        Inventory inv = Bukkit.createInventory(null, 54, "§b§lMan10 丁半");
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName("§e§l部屋を作る");
        item.setItemMeta(itemmeta);
        inv.setItem(53, item);
        p.openInventory(inv);


        return true;

    }

    public void betMenuInv(Player p){
        Inventory betinv = Bukkit.createInventory(null, 54, "§e§l掛け金を決めてください");

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

        ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta blankmeta = blank.getItemMeta();
        blankmeta.setDisplayName(" ");
        blank.setItemMeta(blankmeta);

        for (int i = 9; i < 54; i++) {
            betinv.setItem(i, blank);
        }

        ItemStack clear = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta clearmeta = clear.getItemMeta();
        clearmeta.setDisplayName("§c§lクリア");
        clear.setItemMeta(clearmeta);

        ItemStack ok = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta okmeta = ok.getItemMeta();
        okmeta.setDisplayName("§a§l確認");
        ok.setItemMeta(okmeta);

        betinv.setItem(21, num[0]);
        betinv.setItem(20, num[1]);
        betinv.setItem(19, num[2]);
        betinv.setItem(30, num[3]);
        betinv.setItem(29, num[4]);
        betinv.setItem(28, num[5]);
        betinv.setItem(39, num[6]);
        betinv.setItem(38, num[7]);
        betinv.setItem(37, num[8]);
        betinv.setItem(47, num[9]);

        betinv.setItem(46, clear);
        betinv.setItem(48, ok);

        p.openInventory(betinv);
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equalsIgnoreCase("§b§lMan10 丁半")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.DIAMOND) {

               betMenuInv(p);

            }
        }

        if (e.getInventory().getName().equals("§e§l" + "掛け金を決めてください")) {
            e.setCancelled(true);
            Inventory betinv = e.getInventory();
            String val = "";

            if (e.getSlot() == 46){
                for (int i = 0; i < 9; i++){
                    betinv.setItem(i, new ItemStack(Material.AIR));
                }
            }

            if (e.getInventory().getItem(0) != null){
                p.sendMessage("§c§l掛け金の上限です！");
                e.setCancelled(true);
                return;
            }

            if (e.getSlot() == 19) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(19));

                val = "7";
            }
            if (e.getSlot() == 20) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(20));

                val = "8";
            }
            if (e.getSlot() == 21) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(21));


                val = "9";
            }
            if (e.getSlot() == 28) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(28));

                val = "4";
            }
            if (e.getSlot() == 29) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(29));

                val = "5";
            }
            if (e.getSlot() == 30) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(30));

                val = "6";
            }
            if (e.getSlot() == 37) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(37));

                val = "1";
            }
            if (e.getSlot() == 38) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(38));

                val = "2";
            }
            if (e.getSlot() == 39) {
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(39));

                val = "3";
            }
            if (e.getSlot() == 47) {
                if(betinv.getItem(8) == null){
                    e.setCancelled(true);
                    return;
                }
                moveD(betinv);
                betinv.setItem(8, betinv.getItem(47));

                val = "0";
            }

            e.setCancelled(true);

        }

    }

    public void moveD(Inventory betinv) {
        if (betinv.getItem(8) != null) {
            moveDisplay(betinv);
        }
    }

    public void moveDisplay(Inventory betinv) {
        betinv.setItem(0, betinv.getItem(1));
        betinv.setItem(1, betinv.getItem(2));
        betinv.setItem(2, betinv.getItem(3));
        betinv.setItem(3, betinv.getItem(4));
        betinv.setItem(4, betinv.getItem(5));
        betinv.setItem(5, betinv.getItem(6));
        betinv.setItem(6, betinv.getItem(7));
        betinv.setItem(7, betinv.getItem(8));
    }
}


