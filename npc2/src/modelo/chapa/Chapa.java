package modelo.chapa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import modelo.aluno.Aluno;

@Entity
@Table(name="chapa")
public class Chapa {

	@Id
	private long id;
	private Aluno criador;
	private String nome;
	private java.util.List<Integrante> integrante;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Aluno getCriador() {
		return criador;
	}
	public void setCriador(Aluno criador) {
		this.criador = criador;
	}
	public java.util.List<Integrante> getIntegrante() {
		return integrante;
	}
	public void setIntegrante(java.util.List<Integrante> integrante) {
		this.integrante = integrante;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((criador == null) ? 0 : criador.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((integrante == null) ? 0 : integrante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Chapa other = (Chapa) obj;
		if (criador == null) {
			if (other.criador != null)
				return false;
		} else if (!criador.equals(other.criador))
			return false;
		if (id != other.id)
			return false;
		if (integrante == null) {
			if (other.integrante != null)
				return false;
		} else if (!integrante.equals(other.integrante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
