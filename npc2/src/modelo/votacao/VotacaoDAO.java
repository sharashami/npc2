package modelo.votacao;

import java.util.List;



public interface VotacaoDAO {

	public void votar(Votacao v);

	public Votacao jaVotou(long id);

	public List<Votacao> listarVotos();
	
}
