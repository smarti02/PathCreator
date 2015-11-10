package github.smarti02.pathcreator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author Stephanie Martinez
 *
 */
public class PathCreatorPlugin extends JavaPlugin{
	public static JavaPlugin plugin;
	public static PathController controller = new PathController();
	@Override
	public void onEnable() {
		this.getCommand("path").setExecutor(new PathCommand());
		this.getCommand("path").setTabCompleter(new PathTabCompleter());
		Bukkit.getPluginManager().registerEvents(controller,PathCreatorPlugin.plugin);
	}
	
	@Override
	public void onDisable() {

	}
	
	@Override
	public void onLoad() {
		plugin = this;
	}
}
