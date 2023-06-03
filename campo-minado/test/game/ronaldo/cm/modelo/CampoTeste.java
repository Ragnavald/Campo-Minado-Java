package game.ronaldo.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.ronaldo.excecao.ExplosaoException;
import game.ronaldo.modelo.Campo;

class CampoTeste {

	private Campo campo = new Campo(3,3);
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3,2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	@Test
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3,4);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	@Test
	void testeVizinhoDistancia1Cima() {
		Campo vizinho = new Campo(2,3);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	@Test
	void testeVizinhoDistancia1Baixo() {
		Campo vizinho = new Campo(4,3);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2,2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertFalse(result);
	}
	@Test
	void testeValorPadraoAtributoMarcado(){
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAlternarMarcacao(){
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	@Test
	void testeAlternarMarcacaoDuasChamadas(){
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirComVizinhos() {
		Campo campo22 = new Campo(2, 2);
		Campo campo11 = new Campo(1, 1);
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		assertTrue(campo22.isAberto() && campo11.isAberto());
		
	}
	@Test
	void testeAbrirComVizinhos2() {
		Campo campo22 = new Campo(2, 2);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		
		Campo campo11 = new Campo(1, 1);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		assertTrue(campo22.isAberto() && !campo11.isAberto());
		
	}
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
		assertFalse(campo.abrir());
	}
	

}
