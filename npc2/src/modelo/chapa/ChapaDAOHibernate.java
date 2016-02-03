package modelo.chapa;

import java.util.List;

import modelo.HibernateUtil;
import modelo.aluno.Aluno;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChapaDAOHibernate implements ChapaDAO{
	private Session sessao;
	private Transaction transacao;
	@Override
	public Chapa salvarChapa(Chapa registro) throws HibernateException{
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();			
			this.transacao = this.sessao.beginTransaction();
			this.sessao.saveOrUpdate(registro);
			for (int i = 0; i < registro.getIntegrante().size(); i++) {
				this.sessao.saveOrUpdate(registro.getIntegrante().get(i));
			}
			
			this.transacao.commit();
		} catch (HibernateException ex) {
			this.transacao.rollback();
			System.out.println("Não foi possivel inserir! Erro:" + ex.getMessage());
			throw ex;
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out
						.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}
		
		return registro;
	}
	public Chapa alterarChapa(Chapa registro) throws HibernateException{
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();			
			this.transacao = this.sessao.beginTransaction();
			this.sessao.saveOrUpdate(registro);
			for (int i = 0; i < registro.getIntegrante().size(); i++) {
				this.sessao.update(registro.getIntegrante().get(i));
			}
			
			this.transacao.commit();
		} catch (HibernateException ex) {
			this.transacao.rollback();
			System.out.println("Não foi possivel inserir! Erro:" + ex.getMessage());
			throw ex;
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out
						.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}
		
		return registro;
	}
	@Override
	public List<Chapa> listarChapas() {
		List<Chapa> registros = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Chapa as x");
			registros = query.list();
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta da lista"
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}
	
		return registros;
	}
	@Override
	public Chapa getChapa(long id) {
		Chapa c = null;	
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Chapa as x where x.id = :id");
			query.setLong("id",id);
			c = (Chapa) query.uniqueResult();
			
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta: "
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}
		return c;
	}
	@Override
	public Chapa jaEstaEmChapa(long idAluno) {
		Chapa c = null;	
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Integrante as x where x.id = :id");
			query.setLong("id",idAluno);
			Integrante i = (Integrante) query.uniqueResult();
			if(i!= null){
				c = i.getChapa();
			}
			
		} catch (HibernateException ex) {
			System.out.println("Erro ao fazer consulta: "
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}
		return c;
	}
	@Override
	public void remover(Chapa chapa) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(chapa);
			this.transacao.commit();
		} catch (HibernateException ex) {
			this.transacao.rollback();
			System.out.println("Não foi possível deletar a turma. Erro:"
					+ ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen())
					this.sessao.close();
			} catch (Throwable ex) {
				System.out
						.println("Erro ao fechar a sessão:" + ex.getMessage());
			}
		}

	}

}
