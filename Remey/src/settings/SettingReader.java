package settings;

import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import settings.yaml.object.Settings;


public class SettingReader {
	private static Settings settings = null;

	private SettingReader(){}

	public static Settings getSettings() throws Exception{
		if(settings == null){
			Yaml yamlSettings = new Yaml(new Constructor(Settings.class));
			try(InputStream inputStream = new FileInputStream("./settings.yml")){

				settings = yamlSettings.load(inputStream);
			}catch(Exception e){
				throw e;
			}
		}
		return settings;
	}
}
