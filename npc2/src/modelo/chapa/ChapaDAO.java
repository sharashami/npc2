package modelo.chapa;

import java.util.List;

import org.hibernate.HibernateException;

public interface ChapaDAO {

	public Chapa salvarChapa(Chapa chapa) throws HibernateException;

	public List<Chapa> listarChapas();

	public Chapa getChapa(long id);

	public Chapa jaEstaEmChapa(long idAluno);

	public void remover(Chapa chapa);
	
}
