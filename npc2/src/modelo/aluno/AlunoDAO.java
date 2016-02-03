package modelo.aluno;

import java.util.List;

import org.hibernate.HibernateException;
public interface AlunoDAO {
	public Aluno carregarAluno(Aluno aluno) throws HibernateException, Throwable;
	public List<Aluno> listarAlunos();
	public Aluno salvarAluno(Aluno aluno);
	public Aluno autenticarAluno(Aluno aluno);
	public Aluno getAlunoPelaMatricula(String matricula);	
	
	
}
