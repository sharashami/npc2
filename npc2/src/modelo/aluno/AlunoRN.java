package modelo.aluno;

import java.util.List;

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

	public Aluno autenticarAluno(Aluno aluno) throws HibernateException, Throwable{
		return alunoDAO.autenticarAluno(aluno);
	}
	
	public List<Aluno> listarAlunos(){
		return alunoDAO.listarAlunos();
	}

	public void salvar( Aluno aluno){
		
		alunoDAO.salvarAluno(aluno);
	}
	
}
