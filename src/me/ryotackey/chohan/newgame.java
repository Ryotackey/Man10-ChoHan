package me.ryotackey.chohan;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class newgame {
    public ArrayList<Player> chou = new ArrayList<>();
    public ArrayList<Player> han = new ArrayList<>();

    public boolean Start(){
        Bukkit.getServer().broadcastMessage("§e§l[Man10 丁半]§a丁半が始められました!");
        return true;
    }
}
