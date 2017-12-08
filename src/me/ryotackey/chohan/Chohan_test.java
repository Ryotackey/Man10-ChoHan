package me.ryotackey.chohan;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Chohan_test extends JavaPlugin {

    public static boolean setup;

    ArrayList<UUID> chou = new ArrayList<>();
    ArrayList<UUID> han = new ArrayList<>();


    @Override
    public void onEnable() {
        getCommand("mc").setExecutor(this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        UUID playeruuid = p.getUniqueId();

        newgame New = new newgame();


        switch (args.length) {
            case 0:
                p.sendMessage("§e§l[Man10 丁半]§4§l/mc [c/h/new/list]");
                break;

            case 1:
                if (args[0].equalsIgnoreCase("c")) {
                    if (setup == true) {

                        for (int i = 0; i < chou.size(); i++){

                            if (chou.get(i) == playeruuid){
                                p.sendMessage("§e§l[Man10 丁半]§4§lもうすでに丁に張っています");
                                return true;
                            }
                        }

                        for (int i = 0; i < han.size(); i++){

                            if (han.get(i) == playeruuid){
                                han.remove(i);
                            }
                        }

                        chou.add(playeruuid);
                        p.sendMessage("§e§l[Man10 丁半]§a§l丁に張りました");
                        return true;
                    } else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;

                    }
                }

                if (args[0].equalsIgnoreCase("h")) {
                    if (setup == true) {

                        for (int i = 0; i < han.size(); i++){

                            if (han.get(i) == playeruuid){
                                p.sendMessage("§e§l[Man10 丁半]§4§lもうすでに半に張っています");
                                return true;
                            }
                        }

                        for (int i = 0; i < chou.size(); i++){

                            if (chou.get(i) == playeruuid){
                                chou.remove(i);
                            }
                        }

                        han.add(playeruuid);
                        p.sendMessage("§e§l[Man10 丁半]§a§l半に張りました");
                        return true;
                    } else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    String[] chouplayer = new String[chou.size()];

                    String[] hanplayer = new String[han.size()];

                    if (setup == true) {
                        p.sendMessage("§a§l～丁～");
                        for (int i = 0; i < chou.size(); i++) {

                            chouplayer[i] = Bukkit.getPlayer(chou.get(i)).getName();

                            p.sendMessage("§e§l" + chouplayer[i]);
                        }

                        p.sendMessage("§a§l～半～");

                        for (int i = 0; i < han.size(); i++) {

                            hanplayer[i] = Bukkit.getPlayer(han.get(i)).getName();

                            p.sendMessage("§e§l" + hanplayer[i]);
                        }
                        return true;
                    }
                }

            break;

            case 2:
                if (args[0].equalsIgnoreCase("new")) {
                    if (setup == true) {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半はすでに開始されています");
                        break;
                    } else {
                        setup = New.Start();
                    }
                }
                break;
        }
        return true;
    }


}

