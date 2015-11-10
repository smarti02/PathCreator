package github.smarti02.pathcreator;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

/**
 * Does tab completion for the creation command
 * @author Stephanie
 */
public class PathTabCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> list = new ArrayList<String>();
		
		// /Path [material] [radius]
		if(args.length == 1){
			//get a list of materials
			for(Material m : Material.values()){
				if(startsWithIgnoreCase(m.name(), args[0])){
					list.add(m.name());
				}
			}
		}		
		
		return list;
	}
	
	private static boolean startsWithIgnoreCase(String string1, String string2){
		string1 = string1.toLowerCase();
		string2 = string2.toLowerCase();
		return string1.startsWith(string2);
	}
}
