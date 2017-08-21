package by.htp.spring_tags.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeans {
	private static final String PROP_FILE = "countries.properties";

	private Properties countries;
	private InputStream input;

	@Bean
	public Properties countries() {

		countries = new Properties();
		 
		try {
		
			input = this.getClass().getResourceAsStream(PROP_FILE);
			countries.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return countries;
	}
}
