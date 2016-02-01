package modelo.votacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import modelo.aluno.Aluno;
import modelo.chapa.Chapa;

@Entity
@Table(name="votacao")
public class Votacao {
	@Id
	private long id;
	
	@Column(unique = true)
	private Aluno eleitor;
	private Chapa chapa;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Aluno getEleitor() {
		return eleitor;
	}
	public void setEleitor(Aluno eleitor) {
		this.eleitor = eleitor;
	}
	public Chapa getChapa() {
		return chapa;
	}
	public void setChapa(Chapa chapa) {
		this.chapa = chapa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapa == null) ? 0 : chapa.hashCode());
		result = prime * result + ((eleitor == null) ? 0 : eleitor.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Votacao other = (Votacao) obj;
		if (chapa == null) {
			if (other.chapa != null)
				return false;
		} else if (!chapa.equals(other.chapa))
			return false;
		if (eleitor == null) {
			if (other.eleitor != null)
				return false;
		} else if (!eleitor.equals(other.eleitor))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
