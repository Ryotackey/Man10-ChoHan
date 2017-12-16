package red.man10.man10_chohan;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Man10_Chohan extends JavaPlugin {

    public static boolean setup;

    public static ArrayList<UUID> chou = new ArrayList<>();
    public static ArrayList<UUID> han = new ArrayList<>();

    public FileConfiguration config;

    public double bal;

    public double totalbal;

    public VaultManager val = null;

    public newgame New;

    public int timer;

    public final String prefex = "§f§l[§d§lMa§f§ln§a§l10§c§l丁§b§l半§f§l]";

    @Override
    public void onEnable() {
        getCommand("mc").setExecutor(this);
        saveDefaultConfig();
        config = getConfig();
        val = new VaultManager(this);
        New = new newgame(this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        UUID playeruuid = p.getUniqueId();

        int MaxPlayer = config.getInt("MaxPlayer");

        switch (args.length) {
            case 0:
                p.sendMessage("§e§l------------§0§l[§d§lMa§f§ln§a§l10§c§l丁§b§l半§0§l]§e§l------------");
                p.sendMessage("§e現在の状況:§f(§c丁:" + chou.size() + "人§f, §b半:" + han.size() + "人§f)(§6合計" + jpnBalForm(totalbal) + "円§f)");
                p.sendMessage("§e§l----------------------------------");
                p.sendMessage("§c/mc c§f:§c丁(偶数に賭ける)");
                p.sendMessage("§b/mc h§f:§b半(奇数に賭ける)");
                p.sendMessage("§6/mc new [金額]§f:§6[金額]で丁半ゲームを開催する");

                break;

            case 1:

                double playerbet = val.getBalance(playeruuid);

                if (args[0].equalsIgnoreCase("c")) {
                    if (setup == true) {
                        if (playerbet < bal) {
                            p.sendMessage("§e§l[Man10丁半]§4§lお金が足りません");
                            return false;
                        }else {
                            if (chou.size() < MaxPlayer) {

                                for (int i = 0; i < chou.size(); i++) {

                                    if (chou.get(i) == playeruuid) {
                                        p.sendMessage("§e§l[Man10丁半]§4§lもうすでに丁に賭けています");
                                        return true;
                                    }
                                }

                                for (int i = 0; i < han.size(); i++) {

                                    if (han.get(i) == playeruuid) {
                                        han.remove(i);
                                        chou.add(playeruuid);
                                        Bukkit.broadcastMessage(prefex + "§c§l" + p.getDisplayName() +"§cさんが丁に賭けました§f§l(§c§l丁:" + chou.size() + "人§f§l, §b§l半:" + han.size() + "人§f§l)(§6§l合計" + jpnBalForm(totalbal) + "円§f§l)");
                                        return true;
                                    }
                                }

                                chou.add(playeruuid);
                                val.withdraw(playeruuid, bal);
                                totalbal += bal;
                                Bukkit.broadcastMessage(prefex + "§c§l" + p.getDisplayName() +"§cさんが丁に賭けました§f§l(§c§l丁:" + chou.size() + "人§f§l, §b§l半:" + han.size() + "人§f§l)(§6§l合計" + jpnBalForm(totalbal) + "円§f§l)");

                                return true;
                            } else {
                                p.sendMessage(prefex + "§4§lもう定員です");
                                return true;
                            }
                        }
                    } else {
                        p.sendMessage(prefex + "§4§l丁半は開始されてません");
                        return false;

                    }
                }

                if (args[0].equalsIgnoreCase("h")) {
                    if (setup == true) {
                        if (playerbet < bal) {
                            p.sendMessage(prefex + "§4§lお金が足りません");
                            return false;
                        }else {
                            if (han.size() < MaxPlayer) {
                                for (int i = 0; i < han.size(); i++) {

                                    if (han.get(i) == playeruuid) {
                                        p.sendMessage(prefex + "§4§lもうすでに半に賭けています");
                                        return true;
                                    }
                                }

                                for (int i = 0; i < chou.size(); i++) {

                                    if (chou.get(i) == playeruuid) {
                                        chou.remove(i);
                                        han.add(playeruuid);
                                        Bukkit.broadcastMessage(prefex + "§b" + p.getDisplayName() +"§bさんが半に賭けました§f§l(§c§l丁:" + chou.size() + "人§f§l, §b§l半:" + han.size() + "人§f§l)(§6§l合計" + jpnBalForm(totalbal) + "円§f§l)");
                                        return true;
                                    }
                                }

                                han.add(playeruuid);
                                val.withdraw(playeruuid, bal);
                                totalbal += bal;
                                Bukkit.broadcastMessage(prefex + "§b" + p.getDisplayName() +"§bさんが半に賭けました§f§l(§c§l丁:" + chou.size() + "人§f§l, §b§l半:" + han.size() + "人§f§l)(§6§l合計" + jpnBalForm(totalbal) + "円§f§l)");

                                return true;
                            } else {
                                p.sendMessage(prefex + "§4§lもう定員です");
                                return true;
                            }
                        }

                    } else {
                        p.sendMessage(prefex + "§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    String[] chouplayer = new String[chou.size()];

                    String[] hanplayer = new String[han.size()];

                    if (setup == true) {
                        p.sendMessage(prefex + "§a§l" + jpnBalForm(bal) + "円丁半");
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
                        p.sendMessage(prefex + "§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("cancel")){
                    if (setup == true){
                        if(p.hasPermission("man10chohan.mc.cancel")){

                            gameclear();

                            Bukkit.getServer().broadcastMessage(prefex + "§a§l丁半がキャンセルされました");

                        }else {
                            p.sendMessage(prefex + "§4§l権限がないのでキャンセルできません");
                            return false;
                        }
                    }else {
                        p.sendMessage(prefex + "§4§l丁半は開始されてません");
                        return false;
                    }
                }

                if (args[0].equalsIgnoreCase("reload")){
                    if (p.hasPermission("man10chohan.mc.cancel")) {

                        reloadConfig();
                        config = getConfig();
                        p.sendMessage(prefex + "§aReload complete");
                        return true;
                    }else {
                        p.sendMessage(prefex + "§4§l権限がないのでreloadできません");
                        return false;
                    }

                }
                break;

            case 2:
                if (args[0].equalsIgnoreCase("new")) {
                    if (setup == true) {

                        p.sendMessage(prefex + "§4§l丁半はすでに開始されています");
                        return true;

                    } else {
                        try {
                            bal = Double.parseDouble(args[1]);
                        }catch (NumberFormatException mc){
                            sender.sendMessage(prefex + "§4§l金額を指定してください.");
                            return true;

                        }

                        setup = New.Start();
                    }
                }
                break;
        }
        return false;
    }

    String jpnBalForm(double val){
        long val2 = (long) val;

        String addition = "";
        String form = "万";
        long man = val2/10000;
        if(val >= 100000000){
            man = val2/100000000;
            form = "億";
            long mann = (val2 - man * 100000000) / 10000;
            addition = mann + "万";
        }
        return man + form + addition;
    }

    public void gameclear(){

        for (int i = 0; i < chou.size(); i++) {
            val.deposit(chou.get(i), bal);
        }

        for (int i = 0; i < han.size(); i++) {
            val.deposit(han.get(i), bal);
        }

        chou.clear();
        han.clear();

        totalbal = 0;
        setup = false;
        bal = 0;
        timer = 0;
    }

    public void gamefinish(){

        chou.clear();

        han.clear();

        totalbal = 0;
        setup = false;
        bal = 0;

    }

}

