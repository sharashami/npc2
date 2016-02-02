package modelo.chapa;

import java.util.List;

import modelo.FactoryDAO;
import modelo.aluno.Aluno;

public class ChapaRN {
	private ChapaDAO chapaDAO;
	
	public ChapaRN(){
		chapaDAO = FactoryDAO.criarChapaDAO();
	}
	public Chapa salvar( Chapa chapa){
		
		return chapaDAO.salvarChapa(chapa);
	}

	public List<Chapa> listarChapas(){
		return chapaDAO.listarChapas();
	}
}
