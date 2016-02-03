package modelo.votacao;

import java.util.List;

import modelo.chapa.Chapa;



public interface VotacaoDAO {

	public void votar(Votacao v);

	public Votacao jaVotou(long id);

	public List<Votacao> listarVotos(long idChapa);
	public long quantidadeVotos();

	public void removeVotacao();
	
}
