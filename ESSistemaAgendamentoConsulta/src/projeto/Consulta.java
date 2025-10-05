package projeto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta {
	private Long id;
	private LocalDateTime dataHora;
	private Paciente paciente;
	private Medico medico; 
	private StatusConsulta status;   
	
	public Consulta() {
	}
	
	public Consulta(Long id, LocalDateTime dataHora, Paciente paciente, Medico medico, StatusConsulta status) {
		this.id = id;
		this.dataHora = dataHora;
		this.paciente = paciente;
		this.medico = medico;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public StatusConsulta getStatus() {
		return status;
	}
	
	public void setStatus(StatusConsulta status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Consulta consulta = (Consulta) o;
		return Objects.equals(id, consulta.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public String toString() {
		return "Consulta{" +
				"id=" + id +
				", dataHora=" + dataHora +
				", paciente=" + paciente.getNome() +
				", medico=" + medico.getNome() +
				", status=" + status +
				'}';
	}
}