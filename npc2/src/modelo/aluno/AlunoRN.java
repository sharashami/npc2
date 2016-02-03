package modelo.aluno;

import java.util.List;

import modelo.FactoryDAO;

import org.hibernate.HibernateException;

public class AlunoRN {
	private AlunoDAO alunoDAO;
	
	public AlunoRN(){
		alunoDAO = FactoryDAO.getInstancia().criarAlunoDAO();
	}
	public Aluno carregarAluno(Aluno aluno) {
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
	public Aluno existeAluno(String matricula) {
		return alunoDAO.getAlunoPelaMatricula(matricula);
		
	}
	
}
