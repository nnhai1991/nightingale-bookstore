package com.nightingale;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	public static final String DEFAULT_ENCODING = "UTF-8";
	private static final String defaultCountry = "SG";
	private static final String defaultLanguage = "en";

	public Application() {
		super();
		setRegisterErrorPageFilter(false);
	}

	// localResolver detected by name 'localResolver' compulsory
	@Bean(name = { "localResolver" })
	public LocaleResolver LocaleResolver() {

		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(new Locale(defaultLanguage, defaultCountry));
		return sessionLocaleResolver;
	}

	// localChangeInterceptor
	@Bean
	public LocaleChangeInterceptor localChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("locale");
		return localeChangeInterceptor;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("classpath:tiles/layout.xml");
		return tilesConfigurer;
	}

	@Bean(name = "messageSource")
	public static MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:property/strings", "classpath:property/validation",
				"classpath:property/email_content");
		messageSource.setCacheSeconds(5); // TODO: Change this in production
		messageSource.setDefaultEncoding(Application.DEFAULT_ENCODING);
		return messageSource;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
