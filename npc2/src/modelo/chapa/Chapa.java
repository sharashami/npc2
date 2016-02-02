package modelo.chapa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.aluno.Aluno;

@Entity
@Table(name="chapa")
public class Chapa {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	private Aluno criador;
	
	@Column(unique=true)
	private String nome;
	
	@OneToMany(mappedBy="chapa",targetEntity=Integrante.class ,fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private java.util.List<Integrante> integrante;
	
	
	@Transient
	private String composicao;
	
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
	public String getComposicao() {
		StringBuilder html = new StringBuilder();
		if(id > 0){
			for (int i = 0; i < integrante.size(); i++) {
				Integrante integ = integrante.get(i);
				html.append("<hr><br>Função: "+integ.getFuncao()+"<br>"+"Nome:" +integ.getNome()+"<br>"+"Matrícula:" +integ.getMatricula()+"<br>"+"Curso:" +integ.getCurso());
			}
		}
		composicao = html.toString();
		return composicao;
	}
	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}
}
