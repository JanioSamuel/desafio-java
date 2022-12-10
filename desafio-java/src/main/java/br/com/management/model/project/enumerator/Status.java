package br.com.management.model.project.enumerator;

import br.com.management.util.EnumCommons;

public enum Status implements EnumCommons<String> {
	IN_ANALYSIS("em análise"),
	ANALYSIS_DONE("análise realizada"),
	APPROVED("análise aprovada"),
	ANALYSIS_STARTED("iniciado"),
	PLANNED("planejado"),
	RUNNING("em andamento"),
	FINISHED("encerrado"),
	CANCELED("cancelado");

	private final String value;

	Status(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
