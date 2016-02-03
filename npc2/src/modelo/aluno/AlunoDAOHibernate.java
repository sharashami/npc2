package modelo.aluno;

import java.util.List;

import modelo.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlunoDAOHibernate implements AlunoDAO{
	private Session sessao;
	private Transaction transacao;

	@Override
	public Aluno carregarAluno(Aluno aluno) {
		
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Aluno as x where x.id = :id");
			query.setLong("id",aluno.getId());
			aluno = (Aluno) query.uniqueResult();
			
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta da lista de alunos: "
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sess√£o:" + ex.getMessage());
			}
		}
		return aluno;
	}

	@Override
	public List<Aluno> listarAlunos() {
		List<Aluno> alunos = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Aluno as x");
			alunos = query.list();
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta da lista"
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sess„o:" + ex.getMessage());
			}
		}
	
		return alunos;
	}	
	@Override
	public Aluno salvarAluno(Aluno aluno) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();			
			this.transacao = this.sessao.beginTransaction();
			this.sessao.saveOrUpdate(aluno);
			this.transacao.commit();
		} catch (HibernateException ex) {
			this.transacao.rollback();
			System.out.println("N„o foi possivel inserir! Erro:" + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out
						.println("Erro ao fechar a sess„o:" + ex.getMessage());
			}
		}
		
		return aluno;
	}

	@Override
	public Aluno autenticarAluno(Aluno aluno) {

		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Aluno as x where x.matricula = :mat and x.senha = :senha ");
			query.setString("mat",aluno.getMatricula());
			query.setString("senha",aluno.getSenha());
			aluno = (Aluno) query.uniqueResult();
			
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta da lista: "
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sess√£o:" + ex.getMessage());
			}
		}
		return aluno;
	}

	@Override
	public Aluno getAlunoPelaMatricula(String matricula) {
		Aluno aluno = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Aluno as x where x.matricula LIKE :mat");
			query.setString("mat",matricula);
			aluno = (Aluno) query.uniqueResult();
			
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta: "
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sess√£o:" + ex.getMessage());
			}
		}
		return aluno;
	}
}
