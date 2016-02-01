package modelo.aluno;

import modelo.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlunoDAOHibernate implements AlunoDAO{
	private Session sessao;
	private Transaction transacao;

	@Override
	public Aluno carregarAluno(Aluno aluno) throws HibernateException, Throwable {
		
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Aluno as x where x.id = :id");
			query.setLong("id",aluno.getId());
			aluno = (Aluno) query.uniqueResult();
			
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta da lista de oficinas: "
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}
		return aluno;
	}
		
}
