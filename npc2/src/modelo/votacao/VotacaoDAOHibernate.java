package modelo.votacao;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class VotacaoDAOHibernate implements VotacaoDAO{
	private Session sessao;
	private Transaction transacao;

}
