package red.man10.man10_chohan;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static red.man10.man10_chohan.Man10_Chohan.bal;
import static red.man10.man10_chohan.Man10_Chohan.config;
import static red.man10.man10_chohan.Man10_Chohan.setup;
import static red.man10.man10_chohan.Man10_Chohan.chou;
import static red.man10.man10_chohan.Man10_Chohan.han;
import static red.man10.man10_chohan.Man10_Chohan.owner;

public class newgame {

    private Man10_Chohan plugin;

    public newgame(Man10_Chohan plugin){
        this.plugin = plugin;
    }

    int timer = config.getInt("Waittime");

    public boolean Start(Player p){
        owner = p;
        Bukkit.getServer().broadcastMessage("§e§l[Man10 丁半]§a§l" + bal + "円丁半が始められました!");
        onStartTimer();
        return true;
    }


    public void onStartTimer(){

        new BukkitRunnable(){
            @Override
            public void run() {
                if (setup = true) {
                    if (timer != 0) {
                        if (timer % 10 == 0) {
                            Bukkit.getServer().broadcastMessage("§e[Man10 丁半]§aBET受付終了まであと" + timer + "秒");
                        }
                    } else {
                        if (chou.size() == 0|| han.size() == 0){
                           plugin.gameclear();
                            Bukkit.getServer().broadcastMessage("§e§l[Man10 丁半]§4§l人数が集まらなかったため中止しました");

                            cancel();
                            return;
                        }else {
                            Game();
                            cancel();
                            return;
                        }
                    }
                }else {
                    cancel();
                    return;
                }
                timer--;
            }
        }.runTaskTimer(plugin,0,20);
    }

    public void Game(){

        Player[] chouplayer = new Player[chou.size()];
        Player[] hanplayer = new Player[han.size()];

        double totalbal = bal * (chou.size() + han.size());
        double payout = 0;

        Random rdice = new Random();
        int dice = rdice.nextInt(6) + 1;
        String chouhan;

        if (dice % 2 == 0){
            chouhan = "丁";
            payout = totalbal / chou.size();
        }else {
            chouhan = "半";
            payout = totalbal / han.size();
        }

        Bukkit.broadcastMessage("§e§l[Man10 丁半]§a§lサイコロを振って" + dice + "がでました！よって" + chouhan + "の勝ちです！");

        if (chouhan == "丁") {
            for (int i = 0; i < chou.size(); i++) {
                chouplayer[i] = Bukkit.getPlayer(chou.get(i));
                plugin.val.deposit(chou.get(i), payout);
            }

        }else {

            for (int i = 0; i < han.size(); i++) {
                hanplayer[i] = Bukkit.getPlayer(han.get(i));
                plugin.val.deposit(han.get(i), payout);
            }
        }

        plugin.gameclear();

    }

}
