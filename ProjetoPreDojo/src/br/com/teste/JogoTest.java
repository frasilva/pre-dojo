package br.com.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.cubo.Jogo;
import br.com.cubo.Partida;

public class JogoTest {
	
	
	@Test
    public void deveRetornarNumeroDaPartidaSendoIniciada() {
		Assert.assertEquals(Long.valueOf(11348965), new Jogo().getNumeroPartida("- New match 11348965 has started"));
    }
	
	@Test
	public void deveRetornarUmaData() throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar calendar =Calendar.getInstance();
		String date = "23/04/2013 15:34:22";
		calendar.setTime(simpleDateFormat.parse(date));
		Assert.assertEquals(calendar,new Jogo().getData("23/04/2013 15:34:22 - New match 11348965 has started"));
		
	}
	
	@Test
	public void deveRetornarUmaPartidaSeoNumeroExistir() throws ParseException{
		List<Partida> partidas = new ArrayList<Partida>();
		Partida partida1 = new Partida();
		partida1.setNumeroDaPartida(1l);
		Partida partida2 = new Partida();
		partida2.setNumeroDaPartida(2l);
		partidas.add(partida1);
		partidas.add(partida2);
		Jogo jogo = new Jogo();
		jogo.setPartidas(partidas);
		
		Assert.assertEquals(partida1,jogo.getPartidaByNumeroPartida(1l));
		
	}
	
	@Test
	public void deveRetornarNuloCasoOnumeroDaPartidaNaoExista() throws ParseException{
		List<Partida> partidas = new ArrayList<Partida>();
		Partida partida1 = new Partida();
		partida1.setNumeroDaPartida(1l);
		Partida partida2 = new Partida();
		partida2.setNumeroDaPartida(2l);
		partidas.add(partida1);
		partidas.add(partida2);
		Jogo jogo = new Jogo();
		jogo.setPartidas(partidas);
		
		Assert.assertNotEquals(partida1,jogo.getPartidaByNumeroPartida(3l));
		
	}
	
	@Test
	public void deveRetornarOSujeitoDaAcao() throws ParseException{
		Assert.assertEquals("Roman", new Jogo().getJogadorPraticanteDaAcao("- Roman killed Nick using M16"));
	}
	
	@Test
	public void deveRetornarAVitimaDaAcao() throws ParseException{
		Assert.assertEquals("Nick", new Jogo().getJogadorQueSofreAAcao("- Roman killed Nick using M16"));
	}
	
	@Test
	public void deveRetornarACausaDoAssassinado() throws ParseException{
		Assert.assertEquals("M16", new Jogo().getArmaUtilizadaPeloAssassino("- Roman killed Nick using M16"));
	}
	
	public Calendar getData(String linha) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(simpleDateFormat.parse(linha.substring(0, linha.lastIndexOf("-")-1)));
		return calendar;
	}
	

	@Test
    public void deveRetornarFalseCasoALinhaNaoIndiqueInicioDumaPartida() {
		Jogo jogo = new Jogo();
		Assert.assertFalse(jogo.iniciandoNovaPartida("ended"));
    }
	
	@Test
    public void deveRetornarTrueCasoALinhaIndiqueInicioDumaPartida() {
		Jogo jogo = new Jogo();
		Assert.assertTrue(jogo.iniciandoNovaPartida("started"));
    }
	
	@Test
    public void deveRetornarFalseCasoALinhaNaoIndiqueTerminoDumaPartida() {
		Jogo jogo = new Jogo();
		Assert.assertFalse(jogo.encerrandoPartida("dended"));
    }
	
	@Test
    public void deveRetornarTrueCasoALinhaIndiqueTerminoDumaPartida() {
		Jogo jogo = new Jogo();
		Assert.assertTrue(jogo.encerrandoPartida("ended"));
    }
	

	
	
	
	
	
	
}
