package modelo.chapa;

import java.util.List;

import org.hibernate.HibernateException;

import modelo.FactoryDAO;
import modelo.aluno.Aluno;

public class ChapaRN {
	private ChapaDAO chapaDAO;
	
	public ChapaRN(){
		chapaDAO = FactoryDAO.criarChapaDAO();
	}
	public Chapa salvar( Chapa chapa) throws HibernateException{
		
		return chapaDAO.salvarChapa(chapa);
	}
	public Chapa alterar( Chapa chapa) throws HibernateException{
		
		return chapaDAO.alterarChapa(chapa);
	}

	public List<Chapa> listarChapas(){
		return chapaDAO.listarChapas();
	}
	public Chapa getChapa(long id) {
		return chapaDAO.getChapa(id);
	}
	public Chapa jaEstaEmChapa(long idAluno) {

		return chapaDAO.jaEstaEmChapa(idAluno);
	}
	public void remover(Chapa chapa) {
		chapaDAO.remover(chapa);
		
	}
}
