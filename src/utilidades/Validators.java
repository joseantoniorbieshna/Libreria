package utilidades;

import java.util.regex.Pattern;

public class Validators {
	public static boolean isNaturalNumber(String texto) {
		return Pattern.matches("[0-9]{1,}", texto);
	}
	public static boolean isRealNumber(String texto) {
		return Pattern.matches("[0-9]{1,}\\.[0-9]{0,}", texto);
	}
}
