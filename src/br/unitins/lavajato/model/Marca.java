package br.unitins.lavajato.model;

public enum Marca {

	VOLKSWAGEN(1, "Volkswagen"), 
	CHEVROLET(2, "Chevrolet"),
	FIAT(3, "Fiat"),
	FORD(4, "Ford"),
	HONDA(5, "Honda");
	
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
