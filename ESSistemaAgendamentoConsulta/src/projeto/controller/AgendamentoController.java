package projeto.controller;

import projeto.model.Consulta;
import projeto.model.Medico;
import projeto.model.Paciente;
import projeto.model.Recepcionista;
import projeto.model.StatusConsulta;
import projeto.repository.ConsultaRepository;
import projeto.repository.MedicoRepository;
import projeto.repository.PacienteRepository;
import projeto.repository.RecepcionistaRepository;
import projeto.service.LoginService;
import projeto.view.ConsoleView;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AgendamentoController {

    private ConsoleView view;
    private PacienteRepository pacienteRepo;
    private MedicoRepository medicoRepo;
    private RecepcionistaRepository recepcionistaRepo;
    private ConsultaRepository consultaRepo;
    private LoginService loginService;

    private Object usuarioLogado = null;

    public AgendamentoController(ConsoleView view, PacienteRepository pRepo, MedicoRepository mRepo,
                                 RecepcionistaRepository rRepo, ConsultaRepository cRepo, LoginService loginService) {
        this.view = view;
        this.pacienteRepo = pRepo;
        this.medicoRepo = mRepo;
        this.recepcionistaRepo = rRepo;
        this.consultaRepo = cRepo;
        this.loginService = loginService;
    }

    public void iniciar() {
        while (this.usuarioLogado == null) {
            fazerLogin();
        }
        direcionarParaMenu();
        this.usuarioLogado = null;
        view.exibirMensagem("Logout realizado com sucesso.");
        view.fecharScanner();
    }

    private void fazerLogin() {
        view.exibirTelaDeLogin();
        String email = view.pedirEmail();
        String senha = view.pedirSenha();
        this.usuarioLogado = loginService.autenticar(email, senha);

        if (this.usuarioLogado == null) {
            view.exibirMensagem("Email ou senha inválidos. Tente novamente.");
        } else {
            view.exibirMensagem("Login bem-sucedido!");
        }
    }

    private void direcionarParaMenu() {
        if (usuarioLogado instanceof Paciente) {
            loopMenuPaciente();
        } else if (usuarioLogado instanceof Medico) {
            loopMenuMedico();
        } else if (usuarioLogado instanceof Recepcionista) {
            loopMenuRecepcionista();
        }
    }

    private void loopMenuPaciente() {
        while (true) {
            int opcao = view.exibirMenuPaciente();
            switch (opcao) {
                case 1:
                    agendarConsulta((Paciente) usuarioLogado);
                    break;
                case 2:
                    cancelarConsulta(usuarioLogado);
                    break;
                case 3:
                    listarConsultas((Paciente) usuarioLogado);
                    break;
                case 0:
                    return;
                default:
                    view.exibirMensagem("Opção inválida.");
            }
        }
    }

    private void loopMenuMedico() {
        Medico medico = (Medico) usuarioLogado;
        while (true) {
            int opcao = view.exibirMenuMedico();
            switch (opcao) {
                case 1:
                    listarConsultas(medico);
                    break;
                case 0:
                    return;
                default:
                    view.exibirMensagem("Opção inválida.");
            }
        }
    }
    
    private void loopMenuRecepcionista() {
        while (true) {
            int opcao = view.exibirMenuRecepcionista();
            switch (opcao) {
                case 1:
                    agendarConsulta();
                    break;
                case 2:
                    cancelarConsulta(usuarioLogado);
                    break;
                case 3:
                    cadastrarPaciente();
                    break;
                case 0:
                    return;
                default:
                    view.exibirMensagem("Opção inválida.");
            }
        }
    }

    private void agendarConsulta() {
        try {
            Long pacienteId = view.perguntarIdPaciente();
            Optional<Paciente> optPaciente = pacienteRepo.buscarPorId(pacienteId);
            	if (optPaciente.isEmpty()) { view.exibirMensagem("Erro: Paciente não encontrado!"); return; }

            Long medicoId = view.perguntarIdMedico();
            Optional<Medico> optMedico = medicoRepo.buscarPorId(medicoId);
            	if (optMedico.isEmpty()) { view.exibirMensagem("Erro: Médico não encontrado!"); return; }
            
            LocalDateTime dataHora = view.perguntarDataHora();
           		if (!validarHorarioComercial(dataHora)) {
           			return;
            }

            Consulta novaConsulta = new Consulta();
            novaConsulta.setPaciente(optPaciente.get());
            novaConsulta.setMedico(optMedico.get());
            novaConsulta.setDataHora(dataHora);
            novaConsulta.setStatus(StatusConsulta.AGENDADA);

            Consulta consultaSalva = consultaRepo.salvar(novaConsulta);
            view.exibirMensagem("Sucesso! Consulta agendada com ID: " + consultaSalva.getId());

        } catch (Exception e) {
        	view.exibirMensagem("Erro ao processar agendamento: " + e.getMessage());
        }
    }

    private void agendarConsulta(Paciente pacienteLogado) {
        try {
            Long medicoId = view.perguntarIdMedico();
            Optional<Medico> optMedico = medicoRepo.buscarPorId(medicoId);
            	if (optMedico.isEmpty()) { view.exibirMensagem("Erro: Médico não encontrado!"); return; }
            
            LocalDateTime dataHora = view.perguntarDataHora();
            	if (!validarHorarioComercial(dataHora)) {
            		return;
            }

            Consulta novaConsulta = new Consulta();
            novaConsulta.setPaciente(pacienteLogado);
            novaConsulta.setMedico(optMedico.get());
            novaConsulta.setDataHora(dataHora);
            novaConsulta.setStatus(StatusConsulta.AGENDADA);

            Consulta consultaSalva = consultaRepo.salvar(novaConsulta);
            view.exibirMensagem("Sucesso! Consulta agendada para você (ID: " + consultaSalva.getId() + ")");

        } catch (Exception e) {
            view.exibirMensagem("Erro ao processar agendamento: " + e.getMessage());
        }
    }

    private void listarConsultas() {
        List<Consulta> consultas = consultaRepo.listarTodos();
        view.exibirListaConsultas(consultas);
    }

    private void listarConsultas(Paciente pacienteLogado) {
        List<Consulta> todas = consultaRepo.listarTodos();
        List<Consulta> minhasConsultas = todas.stream()
                .filter(c -> c.getPaciente().equals(pacienteLogado))
                .toList();
        view.exibirListaConsultas(minhasConsultas);
    }

    private void listarConsultas(Medico medicoLogado) {
        List<Consulta> todas = consultaRepo.listarTodos();
        List<Consulta> minhaAgenda = todas.stream()
                .filter(c -> c.getMedico().equals(medicoLogado))
                .toList();
        view.exibirListaConsultas(minhaAgenda);
    }
    
    private boolean validarHorarioComercial(LocalDateTime dataHora) {
        LocalTime horaConsulta = dataHora.toLocalTime();
        LocalTime inicioExpediente = LocalTime.of(8, 0); 
        LocalTime fimExpediente = LocalTime.of(18, 0);  
        
        boolean horarioValido = !horaConsulta.isBefore(inicioExpediente) && 
                                 horaConsulta.isBefore(fimExpediente);

        if (!horarioValido) {
            view.exibirMensagem("Erro: Horário fora do expediente (agendamentos somente das 08:00 às 17:59).");
            return false;
        }
        return true;
    }

    private void cancelarConsulta(Object usuarioLogado) {
        try {
            Long consultaId = view.perguntarIdConsultaParaCancelar();
            Optional<Consulta> optConsulta = consultaRepo.buscarPorId(consultaId);

            if (optConsulta.isEmpty()) {
                view.exibirMensagem("Erro: Consulta não encontrada com este ID.");
                return;
            }

            Consulta consulta = optConsulta.get();
            boolean permissaoConcedida = false;

            if (usuarioLogado instanceof Recepcionista) {
                permissaoConcedida = true;
            }
           
            if (usuarioLogado instanceof Paciente) {
                Paciente pacienteLogado = (Paciente) usuarioLogado;
                if (consulta.getPaciente().equals(pacienteLogado)) {
                    permissaoConcedida = true;
                }
            }

            if (permissaoConcedida) {
                if (consulta.getStatus() == StatusConsulta.CANCELADA) {
                    view.exibirMensagem("Esta consulta já estava cancelada.");
                } else {
                    consulta.setStatus(StatusConsulta.CANCELADA);
                    consultaRepo.salvar(consulta);
                    view.exibirMensagem("Consulta (ID: " + consulta.getId() + ") foi cancelada com sucesso!");
                }
            } else {
                view.exibirMensagem("Erro: Você não tem permissão para cancelar esta consulta.");
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro ao processar cancelamento: " + e.getMessage());
        }
    }

    private void cadastrarPaciente() {
        try {
            view.exibirMensagem("\n--- Cadastro de Novo Paciente ---");
            Paciente novoPaciente = view.perguntarDadosNovoPaciente();
            Paciente pacienteSalvo = pacienteRepo.salvar(novoPaciente);            
            view.exibirMensagem("Paciente cadastrado com sucesso!");
            view.exibirMensagem("Nome: " + pacienteSalvo.getNome());
            view.exibirMensagem("ID gerado: " + pacienteSalvo.getId());
        } catch (Exception e) {
            view.exibirMensagem("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }
}