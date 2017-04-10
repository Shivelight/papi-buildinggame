package pw.khan.BuildingGamePlaceholder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.stefvanschiedev.buildinggame.api.BuildingGame;
import com.gmail.stefvanschiedev.buildinggame.managers.stats.StatManager;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.Arena;
import com.gmail.stefvanschiedev.buildinggame.utils.stats.Stat;
import com.gmail.stefvanschiedev.buildinggame.utils.stats.StatType;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class BuildingGameExpansion extends PlaceholderExpansion{
	
    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(getPlugin()) != null;
    }


    @Override
    public String getAuthor() {
        return "Franchise";
    }

    @Override
    public String getIdentifier() {
        return "buildinggame";
    }

    @Override
    public String getPlugin() {
        return "BuildingGame";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
    	
    	if (p == null){
             return "";
        }
    	 
    	if (identifier.startsWith("stat_")){
    		String a = identifier.split("stat_")[1];
    		
    		switch (a) {
			case "first":
			case "second":
			case "third":
			case "placed":
			case "broken":
			case "walked":
			case "plays":
				break;
			default:
				return "Wrong Stat (first/second/third/placed/broken/plays)";
			}
    		
    		Stat s = StatManager.getInstance().getStat(p, StatType.valueOf(a.toUpperCase()));
    		
    		if (s == null){
    			return "0";
    		}
    		
    		return "" + s.getValue();
    	}
    	
    	// buildinggame_arena_<name>_<type>
    	// <name> = your arena name
    	// <type> = player
    	if (identifier.startsWith("arena_")){
    		String[] a = identifier.split("_");
    		
    		// arena
    		String b = a[2];
    		// type
    		String c = a[3];
    		
    		Arena arena = BuildingGame.getArena(b);
    		
    		if (arena == null){
    			return "arena not found";
    		}
    		
    		switch (c) {
			case "player":
				return "" + arena.getPlayers();
			case "subject":
				return arena.getSubject();
			case "mode":
				return "" + arena.getMode();
			case "state":
				return arena.getState().name();
			default:
				return "Wrong Stat (player/subject/mode/state)";
			}
    		
    	}
    	return null;
    }
}
