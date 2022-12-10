package br.com.biblioteca.model.project.enumerator;

import br.com.biblioteca.util.EnumCommons;

public enum RiskRating implements EnumCommons<String> {
	LOW("baixo risco"),
	MEDIUM("médio risco"),
	HIGH("alto risco");

	private final String value;

	RiskRating(final String value) {
		this.value = value;
	}
	@Override public String getValue() {
		return value;
	}
}
