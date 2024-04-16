/*
 * FormatterRegistrar.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class FormatterRegistrar implements WebMvcConfigurer {

	private final AntiSpamSystemService service;


	@Autowired
	public FormatterRegistrar(final AntiSpamSystemService service) {
		this.service = service;
	}

	@Override
	public void addFormatters(final FormatterRegistry registry) {
		registry.addFormatter(this.spamFormatter());
	}

	@Bean
	public SpamFormatter spamFormatter() {
		return new SpamFormatter(this.service);
	}

}
