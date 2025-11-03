package projeto.repository;

import projeto.model.Consulta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsultaRepository {
    private Map<Long, Consulta> bancoDeDados = new HashMap<>();
    private Long proximoId = 1L;

    public Consulta salvar(Consulta consulta) {
        if (consulta.getId() == null) {
            consulta.setId(proximoId++);
        }
        bancoDeDados.put(consulta.getId(), consulta);
        return consulta;
    }
    
    public Optional<Consulta> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }

    public List<Consulta> listarTodos() {
        return new ArrayList<>(bancoDeDados.values());
    }
}