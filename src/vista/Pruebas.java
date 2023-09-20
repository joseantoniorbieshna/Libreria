package vista;

import utilidades.Validators;

public class Pruebas {
	public static void main(String[] args) {
		Validators validation= new Validators();
		System.out.println(validation.isRealNumber("111.110ds1"));
	}
}
