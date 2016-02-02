package modelo;



import modelo.aluno.AlunoDAO;
import modelo.aluno.AlunoDAOHibernate;
import modelo.chapa.ChapaDAO;
import modelo.chapa.ChapaDAOHibernate;
import modelo.votacao.VotacaoDAO;
import modelo.votacao.VotacaoDAOHibernate;

public class FactoryDAO {
	
	public static AlunoDAO criarAlunoDAO(){
		return new AlunoDAOHibernate();
	}

	public static ChapaDAO criarChapaDAO(){
		return new ChapaDAOHibernate();
	}


	public static VotacaoDAO criarVotacaoDAO(){
		return new VotacaoDAOHibernate();
	}
	
}
