package projeto.repository;

import projeto.model.Recepcionista;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RecepcionistaRepository {
    private Map<Long, Recepcionista> bancoDeDados = new HashMap<>();

    public void salvar(Recepcionista recepcionista) {
        bancoDeDados.put(recepcionista.getId(), recepcionista);
    }

    public Optional<Recepcionista> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }
    
    public Optional<Recepcionista> buscarPorEmail(String email) {
        return bancoDeDados.values().stream()
                .filter(r -> r.getEmail().equals(email))
                .findFirst();
    }
}