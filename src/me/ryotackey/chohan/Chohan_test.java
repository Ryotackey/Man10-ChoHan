package me.ryotackey.chohan;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Chohan_test extends JavaPlugin {

    public static boolean setup;

    @Override
    public void onEnable(){
        getCommand("mc").setExecutor(this);
    }

    @Override
    public void onDisable(){}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        newgame New = new newgame();


        switch (args.length){
            case 0:
                p.sendMessage("§e§l[Man10 丁半]§4§l/mc [c/h/new/list]");
                break;

            case 1:
                if (args[0].equalsIgnoreCase("c")){
                    if (setup == true) {
                        for (int i = 0; i < New.han.size(); i++){
                            if (New.han.get(i) == p){
                                New.han.remove(p);
                            }
                        }
                        New.chou.add(p);
                        p.sendMessage("§e§l[Man10 丁半]§a§l丁に張りました");
                    }else {
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半は開始されてません");
                        return false;
                    }
                }
                break;

            case 2:
                if (args[0].equalsIgnoreCase("new")){
                    if (setup == true){
                        p.sendMessage("§e§l[Man10 丁半]§4§l丁半はすでに開始されています");
                        break;
                    }else {
                        setup = New.Start();
                    }
                }
                break;
        }
        return true;
    }
}
