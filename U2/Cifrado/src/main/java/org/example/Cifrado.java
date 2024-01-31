package org.example;

public class Cifrado {

	private String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	private String clave;

	String mensajeConClave = "";
	String mensajeCifrado = "";
	String mensajeDescifrado = "";

	public Cifrado(String clave) {
		this.clave = clave;
	}

	public String getAbecedario() {
		return abecedario;
	}

	public void setAbecedario(String abecedario) {
		this.abecedario = abecedario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getMensajeCifrado() {
		return mensajeCifrado;
	}

	public void setMensajeCifrado(String mensajeCifrado) {
		this.mensajeCifrado = mensajeCifrado;
	}

	public String getMensajeDescifrado() {
		return mensajeDescifrado;
	}

	public void setMensajeDescifrado(String mensajeDescifrado) {
		this.mensajeDescifrado = mensajeDescifrado;
	}

	public String cifrarMensaje(String mensaje){
		//Cifrar: C=(P+K)mod26
		String mensajeConClave = "";

		int j = 0;
		for (int i = 0; i < mensaje.length(); i++){
			if(mensaje.charAt(i) != ' '){
				this.mensajeConClave += this.clave.charAt(j);
				j++;
			}else{
				this.mensajeConClave += " ";
			}
			if(j >= this.clave.length()){
				j  = 0;
			}
		}
//		System.out.println("Mensaje como clave: " +this.mensajeConClave);

		for (int i = 0; i < mensaje.length(); i++){
			char caracter = mensaje.toCharArray()[i];
			int posAbecedario = abecedario.indexOf(caracter);

			char caracCifrado = this.mensajeConClave.toCharArray()[i];
			int posCifradoAbecedario = abecedario.indexOf(caracCifrado);

			if(caracter != ' ' && caracCifrado != ' '){
				int res = (posAbecedario + posCifradoAbecedario) % 27;
				mensajeCifrado += abecedario.charAt(res);
			}else{
				mensajeCifrado+= " ";
			}


		}
//		System.out.println("Mensaje cifrado: " +mensajeCifrado);
//		System.out.println("-----------------------------------");
		return mensajeCifrado;
	}

	public String descifrar(String mensajeCifrado){
		String mensajeDevuelto = "";
		//Cifrar: C=(P+K)mod26
		//P=(C−K)mod26
		//LETRA REAL = (LETRA CIFRADA POS - CLAVE POS ) mod 27
//		System.out.println(this.mensajeConClave);
//		System.out.println(mensajeCifrado);

		for (int i = 0; i < mensajeCifrado.length(); i++){
			char c = mensajeCifrado.toCharArray()[i];
			char k = this.mensajeConClave.toCharArray()[i];
			//System.out.println(c);

			if(c != ' ' && k != ' '){
				int numC = this.abecedario.indexOf(c);
				int numK = this.abecedario.indexOf(k);

//				System.out.println(c+ " "+numC);
//				System.out.println(k+ " "+numK);
				int numP = (numC - numK);
				if(numP < 0){
					numP += 27;
				}
				int res = numP % 27;
				//System.out.println(res);
				mensajeDevuelto += this.abecedario.charAt(res);
			}else{
				mensajeDevuelto += " ";
			}


		}

		return mensajeDevuelto;
	}
}
