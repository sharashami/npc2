package modelo.votacao;


import java.util.List;

import modelo.HibernateUtil;
import modelo.chapa.Chapa;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VotacaoDAOHibernate implements VotacaoDAO{
	private Session sessao;
	private Transaction transacao;
	@Override
	public void votar(Votacao v) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();			
			this.transacao = this.sessao.beginTransaction();
			this.sessao.saveOrUpdate(v);
			this.transacao.commit();
		} catch (HibernateException ex) {
			this.transacao.rollback();
			System.out.println("Não foi possivel inserir! Erro:" + ex.getMessage());
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
	@Override
	public Votacao jaVotou(long id) {
		Votacao c = null;	
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Votacao as x where x.eleitor.id = :id");
			query.setLong("id",id);
			c = (Votacao) query.uniqueResult();
			
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
	public List<Votacao> listarVotos() {
		List<Votacao> registros = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			Query query = this.sessao
					.createQuery("select x from Votacao as x");
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

}
