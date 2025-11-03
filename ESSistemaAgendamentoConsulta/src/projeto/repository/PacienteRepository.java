package projeto.repository;

import projeto.model.Paciente;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PacienteRepository {
    private Map<Long, Paciente> bancoDeDados = new HashMap<>();
    private AtomicLong proximoId = new AtomicLong(1);

    public Paciente salvar(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(proximoId.getAndIncrement());
        }
        bancoDeDados.put(paciente.getId(), paciente);
        return paciente;
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }
    
    public Optional<Paciente> buscarPorEmail(String email) {
        return bancoDeDados.values().stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst();
    }
}