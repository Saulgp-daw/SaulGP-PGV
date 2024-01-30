package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
	public static void main(String[] args) {
		//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
		// to see how IntelliJ IDEA suggests fixing it.


		String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		String mensaje = "BUENAS TARDES";
		String clave = "PISTACHO";

		String mensajeConClave = "";
		String mensajeCifrado = "";
		int j = 0;
		for (int i = 0; i < mensaje.length(); i++){
			if(mensaje.charAt(i) != ' '){
				mensajeConClave += clave.charAt(j);
				j++;
			}else{
				mensajeConClave += " ";
			}
			if(j >= clave.length()){
				j  = 0;
			}
		}

		System.out.println("Mensaje como clave: " +mensajeConClave);

		//char caracter  =mensaje.toCharArray()[mensaje.length()-1];
		//System.out.println(caracter);


		for (int i = 0; i < mensaje.length()-1; i++){

			char caracter = mensaje.toCharArray()[i];
			System.out.println(caracter);
			int posAbecedario = abecedario.indexOf(caracter);
			System.out.println(posAbecedario);

			char caracCifrado = mensajeConClave.toCharArray()[i];
			int posCifradoAbecedario = abecedario.indexOf(caracCifrado);
			System.out.println(caracCifrado);
			System.out.println(posCifradoAbecedario);


			if(caracter != ' ' && caracCifrado != ' '){
				int res = (posAbecedario + posCifradoAbecedario) % 27;
				System.out.println("Suma: "+ res);
				mensajeCifrado += abecedario.charAt(res);
			}else{
				mensajeCifrado+= " ";
			}

			System.out.println("---------------------");
			//int indiceMensaje = abecedario.charAt(mensaje.toCharArray()[i]);
			// indiceClave = abecedario.charAt(clave.toCharArray()[i]);

			//int suma = indiceMensaje+indiceClave;

		}
		System.out.println("Mensaje cifrado: " +mensajeCifrado);




	}


}