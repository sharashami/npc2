package modelo.chapa;

import java.util.List;

public interface ChapaDAO {

	public Chapa salvarChapa(Chapa chapa);

	public List<Chapa> listarChapas();
	
}
