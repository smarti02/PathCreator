package github.smarti02.pathcreator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PathPlayer{
	Player player;
	Material material;
	Integer radius;
	Block block;
	Integer meta;
	public PathPlayer(Player player, Material block, Integer radius, int meta) {
		super();
		this.player = player;
		this.material = block;
		this.radius = radius;
		this.meta = meta;
	}

	public Player getPlayer() {
		return player;
	}

	public Material getMaterial() {
		return material;
	}
	
	public Integer getRadius() {
		return radius;
	}
	
	public Block getBlock(){
		return block;
	}
	public void setMaterial(Material block) {
		this.material = block;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}

	@Override
	public boolean equals(Object e){
		if(e instanceof Player){
			return this.player.equals(e);
		}else if(e instanceof PathPlayer){
			return this.player.equals(((PathPlayer)e).getPlayer());
		}
		return false;
	}
	
}
