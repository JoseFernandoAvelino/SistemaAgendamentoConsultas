package projeto.service;

import projeto.model.Medico;
import projeto.model.Paciente;
import projeto.model.Recepcionista;
import projeto.repository.MedicoRepository;
import projeto.repository.PacienteRepository;
import projeto.repository.RecepcionistaRepository;

import java.util.Optional;

public class LoginService {

    private PacienteRepository pacienteRepo;
    private MedicoRepository medicoRepo;
    private RecepcionistaRepository recepcionistaRepo;

    public LoginService(PacienteRepository pRepo, MedicoRepository mRepo, RecepcionistaRepository rRepo) {
        this.pacienteRepo = pRepo;
        this.medicoRepo = mRepo;
        this.recepcionistaRepo = rRepo;
    }

    public Object autenticar(String email, String senha) {
        Optional<Paciente> optPaciente = pacienteRepo.buscarPorEmail(email);
        if (optPaciente.isPresent() && optPaciente.get().getSenha().equals(senha)) {
            return optPaciente.get();
        }

        Optional<Medico> optMedico = medicoRepo.buscarPorEmail(email);
        if (optMedico.isPresent() && optMedico.get().getSenha().equals(senha)) {
            return optMedico.get(); 
        }

        Optional<Recepcionista> optRecepcionista = recepcionistaRepo.buscarPorEmail(email);
        if (optRecepcionista.isPresent() && optRecepcionista.get().getSenha().equals(senha)) {
            return optRecepcionista.get();
        }

        return null;
    }
}