package projeto.model;

import java.util.Objects;

public class Medico {
	private Long id;
	private String nome;
	private String crm;
	private Especialidade especialidade;
	private String email;
	private String senha;
	
	public Medico() {
	}
	
	public Medico(Long id, String nome, String crm, Especialidade especialidade, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
		this.email = email;
	    this.senha = senha;
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
	
	public String getCrm() {
		return crm;
	}
	
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	public String getEmail() {
		return email; 
	}
	
	public String getSenha() {
		return senha; 
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Medico medico = (Medico) o;
		return Objects.equals(id, medico.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public String toString() {
		return "Medico{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", crm='" + crm + '\'' +
				", especialidade=" + especialidade.getNome() +
				'}';
	}
}