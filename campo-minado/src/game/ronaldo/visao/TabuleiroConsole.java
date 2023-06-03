package game.ronaldo.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import game.ronaldo.excecao.ExplosaoException;
import game.ronaldo.excecao.SairException;
import game.ronaldo.modelo.Tabuleiro;

public class TabuleiroConsole {

	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();
	}

	private void executarJogo() {
		
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar(); 
				}
				
			}
			
		} catch (SairException e) {
			System.out.println(" Tchau!!!");
		}finally {
			entrada.close();
		}
		
	}

	private void cicloDoJogo() {
		// TODO Auto-generated method stub
		try {
			
			while(!tabuleiro.objetivoAlcancado()){	
				System.out.println(tabuleiro.toString());
				String digitado = capturarValorDigitado("Digite (x,y): ");
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
				.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if ("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!!!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
			
		}
		return digitado;
	}
	
	
}
