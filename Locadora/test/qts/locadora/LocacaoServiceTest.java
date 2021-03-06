package qts.locadora;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

import qts.locador.exception.JogoSemEstoqueException;
import qts.locadora.service.LocacaoService;
import qts.locadora.util.DataUtil;



public class LocacaoServiceTest{

	@Test
	public void test() throws Exception{
		Cliente cliente = new Cliente("Antonio");
		Jogo jogo = new Jogo("Harry Potter",10, 0);
		LocacaoService locacaoService = new LocacaoService();
		Locacao locacao;
		
		try {
	
			locacao = locacaoService.alugarJogo(cliente, jogo); 
			Assert.assertTrue(locacao.getJogo().getNome().equals(jogo.getNome())); 
			Assert.assertTrue(locacao.getCliente().getNome().equals(cliente.getNome())); 
			Assert.assertTrue("Erro no valor do jogo", locacao.getValor()==jogo.getValor());
			Assert.assertTrue(new DataUtil().verificarDataIguais(locacao.getRetirada(),new Date()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	} 
	

	@Test (expected = JogoSemEstoqueException.class)
	public void testSemEstoque() throws Exception{
		Cliente cliente = new Cliente("Bob");
		Jogo jogo = new Jogo("GTA", 100.0, 0);
		LocacaoService lser = new LocacaoService();
		Locacao locacao;
		locacao = lser.alugarJogo(cliente, jogo);
	}
}
