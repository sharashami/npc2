package modelo.aluno;

import modelo.FactoryDAO;

import org.hibernate.HibernateException;

public class AlunoRN {
	private AlunoDAO alunoDAO;
	
	public AlunoRN(){
		alunoDAO = FactoryDAO.criarAlunoDAO();
	}
	public Aluno carregarAluno(Aluno aluno) throws HibernateException, Throwable{
		return alunoDAO.carregarAluno(aluno);
	}
	
}
