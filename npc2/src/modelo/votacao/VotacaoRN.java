package modelo.votacao;


import java.util.List;

import modelo.FactoryDAO;
import modelo.chapa.Chapa;

public class VotacaoRN {
	private VotacaoDAO votacaoDAO;
	
	public VotacaoRN(){
		votacaoDAO = FactoryDAO.criarVotacaoDAO();
	}

	public void votar(Votacao v) {
		votacaoDAO.votar(v);
	}

	public Votacao jaVotou(long id) {
		
		return votacaoDAO.jaVotou(id);
	}

	public List<Votacao> listarVotos(long idChapa) {
		return votacaoDAO.listarVotos(idChapa);
	}
	public long quantidadeVotos(){
		return votacaoDAO.quantidadeVotos();
		
	}

	public void removeVotacao() {
		votacaoDAO.removeVotacao();
		
	}
}
