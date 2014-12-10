package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {
	
	@Test
	public void deveCriar10SessoesParaPeriodoDe10DiasPeriodicidadeDiaria() {
		Espetaculo e = new Espetaculo();
		LocalDate dtInicio = new LocalDate(2014,12,10);
		LocalDate dtFim    = new LocalDate(2014,12,20);
		LocalTime horario  = new LocalTime(22,0);
		
		List<Sessao> sessoes = e.criaSessoes(dtInicio, dtFim, horario, Periodicidade.DIARIA);
		
		for (Sessao sessao : sessoes) {
			Assert.assertTrue(sessao.getInicio().equals(dtInicio.toDateTime(horario)));
			dtInicio = dtInicio.plusDays(1);
		}
		Assert.assertEquals(sessoes.size(), 11);
	}
		
	@Test
	public void deveCriar2SessaoParaPeriodoDe10DiasPeriodicidadeSemanal() {
		Espetaculo e = new Espetaculo();
		LocalDate dtInicio = new LocalDate(2014,12,10);
		LocalDate dtFim    = new LocalDate(2014,12,20);
		LocalTime horario  = new LocalTime(22,0);
		
		List<Sessao> sessoes = e.criaSessoes(dtInicio, dtFim, horario, Periodicidade.SEMANAL);
		
		for (Sessao sessao : sessoes) {
			Assert.assertTrue(sessao.getInicio().equals(dtInicio.toDateTime(horario)));
			dtInicio = dtInicio.plusWeeks(1);
		}
		Assert.assertEquals(sessoes.size(), 2);
	}
	
	
	@Test
	public void deveCriar1SessoaoParaMesmoDiaPeriodicidadeDiaria() {
		Espetaculo e = new Espetaculo();
		LocalDate dtInicio = new LocalDate(2014,12,10);
		LocalDate dtFim    = new LocalDate(2014,12,10);
		LocalTime horario  = new LocalTime(22,0);
		
		List<Sessao> sessoes = e.criaSessoes(dtInicio, dtFim, horario, Periodicidade.DIARIA);
		
		for (Sessao sessao : sessoes) {
			Assert.assertTrue(sessao.getInicio().equals(dtInicio.toDateTime(horario)));
		}
		Assert.assertEquals(sessoes.size(), 1);
	}
	
	@Test
	public void deveCriar5SessoesParaPeriodoDe30DiasPeriodicidadeSemanal() {
		Espetaculo e = new Espetaculo();
		LocalDate dtInicio = new LocalDate(2014,01,01);
		LocalDate dtFim    = new LocalDate(2014,01,31);
		LocalTime horario  = new LocalTime(22,0);
		
		List<Sessao> sessoes = e.criaSessoes(dtInicio, dtFim, horario, Periodicidade.SEMANAL);
		
		for (Sessao sessao : sessoes) {
			Assert.assertTrue(sessao.getInicio().equals(dtInicio.toDateTime(horario)));
			dtInicio = dtInicio.plusWeeks(1);
		}
		Assert.assertEquals(sessoes.size(), 5);
	}
	
	@Test
	public void naoDeveCriarSessaoQuandoDataFinalInferiorDataInicial() {
		Espetaculo e = new Espetaculo();
		LocalDate dtInicio = new LocalDate(2014,12,20);
		LocalDate dtFim    = new LocalDate(2014,12,10);
		LocalTime horario  = new LocalTime(22,0);
		
		Assert.assertTrue(dtInicio.toLocalDateTime(horario).isAfter((dtFim.toLocalDateTime(horario))));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
