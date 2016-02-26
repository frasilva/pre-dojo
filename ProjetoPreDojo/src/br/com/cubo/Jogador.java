package br.com.cubo;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	
	private String nome;
	private List<Assasinato> assasinatos = new ArrayList<Assasinato>();
	private List<Morte> mortes = new ArrayList<Morte>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Assasinato> getAssasinatos() {
		return assasinatos;
	}
	public void setAssasinatos(List<Assasinato> assasinatos) {
		this.assasinatos = assasinatos;
	}
	public List<Morte> getMortes() {
		return mortes;
	}
	public void setMortes(List<Morte> mortes) {
		this.mortes = mortes;
	}
	

}
