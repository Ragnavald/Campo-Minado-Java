package game.ronaldo.modelo;

import java.util.ArrayList;
import java.util.List;

import game.ronaldo.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	
	 public Campo(int linha, int coluna){	
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public boolean adicionarVizinho(Campo campo) {
		
		boolean linhaDiferente = linha != campo.linha;
		boolean colunaDiferente = coluna != campo.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int  deltaLinha = Math.abs(linha - campo.linha);
		int deltaColuna = Math.abs(coluna - campo.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(campo);
			return true;
		}else if(deltaGeral == 2 && diagonal){
			vizinhos.add(campo);
			return true;
		}else {
			return false;
		}
		
	}
	
	public void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}
 public	boolean abrir(){
		if (!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				//GAME OVER
				throw new ExplosaoException();	
			}
			if (vizinhancaSegura()) {
				vizinhos.forEach(v-> v.abrir());
			}
			return true;
		}else {
			return false;
		}
	}
	
	
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(m -> m.minado);
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	public void minar() {
		minado = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(m -> m.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if (marcado) {
			return "x";
		} else if (aberto && minado) {
			return "*";
		} else if (aberto && minasNaVizinhanca() > 0) {
			return  Long.toString(minasNaVizinhanca());
		} else if (aberto) {
			return " ";
		} else {
			return "?";
		}
	}
}
