package br.com.cubo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Jogo {
	
	private List<Partida> partidas = new ArrayList<Partida>();
	private Long ultimaPartida= null;
	
	public void jogar() throws IOException, ParseException {
		FileInputStream arquivo = new FileInputStream("c:\\Log.txt");
		InputStreamReader leitor = new InputStreamReader(arquivo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine().toLowerCase();
		
		while(linha != null) {
			if(iniciandoNovaPartida(linha.substring(linha.lastIndexOf(" ")))){
				Partida partida = new Partida();
				partida.setDataInicio(getData(linha));
				partida.setNumeroDaPartida(getNumeroPartida(linha));
				ultimaPartida = getNumeroPartida(linha); 
				partidas.add(partida);
			}else if(encerrandoPartida(linha.substring(linha.lastIndexOf(" ")))){
				Partida partida = getPartidaByNumeroPartida(getNumeroPartida(linha));
				partida.setDataTermino(getData(linha));
				partida.setPartidaEmAndamento(Boolean.FALSE);
			}else{
				String nome =getJogadorPraticanteDaAcao(linha);
				String nomeDeQuemSAofreAAcao =getJogadorQueSofreAAcao(linha);
				String causaDaMorte = getArmaUtilizadaPeloAssassino(linha);
				
				adicionarJogada(nome, nomeDeQuemSAofreAAcao,causaDaMorte);
				
			}
			linha = buffer.readLine();
		}
		exibirRanking();
	}

	
	public Boolean iniciandoNovaPartida(String palavra){
		return palavra.trim().equalsIgnoreCase("started");
	}
	
	public Boolean encerrandoPartida(String palavra){
		return palavra.trim().equalsIgnoreCase("ended");
	}
	
	public Long getNumeroPartida(String linha){
		String linhaAux =linha.substring(linha.toLowerCase().lastIndexOf("match "), linha.lastIndexOf(" "));
		String linhaAux2 = linhaAux.substring(6,linhaAux.lastIndexOf(" "));
		return Long.parseLong(linhaAux2);
	}
	
	public Calendar getData(String linha) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(simpleDateFormat.parse(linha.substring(0, linha.lastIndexOf("-")-1)));
		return calendar;
	}
	
	public Partida getPartidaByNumeroPartida(Long numero){
		for(Partida p: partidas){
			if(p.getNumeroDaPartida().equals(numero)){
				return p;
			}
		}
		return null;
	}
	
	public String getJogadorPraticanteDaAcao(String linha){
		String aux =linha.substring(linha.indexOf("-")+2); 
		String jogador =aux.substring(0,aux.indexOf(" ")); 
		return jogador;
	}
	
	public String getJogadorQueSofreAAcao(String linha){
		String aux =linha.substring(linha.indexOf("killed ")+7,linha.lastIndexOf(" ")); 
		String jogador = aux.substring(0,aux.indexOf(" "));
		return jogador;
	}
	
	public String getArmaUtilizadaPeloAssassino(String linha){
		String arma = linha.substring(linha.lastIndexOf(" ")+1);
		return arma;
	}
	
	public void adicionarJogada(String ativo, String passivo, String causaDaMorte){
		Partida partida=getPartidaByNumeroPartida(ultimaPartida);
		Jogador jogador = getJogadorByNome(ativo);
		if(jogador==null){
			jogador = new Jogador();
			partida.getJogadores().add(jogador);
		}
		jogador.setNome(ativo);
		
		jogador.getAssasinatos().add(new Assasinato(passivo, causaDaMorte));
		
		
		if(!ativo.equalsIgnoreCase("<world>")){
			jogador = getJogadorByNome(passivo);
			if(jogador==null){
				jogador = new Jogador();
				partida.getJogadores().add(jogador);
			}
			jogador.setNome(passivo);
			
			jogador.getMortes().add(new Morte(ativo, causaDaMorte));
		}
		
		
	}
	
	public String getArmaPreferidaDoVencedor(Long numeroPartida){
		Partida partida= getPartidaByNumeroPartida(numeroPartida);
		Jogador jogadorVencedor= partida.getJogadores().get(0);
		for(Jogador j: partida.getJogadores()){
			if(jogadorVencedor.getAssasinatos().size()< j.getAssasinatos().size()){
				jogadorVencedor= j;
			}
		}
		return jogadorVencedor.getAssasinatos().get(0).getArmaUtilizada();
	}
	
	
	public Jogador getJogadorByNome(String nome){
		Partida partida= getPartidaByNumeroPartida(ultimaPartida);
		for(Jogador j: partida.getJogadores()){
			if(j.getNome().equalsIgnoreCase(nome)){
				return j;
			}
		}
		return null;
	}
	
	public Boolean oJogadorInvicto(Long numeroDaPartida){
		Partida partida= getPartidaByNumeroPartida(numeroDaPartida);
		Jogador jogadorVencedor= partida.getJogadores().get(0);
		for(Jogador j: partida.getJogadores()){
			if(jogadorVencedor.getAssasinatos().size()< j.getAssasinatos().size()){
				jogadorVencedor= j;
			}
		}
		if(jogadorVencedor.getMortes().size()==0){
			return true;
		}
		return false;
	}
	
	public void exibirRanking(){
		for(Partida  p: partidas){
			System.out.println("Numero da partida: "+p.getNumeroDaPartida());
			System.out.println("Data de Inicio da partida: "+p.getDataInicio().getTime()+"\n");
			for(Jogador j: p.getJogadores()){
				System.out.println("Nome do Jogador: "+j.getNome());
				System.out.println("numero de assassinatos: "+j.getAssasinatos().size());
				System.out.println("Nome de vezes que morreu: "+j.getMortes().size()+"\n");
				
				
			}
			System.out.println("A partida está em andamento: "+p.isPartidaEmAndamento());
			System.out.println("Arma favorida do vencedor: "+getArmaPreferidaDoVencedor(p.getNumeroDaPartida()));
			if(oJogadorInvicto(p.getNumeroDaPartida())){
				System.out.println("O jogador Vencedor Recebe um award, por não ter morrido nenhuma vez nessa partida favorida do vencedor");	
			}
			System.out.println("Data de Termino da partida: "+p.getDataTermino().getTime()+"\n");
		}
	}


	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public Long getUltimaPartida() {
		return ultimaPartida;
	}

	public void setUltimaPartida(Long ultimaPartida) {
		this.ultimaPartida = ultimaPartida;
	}

}
