package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba su mensaje: ");
		String mensaje = sc.nextLine();
		String clave = "";
		do{
			System.out.println("Escriba una clave (la longitud debe ser menor que el mensaje): ");
			clave = sc.nextLine();
		}while(clave.length() > mensaje.length());

		Cifrado nuevo = new Cifrado(clave.toUpperCase());

		String cifrado = nuevo.cifrarMensaje(mensaje.toUpperCase());
		System.out.println("Mensaje cifrado: " +cifrado);

		String decodificado = nuevo.descifrar(cifrado);
		System.out.println("Mensaje descifrado: "+ decodificado);

	}


}