package model.chapa;

import model.aluno.Aluno;

public class Integrante extends Aluno{
	private String funcao;
	private String curso;
	
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
}
