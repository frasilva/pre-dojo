package br.com.cubo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Partida {

	private Long numeroDaPartida;
	private Calendar DataInicio;
	private Calendar DataTermino;
	private Boolean partidaEmAndamento  = Boolean.TRUE; 
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	public Long getNumeroDaPartida() {
		return numeroDaPartida;
	}
	public void setNumeroDaPartida(Long numeroDaPartida) {
		this.numeroDaPartida = numeroDaPartida;
	}
	public Calendar getDataInicio() {
		return DataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
		DataInicio = dataInicio;
	}
	public Calendar getDataTermino() {
		return DataTermino;
	}
	public void setDataTermino(Calendar dataTermino) {
		DataTermino = dataTermino;
	}
	public Boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}
	public void setPartidaEmAndamento(Boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	public Boolean getPartidaEmAndamento() {
		return partidaEmAndamento;
	}
	
}
