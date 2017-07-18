package es.cic.taller.blackjack;


public enum Numero {
	UNO(1, 1, "as"),
	DOS(2, 1, "dos"),
	TRES(3, 10, "tres"),
	CUATRO(4, 4, "cuatro"),
	CINCO(5, 5, "cinco"),
	SEIS(6, 6, "seis"),
	SIETE(7, 7, "siete"),
	OCHO(8, 8, "ocho"),
	NUEVE(9, 9, "nueve"),
	DIEZ(10, 10, "diez"),
	JOTA(11, 10, "jota"),
	REINA(12, 10, "reina"),
	REY(13, 10, "rey");
	
	private final int numero;
	private final int valor;
	private final String texto;
	
	Numero(int numero, int valor, String texto) {
		this.numero = numero;
		this.valor = valor;
		this.texto = texto;
	}

	public int getNumero() {
		return numero;
	}

	public int getValor() {
		return valor;
	}

	public String getTexto() {
		return texto;
	}
	
	public String getNumeroComoTexto() {
		if (numero < 10) {
			return "0" + numero;
		}
		return "" + numero;
	}
	
	public static Numero getNumero(int numero) {
		switch (numero) {
		case 1: return Numero.UNO;
		case 2: return Numero.DOS;
		case 3: return Numero.TRES;
		case 4: return Numero.CUATRO;
		case 5: return Numero.CINCO;
		case 6: return Numero.SEIS;
		case 7: return Numero.SIETE;
		case 8: return Numero.OCHO;
		case 9: return Numero.NUEVE;
		case 10: return Numero.DIEZ;
		case 11: return Numero.JOTA;
		case 12: return Numero.REINA;
		case 13: return Numero.REY;
		default: throw new RuntimeException("Carta no soportada");
		}
	}

}
