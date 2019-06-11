package br.unitins.lojabike.model;

public enum Categoria {
	
	TRILHA(1, "Trilha"), 
	PASSEIO(2, "Passeio"), 
	CORRIDA(3, "Corrida");
	
	private int value;
	private String label;
	
	Categoria(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma categoria a partir de um valor inteiro
	public static Categoria valueOf(int value) {
		for (Categoria cat : Categoria.values()) {
			if (cat.getValue() == value) {
				return cat;
			}
		}
		return null;
	}

}
