package br.com.biblioteca.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.biblioteca.model.project.enumerator.Status;

@Converter
public class EnumStatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status attribute) {
		if (attribute != null) {
			return attribute.getValue();
		}
		return null;
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		for (Status e : Status.values()) {
			if (e.getValue().equals(dbData)) {
				return e;
			}
		}
		return null;
	}
}
