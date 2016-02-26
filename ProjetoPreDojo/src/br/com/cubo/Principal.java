package br.com.cubo;

import java.io.IOException;
import java.text.ParseException;

public class Principal {
	

	public static void main(String[] args) throws IOException, ParseException {
		Jogo jogo = new Jogo();
		jogo.jogar();
	}
	
}
