package es.cic.taller.blackjack;

public enum Palo {
	PICAS("Picas", 1),
	DIAMANTES("Diamantes", 2),
	TREBOL("Trebol", 3),
	CORAZONES("Corazones", 4),
	ESPECIAL("Especial", 5);

	
	public static final int CUANTAS_CARTA_POR_PALO = 13;

	private final String texto;
	private final int numero;
	
	
	Palo(String texto, int numero)
	{
		this.texto = texto;
		this.numero = numero;
	}
	
	public String getTexto() {
		return this.texto;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
}
