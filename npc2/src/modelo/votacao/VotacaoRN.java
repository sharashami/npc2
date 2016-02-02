package modelo.votacao;


import modelo.FactoryDAO;

public class VotacaoRN {
	private VotacaoDAO alunoDAO;
	
	public VotacaoRN(){
		alunoDAO = FactoryDAO.criarVotacaoDAO();
	}
	
}
