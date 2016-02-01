package modelo;



import modelo.aluno.AlunoDAO;
import modelo.aluno.AlunoDAOHibernate;
import modelo.chapa.ChapaDAO;
import modelo.chapa.ChapaDAOHibernate;

public class FactoryDAO {
	
	public static AlunoDAO criarAlunoDAO(){
		return new AlunoDAOHibernate();
	}

	public static ChapaDAO criarChapaDAO(){
		return new ChapaDAOHibernate();
	}
}
