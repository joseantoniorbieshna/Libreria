package utilidades;

import java.util.regex.Pattern;

public class Validators {
	public static boolean isNaturalNumber(String texto) {
		return Pattern.matches("[0-9]{0,}", texto);
	}
	public static boolean isRealNumber(String texto) {
		return Pattern.matches("[0-9]{0,}\\.[0-9]{0,2}", texto);
	}
	public static boolean isPhraseWithoutSymbolsAndNumber(String texto) {
		return Pattern.matches("^[a-z A-Z ñ Ñ]+$", texto);
	}
}
