package github.smarti02.pathcreator;


import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author Stephanie
 */
public class PathCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Must be a player to use path
		if(!(sender instanceof Player)){
			sender.sendMessage("You must be a Player to use this command!");
			return true;
		}
		
		if(PathCreatorPlugin.controller.hasPlayer((Player)sender) && args.length == 0){
			PathCreatorPlugin.controller.removePlayer((Player)sender);
			sender.sendMessage("Path stopped");
		}else{
			// /path [block] [radius] 
			if(args.length != 2){
				sender.sendMessage("Incorrect number of arguments.");
				return false;
			}
			
			String[] split = args[0].split(":");
			
			//get the Material
			Material material = Material.getMaterial(replaceusrInput(split[0]).toUpperCase());
			
			Integer meta = 0;
			
			if(split.length>1){
				meta = Integer.valueOf(split[1]);
			}
			
			if(material == null){
				sender.sendMessage("Could not find block "+ split[0]);
				return true;
			}
			
			//get the radius
			int radius;
			try{
				radius = Integer.valueOf(args[1]);
			}catch(NumberFormatException e){
				sender.sendMessage("Could not load radius. "+e.getMessage());
				return false;
			}
			
			if(radius < 0 ){
				sender.sendMessage("Radius must be at least 0");
				return true;
			}
			
			if(Arrays.asList(PathCreatorPlugin.controller.doNotReplace).contains(material)){
				sender.sendMessage("Cannot create a path with "+split[0]);
				return true;
			}
			
			PathCreatorPlugin.controller.addPlayer(new PathPlayer((Player)sender, material, radius, meta));
			sender.sendMessage("Path Started");
			
		}
		
		
		return true;
	}
	
	private String replaceusrInput(String usrMaterial){
		String usrMaterialOG = usrMaterial;
		
		//prep
		usrMaterial = usrMaterial.toLowerCase();
		usrMaterial.replace("_","");
		usrMaterial.replace("-","");
		usrMaterial.replace(" ","");
		
		switch(usrMaterial){
			case "stonebrick":
				return "SMOOTH_BRICK"; 
		}
	
		return usrMaterialOG;
	}
	
}
