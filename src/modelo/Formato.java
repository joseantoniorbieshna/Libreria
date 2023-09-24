package modelo;

public enum Formato {
	cartone("Cartoné"),rustico("Rústico"),grapada("Grapada"),espiral("Espiral");
	
	private String textFormato;
	Formato(String textFormato){
		this.textFormato=textFormato;
	}
	public String getTextoFormato() {
		return textFormato;
	}
	public static Formato getFormatoByTexto(String text) {
		Formato formatoEncontrado = null;
		Formato[] arrayFormato = Formato.values();
		for (int i = 0; i < arrayFormato.length && formatoEncontrado==null; i++) {
			if(arrayFormato[i].getTextoFormato().equals(text))
				formatoEncontrado=arrayFormato[i];
		}
		return formatoEncontrado;
	}
	public static String[] getAllText() {
		Formato[] arrayFormato = Formato.values();
		String[] arrayTexto = new String[arrayFormato.length];
		for (int i = 0; i < arrayFormato.length ; i++) {
			arrayTexto[i] = arrayFormato[i].getTextoFormato();
		}
		return arrayTexto;
	}
}
