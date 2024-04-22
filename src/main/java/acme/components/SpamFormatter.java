/*
 * PhoneFormatter.java
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

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import acme.client.helpers.MessageHelper;
import acme.client.helpers.StringHelper;

@Component
public class SpamFormatter implements Formatter<String>

{

	private final AntiSpamSystemService service;


	@Autowired
	public SpamFormatter(final AntiSpamSystemService service) {
		this.service = service;
	}

	// Formatter<AbstractEntity> interface ---------------------------------------------

	@Override
	public String print(final String object, final Locale locale) {
		return object;
	}

	@Override
	public String parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		String regex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;

		List<String> phrases = this.service.findAllSpam();
		if (phrases.isEmpty())
			return text;
		double threshold = 0.1;
		int count = 0;

		for (int i = 0; i < phrases.size(); i++) {
			String phrase = phrases.get(i).replaceAll("\\s+", "\\\\s+");
			StringBuilder regexBuilder = new StringBuilder("\\b(");
			regexBuilder.append(phrase);
			regexBuilder.append(")\\b");
			regex = regexBuilder.toString();
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			matcher = pattern.matcher(text);

			if (matcher.find())
				count++;
		}
		double ratio = count / (double) phrases.size();
		if (ratio > threshold) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			System.out.println("ERROR POR SPAM");
			throw new ParseException(0, errorMessage);
		}
		return text;
	}

}
