package qts.locadora;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

import qts.locador.exception.JogoEstoqueInvalidoException;
import qts.locador.exception.JogoSemEstoqueException;
import qts.locadora.service.LocacaoService;
import qts.locadora.util.DataUtil;

public class LocacaoServiceTest{
	@Test
	public void teste() throws Exception{
		Cliente cliente = new Cliente("Carol");
		Jogo jogo = new Jogo("The Sims 4", 70.00, 1);

		LocacaoService locacaoService = new LocacaoService();
		Locacao locacao;

		locacao = locacaoService.alugarJogo(cliente, jogo);
		
		Assert.assertTrue(locacao.getJogo().getNome().equals(jogo.getNome()));
		Assert.assertTrue(locacao.getCliente().getNome().equals(cliente.getNome()));
		Assert.assertTrue("Erro no valor do jogo", locacao.getValor()==jogo.getValor());
		Assert.assertTrue(new DataUtil().verificarDatasIguais(locacao.getRetirada(),new Date()));


	
	}

	@Test (expected = JogoSemEstoqueException.class)
	public void testSemEstoque() throws Exception {
		Cliente cliente = new Cliente("Carol");
		Jogo jogo = new Jogo("The Sims 4", 70.00, 0);

	LocacaoService locacaoService = new LocacaoService();
	Locacao locacao;
	locacao = locacaoService.alugarJogo(cliente, jogo);
	}
	
	@Test (expected = JogoEstoqueInvalidoException.class)
	public void testeum() throws Exception {
		Cliente cliente = new Cliente("Carol");
		Jogo jogo = new Jogo("The Sims 4", 70.00, -2);

	LocacaoService locacaoService = new LocacaoService();
	Locacao locacao;
	locacao = locacaoService.alugarJogo(cliente, jogo);
	}
}
