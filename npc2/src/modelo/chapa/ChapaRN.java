package modelo.chapa;

import modelo.FactoryDAO;

public class ChapaRN {
	private ChapaDAO alunoDAO;
	
	public ChapaRN(){
		alunoDAO = FactoryDAO.criarChapaDAO();
	}
	
}
