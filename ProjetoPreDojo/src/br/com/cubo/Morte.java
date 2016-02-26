package br.com.cubo;

public class Morte {

	private String causa;
	private String quemFoiOAssasino;
	
	public Morte(String causa, String quemFoiOAssasino){
		this.causa = causa;
		this.quemFoiOAssasino = quemFoiOAssasino;
	}
	
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getQuemFoiOAssasino() {
		return quemFoiOAssasino;
	}
	public void setQuemFoiOAssasino(String quemFoiOAssasino) {
		this.quemFoiOAssasino = quemFoiOAssasino;
	}
	
	
	
}
