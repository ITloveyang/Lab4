import java.util.Properties;
import java.io.FileInputStream;

public class Config {
	public static String tmpPath,dotForWindows;
	
	static {
		loadConfig();
	}
	
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
	}
}
