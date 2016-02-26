package br.com.cubo;

public class Assasinato {

	private String armaUtilizada;
	private String quemFoiAssasinato;
	
	public Assasinato(String quemFoiAssasinato, String armaUtilizada){
		this.armaUtilizada = armaUtilizada;
		this.quemFoiAssasinato = quemFoiAssasinato;
	}
	
	
	public String getArmaUtilizada() {
		return armaUtilizada;
	}
	public void setArmaUtilizada(String armaUtilizada) {
		this.armaUtilizada = armaUtilizada;
	}
	public String getQuemFoiAssasinato() {
		return quemFoiAssasinato;
	}
	public void setQuemFoiAssasinato(String quemFoiAssasinato) {
		this.quemFoiAssasinato = quemFoiAssasinato;
	}
	

}
