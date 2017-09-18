import java.util.Properties;
import java.io.FileInputStream;

/**
 * Load Config from config.properties. 
 * <p>tmpPath, dotForWindows, dotForLinux.
 * @author HanYue
 *
 */
public class Config {
	public static String tmpPath,dotForWindows,dotForLinux;
	
	/**
	 * load config.properties when start.
	 */
	static {
		loadConfig();
	}
	
	/**
	 * load config.properties at the root directory of project.
	 */
	private static void loadConfig() {
		String propertiesFilename="config.properties";
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(propertiesFilename));
		}catch(Exception e) {
			throw new RuntimeException("Load properties file Failed");
		}
		tmpPath=p.getProperty("tmpPath");
		dotForWindows=p.getProperty("dotForWindows");
		dotForLinux=p.getProperty("dotForLinux");
		
		//prepare();
	}
	
	/**
	 * Create temp directory if not existed(not used now).
	 */
	private static void prepare() {
		try {
			Runtime.getRuntime().exec("cmd /c if not exist \""+tmpPath+"\" md \""+tmpPath+"\"").waitFor();
		}catch(Exception e) {
			throw new RuntimeException("Failed to create tmp folder.");
		}
	}
}
