package model.chapa;

import model.aluno.Aluno;

public class Chapa {
	
	private Aluno criador;
	private java.util.List<Integrante> composicao;
	private String nome;
	public java.util.List<Integrante> getComposicao() {
		return composicao;
	}
	public void setComposicao(java.util.List<Integrante> composicao) {
		this.composicao = composicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
