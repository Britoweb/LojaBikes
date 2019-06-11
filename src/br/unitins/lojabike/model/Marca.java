package br.unitins.lojabike.model;

public enum Marca {

	MONARK(1, "Volkswagen"), 
	CALOI(2, "Chevrolet"),
	SPECIALIZED(3, "Fiat");
	
	private int value;
	private String label;
	
	Marca(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma marca a partir de um valor inteiro
	public static Marca valueOf(int value) {
		for (Marca marca : Marca.values()) {
			if (marca.getValue() == value) {
				return marca;
			}
		}
		return null;
	}
}
