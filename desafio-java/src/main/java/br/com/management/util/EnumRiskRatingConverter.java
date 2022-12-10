package br.com.management.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.management.model.project.enumerator.RiskRating;

@Converter
public class EnumRiskRatingConverter implements AttributeConverter<RiskRating, String> {

	@Override
	public String convertToDatabaseColumn(RiskRating attribute) {
		if (attribute != null) {
			return attribute.getValue();
		}
		return null;
	}

	@Override
	public RiskRating convertToEntityAttribute(String dbData) {
		for (RiskRating e : RiskRating.values()) {
			if (e.getValue().equals(dbData)) {
				return e;
			}
		}
		return null;
	}
}
