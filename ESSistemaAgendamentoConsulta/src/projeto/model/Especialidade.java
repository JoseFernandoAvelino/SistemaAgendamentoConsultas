package projeto.model;

import java.util.Objects;

public class Especialidade {
	private Long id;
	private String nome;
	
	public Especialidade() {		
	}
	
	public Especialidade(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Especialidade that = (Especialidade) o;
		return Objects.equals(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public String toString() {
		return "Especialidade{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				'}';
	}
}