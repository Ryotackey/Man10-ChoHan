package me.ryotackey.chohan;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Chohan_test extends JavaPlugin {

    public static boolean setup;

    public static ArrayList<UUID> chou = new ArrayList<>();
    public static ArrayList<UUID> han = new ArrayList<>();

    public static Player owner;

    public static FileConfiguration config;

    public static double bal;


    
    public VaultManager val = new VaultManager(this);

    @Override
    public void onEnable() {
        getCommand("mc").setExecutor(this);
        saveDefaultConfig();
        config = getConfig();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        UUID playeruuid = p.getUniqueId();

        newgame New = new newgame(this);

        int MaxPlayer = config.getInt("MaxPlayer");

        switch (args.length) {
            case 0:
                p.sendMessage("§e§l[Man10 丁半]§4§l/mc [c/h/new/list]");
                break;

            case 1:

                double playerbet = val.getBalance(playeruuid);

                if (args[0].equalsIgnoreCase("c")) {
                    if (setup == true) {
                        if (playerbet < bal) {
                            p.sendMessage("§e§l[Man10 丁半]§4§lお金が足りません");
                            return false;
                        }else {
                            if (chou.size() < MaxPlayer) {

                                for (int i = 0; i < chou.size(); i++) {

                                    if (chou.get(i) == playeruuid) {
                                        p.sendMessage("§e§l[Man10 丁半]§4§lもうすでに丁に張っています");
                                        return true;
                                    }
                                }

                                for (int i = 0; i < han.size(); i++) {

                                    if (han.get(i) == playeruuid) {
                                        han.remove(i);
                                    }
                                }

                                chou.add(playeruuid);
                                p.sendMessage("§e§l[Man10 丁半]§a§l丁に張りました");
                                return true;
                            } else {
                                p.sendMessage("§e§l[Man10 丁半]§4§lもう定員です");
                                return true;
                            }
                        }
                    } else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;

                    }
                }

                if (args[0].equalsIgnoreCase("h")) {
                    if (setup == true) {
                        if (playerbet < bal) {
                            p.sendMessage("§e§l[Man10 丁半]§4§lお金が足りません");
                            return false;
                        }else {
                            if (han.size() < MaxPlayer) {
                                for (int i = 0; i < han.size(); i++) {

                                    if (han.get(i) == playeruuid) {
                                        p.sendMessage("§e§l[Man10 丁半]§4§lもうすでに半に張っています");
                                        return true;
                                    }
                                }

                                for (int i = 0; i < chou.size(); i++) {

                                    if (chou.get(i) == playeruuid) {
                                        chou.remove(i);
                                    }
                                }

                                han.add(playeruuid);
                                p.sendMessage("§e§l[Man10 丁半]§a§l半に張りました");
                                return true;
                            } else {
                                p.sendMessage("§e§l[Man10 丁半]§4§lもう定員です");
                                return true;
                            }
                        }

                    } else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    String[] chouplayer = new String[chou.size()];

                    String[] hanplayer = new String[han.size()];

                    if (setup == true) {
                        p.sendMessage("§e§l[Man10 丁半]§a§l" + bal + "円丁半");
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
                    }else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("cancel")){
                    if (setup == true){
                        if(sender.hasPermission("man10chohan.mc.cancel")){

                            gameclear();

                            Bukkit.getServer().broadcastMessage("§e§l[Man10 丁半]§a§l丁半がキャンセルされました");

                        }else {
                            if (owner == p) {
                                gameclear();
                                Bukkit.getServer().broadcastMessage("§e§l[Man10 丁半]§a§l丁半がキャンセルされました");
                            } else {
                                p.sendMessage("§e§l[Man10 丁半]§4§lあなたは開催者でないのでキャンセルできません");
                                return false;
                            }
                        }
                    }else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("reload")){

                    reloadConfig();
                    config = getConfig();
                    p.sendMessage("§e§l[Man10 丁半]§aReload complete");
                    return true;

                }
                break;

            case 2:
                if (args[0].equalsIgnoreCase("new")) {
                    if (setup == true) {

                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半はすでに開始されています");
                        return true;

                    } else {

                        try {

                            bal = Double.parseDouble(args[1]);
                        }catch (NumberFormatException mc){
                            sender.sendMessage("§e§l[Man10 丁半]§4§l金額を指定してください.");
                            return true;

                        }

                        setup = New.Start(p);
                    }
                }
                break;
        }
        return false;
    }

    public void gameclear(){
        for (int i = 0; i < chou.size(); i++) {
            chou.remove(i);
        }

        for (int i = 0; i < han.size(); i++) {
            han.remove(i);
        }



        setup = false;
        bal = 0;
    }

}

