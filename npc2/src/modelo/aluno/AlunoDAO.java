package modelo.aluno;

import org.hibernate.HibernateException;
public interface AlunoDAO {
	public Aluno carregarAluno(Aluno aluno) throws HibernateException, Throwable;
	
}
