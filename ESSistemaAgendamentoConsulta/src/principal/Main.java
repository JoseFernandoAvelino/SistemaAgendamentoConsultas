package principal;

import projeto.controller.AgendamentoController;
import projeto.model.Especialidade;
import projeto.model.Medico;
import projeto.model.Paciente;
import projeto.model.Recepcionista; // Novo
import projeto.repository.ConsultaRepository;
import projeto.repository.MedicoRepository;
import projeto.repository.PacienteRepository;
import projeto.repository.RecepcionistaRepository; // Novo
import projeto.service.LoginService; // Novo
import projeto.view.ConsoleView;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        PacienteRepository pacienteRepo = new PacienteRepository();
        MedicoRepository medicoRepo = new MedicoRepository();
        RecepcionistaRepository recepcionistaRepo = new RecepcionistaRepository();
        ConsultaRepository consultaRepo = new ConsultaRepository();

        Especialidade cardio = new Especialidade(1L, "Cardiologia");
        
        Paciente p1 = new Paciente(1L, "Ana Souza", "123.456",
                LocalDate.of(1998, 5, 12), "(81) 99999",
                "p", "1");
        
        Medico m1 = new Medico(1L, "Dra. Beatriz", "12345-PE", cardio,
                "m", "1");
        
        Recepcionista r1 = new Recepcionista(1L, "Carlos (Recepção)",
                "r", "1");
        
        pacienteRepo.salvar(p1);
        medicoRepo.salvar(m1);
        recepcionistaRepo.salvar(r1);

        System.out.println("--- Dados Iniciais Carregados ---");
        System.out.println("Paciente [p/1]: Ana Souza");
        System.out.println("Médico   [m/1]: Dra. Beatriz");
        System.out.println("Recepção [r/1]: Carlos");
        System.out.println("---------------------------------");

        ConsoleView view = new ConsoleView();        
        LoginService loginService = new LoginService(pacienteRepo, medicoRepo, recepcionistaRepo);
        AgendamentoController controller = new AgendamentoController(view, pacienteRepo, medicoRepo, recepcionistaRepo, consultaRepo, loginService);

        controller.iniciar();
    }
}