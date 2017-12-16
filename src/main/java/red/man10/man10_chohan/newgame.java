package red.man10.man10_chohan;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static red.man10.man10_chohan.Man10_Chohan.*;

public class newgame {

    private Man10_Chohan plugin;

    public newgame(Man10_Chohan plugin){
        this.plugin = plugin;
    }

    public boolean Start(){
        plugin.timer = plugin.config.getInt("Waittime");
        Bukkit.getServer().broadcastMessage(plugin.prefex +"§6§l" + plugin.jpnBalForm(plugin.bal) + "円§a§l丁半が始められました!§f§l[/mc]");
        onStartTimer();
        return true;
    }


    public void onStartTimer(){

        new BukkitRunnable(){
            @Override
            public void run() {
                if (setup == true) {
                    if (plugin.timer >= -2) {
                        if (plugin.timer % 10 == 0 || plugin.timer <= 5) {
                            Bukkit.getServer().broadcastMessage(plugin.prefex + "§aBET受付終了まであと" + plugin.timer + "秒");
                        }
                    }

                    if (plugin.timer == 0){
                        if (chou.size() == 0|| han.size() == 0){
                            plugin.gameclear();
                            Bukkit.getServer().broadcastMessage(plugin.prefex +"§4§l人数が集まらなかったため中止しました");

                            plugin.gameclear();

                            cancel();
                            return;
                        }else {
                            Bukkit.broadcastMessage(plugin.prefex + "§a§lサイコロを振っています…  §f§l§k123");
                            if (plugin.timer == -3) {
                                Game();
                                cancel();
                                return;
                            }
                        }
                    }

                }else {
                    cancel();
                    return;
                }
                plugin.timer--;
            }
        }.runTaskTimer(plugin,0,20);
    }

    public void Game(){

        Player[] chouplayer = new Player[chou.size()];
        Player[] hanplayer = new Player[han.size()];

        double payout;

        Random rdice = new Random();
        int dice = rdice.nextInt(6) + 1;
        String chouhan;

        if (dice % 2 == 0){
            chouhan = "丁";
            payout = plugin.totalbal / chou.size();
        }else {
            chouhan = "半";
            payout = plugin.totalbal / han.size();
        }

        Bukkit.broadcastMessage(plugin.prefex + "§f結果: §e§l" + dice +"§c§l" + chouhan + "の勝利！");

        if (chouhan == "丁") {
            for (int i = 0; i < chou.size(); i++) {
                chouplayer[i] = Bukkit.getPlayer(chou.get(i));
                plugin.val.deposit(chou.get(i), payout);

                Bukkit.broadcastMessage(plugin.prefex + "§c§l" + chouplayer[i].getName() + "さん§a§l勝利！§f" + plugin.jpnBalForm(plugin.bal) + "円§f§l⇒§6§l" + plugin.jpnBalForm(payout) + "円");
            }

            for (int i = 0; i < han.size(); i++){
                hanplayer[i].sendMessage(plugin.prefex + "§cあなたは負けました");
            }
            plugin.gamefinish();

        }else {
            Bukkit.broadcastMessage(plugin.prefex + "§f結果: §e§l" + dice +"§b§l" + chouhan + "の勝利！");

            for (int i = 0; i < han.size(); i++) {
                hanplayer[i] = Bukkit.getPlayer(han.get(i));
                plugin.val.deposit(han.get(i), payout);

                Bukkit.broadcastMessage(plugin.prefex + "§b§l" + hanplayer[i].getName() + "さん§a§l勝利！§f" + plugin.jpnBalForm(plugin.bal) + "円§f§l⇒§6§l" + plugin.jpnBalForm(payout) + "円");
            }

            for (int i = 0; i < chou.size(); i++){
                chouplayer[i].sendMessage(plugin.prefex + "§cあなたは負けました");
            }

            plugin.gamefinish();
        }

    }

}
