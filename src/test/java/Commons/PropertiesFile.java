package Commons;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFile {

	private static Properties properties;
	private static FileInputStream fileIn;

	static String projectPath = System.getProperty("user.dir") + "/";
	private static String propertiesFilePathRoot = "src/test/java/recources/config.properties";

	public static void setPropertiesFile() {
		properties = new Properties();
		try {
			fileIn = new FileInputStream(projectPath + propertiesFilePathRoot);
			// Load properties file
			properties.load(fileIn);
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}

	public static String getPropValue(String KeyProp) {
		String value = null;
		try {
			// get values from properties file
			value = properties.getProperty(KeyProp);
			System.out.println(value);
			return value;
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return value;
	}

}
