package game.pokemon;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class PokemonApplication extends SpringServletContainerInitializer {

	public static void main(String[] args) {
		var context=SpringApplication.run(PokemonApplication.class, args);
		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
	}

	/*@Override
	public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
		super.onStartup(webAppInitializerClasses, servletContext);
	}*/
}
