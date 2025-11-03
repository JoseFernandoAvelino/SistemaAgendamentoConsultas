package projeto.repository;

import projeto.model.Medico;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MedicoRepository {
    private Map<Long, Medico> bancoDeDados = new HashMap<>();

    public void salvar(Medico medico) {
        bancoDeDados.put(medico.getId(), medico);
    }

    public Optional<Medico> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }
    
    public Optional<Medico> buscarPorEmail(String email) {
        return bancoDeDados.values().stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst();
    }
}