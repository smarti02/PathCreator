package github.smarti02.pathcreator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PathController implements Listener{
	private Material[] doNotReplace = {Material.AIR, Material.WATER, Material.STATIONARY_WATER,
			Material.DOUBLE_PLANT, Material.YELLOW_FLOWER, Material.RED_ROSE, Material.CACTUS,
			Material.LONG_GRASS, Material.ARROW, Material.BIRCH_DOOR, Material.ACACIA_DOOR, 
			Material.DARK_OAK_DOOR, Material.IRON_DOOR, Material.JUNGLE_DOOR, 
			Material.SPRUCE_DOOR, Material.WOOD_DOOR, Material.FENCE, Material.ACACIA_FENCE, 
			Material.BIRCH_FENCE, Material.DARK_OAK_FENCE, Material.IRON_FENCE, 
			Material.JUNGLE_FENCE,Material.NETHER_FENCE, Material.SPRUCE_FENCE, Material.BEDROCK, Material.BOAT};
	
	private List<PathPlayer> paths = new LinkedList<PathPlayer>();
	
	public boolean hasPlayer(Player player){
		Iterator<PathPlayer> it = paths.iterator();
		
		while(it.hasNext()){
			PathPlayer p = it.next();
			if(player.equals(p.getPlayer()))
				return true;
		}
		
		return false;
	}
	
	public void addPlayer(PathPlayer player){
		paths.add(player);
	}
	
	public boolean removePlayer(Player player){
		Iterator<PathPlayer> it = paths.iterator();
		
		while(it.hasNext()){
			PathPlayer p = it.next();
			if(player.equals(p.getPlayer())){
				it.remove();
				return true;
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(!hasPlayer(e.getPlayer()) || paths.isEmpty()){
			return;
		}
		
		Iterator <PathPlayer> it = paths.iterator();
		PathPlayer player = null;
		
		while(it.hasNext()){
			player = it.next();
			if(player.equals((Player)e.getPlayer()))
					break;
		}
		
		//if the player has not moved, quit
		Block block =e.getPlayer().getLocation().getBlock(); 
		if(block.equals(player.getBlock())){
			return;
		}
		
		//the player has moved, change the block and update players block
		player.setBlock(block);
		Block below = block.getRelative(BlockFace.DOWN);
		
		//check the blocks around it
		for(int i = player.getRadius()*-1; i <= player.getRadius(); i++){
			for(int j = player.getRadius()*-1; j <= player.getRadius(); j++){
				Location replace = below.getLocation();
				replace.setX(below.getX()+i);
				replace.setZ(below.getZ()+j);
				Block rBlock = replace.getBlock();
				if(Arrays.asList(this.doNotReplace).contains(rBlock.getType())){
					continue;
				}else{
					rBlock.setType(player.getMaterial());
					rBlock.setData((byte)player.meta.intValue());
				}
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		if(hasPlayer(e.getPlayer())){
			paths.remove(e.getPlayer());
		}
	}
}
