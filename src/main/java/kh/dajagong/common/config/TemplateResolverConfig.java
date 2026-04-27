package kh.dajagong.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class TemplateResolverConfig {
	@Bean
	public ClassLoaderTemplateResolver boardResolver() {
		ClassLoaderTemplateResolver bResolver = new ClassLoaderTemplateResolver();
		bResolver.setPrefix("templates/views/book-review/");
		bResolver.setSuffix(".html");
		bResolver.setTemplateMode(TemplateMode.HTML);
		bResolver.setCacheable(false);
		bResolver.setCheckExistence(true);
		
		return bResolver;
	}
}
