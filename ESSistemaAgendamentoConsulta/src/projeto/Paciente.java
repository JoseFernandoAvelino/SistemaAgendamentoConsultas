package projeto;

import java.time.LocalDate;
import java.util.Objects;

public class Paciente {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String telefone;
	
	public Paciente() {
	}
	
	public Paciente(Long id, String nome, String cpf, LocalDate dataNascimento, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
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
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Paciente paciente = (Paciente) o;
		return Objects.equals(id, paciente.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Paciente{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				'}';
	}
}
