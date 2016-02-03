package modelo;



import modelo.aluno.AlunoDAO;
import modelo.aluno.AlunoDAOHibernate;
import modelo.chapa.ChapaDAO;
import modelo.chapa.ChapaDAOHibernate;
import modelo.votacao.VotacaoDAO;
import modelo.votacao.VotacaoDAOHibernate;

public class FactoryDAO {

	public static FactoryDAO instancia;
	 
	private FactoryDAO(){
		
	}
    public static FactoryDAO getInstancia() {
        if (instancia == null)
            instancia = new FactoryDAO();
        return instancia;
    }
    
	
	public AlunoDAO criarAlunoDAO(){
		return new AlunoDAOHibernate();
	}

	public ChapaDAO criarChapaDAO(){
		return new ChapaDAOHibernate();
	}

	public VotacaoDAO criarVotacaoDAO(){
		return new VotacaoDAOHibernate();
	}
	
}
