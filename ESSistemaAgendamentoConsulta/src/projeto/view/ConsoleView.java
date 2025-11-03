package projeto.view;

import projeto.model.Paciente;
import projeto.model.Consulta;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirEmail() {
        System.out.print("Email: ");
        return scanner.nextLine();
    }

    public String pedirSenha() {
        System.out.print("Senha: ");
        return scanner.nextLine();
    }

    public void exibirTelaDeLogin() {
        System.out.println("\n--- BEM-VINDO AO SISTEMA DE AGENDAMENTO ---");
        System.out.println("Por favor, faça o login:");
    }
    
    public int exibirMenuPaciente() {
        System.out.println("\n--- Menu do Paciente ---");
        System.out.println("1. Agendar Consulta");
        System.out.println("2. Cancelar Consulta");
        System.out.println("3. Consultar Histórico");
        System.out.println("0. Sair (Logout)");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int exibirMenuMedico() {
        System.out.println("\n--- Menu do Médico ---");
        System.out.println("1. Visualizar Agenda");
        System.out.println("0. Sair (Logout)");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int exibirMenuRecepcionista() {
        System.out.println("\n--- Menu da Recepcionista ---");
        System.out.println("1. Agendar Consulta");
        System.out.println("2. Cancelar Consulta");
        System.out.println("3. Cadastrar Paciente");
        System.out.println("0. Sair (Logout)");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Long perguntarIdPaciente() {
        System.out.print("Digite o ID do Paciente: ");
        return Long.parseLong(scanner.nextLine());
    }

    public Long perguntarIdMedico() {
        System.out.print("Digite o ID do Médico: ");
        return Long.parseLong(scanner.nextLine());
    }

    public LocalDateTime perguntarDataHora() {
        while (true) {
            System.out.print("Digite a data e hora (dd/MM/yyyy HH:mm): ");
            String input = scanner.nextLine();
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Tente novamente.");
            }
        }
    }

    public void exibirListaConsultas(List<Consulta> consultas) {
        System.out.println("\n--- Lista de Consultas Agendadas ---");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
            return;
        }
        for (Consulta consulta : consultas) {
            System.out.println(consulta.toString());
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void fecharScanner() {
        scanner.close();
    }
    
    public Paciente perguntarDadosNovoPaciente() {
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF (xxx.xxx.xxx-xx): ");
        String cpf = scanner.nextLine();
        
        LocalDate dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            try {
                dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            }
        }
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email (para login): ");
        String email = scanner.nextLine();
        
        System.out.print("Senha (provisória): ");
        String senha = scanner.nextLine();

        return new Paciente(null, nome, cpf, dataNascimento, telefone, email, senha);
    }
    
    public Long perguntarIdConsultaParaCancelar() {
        while (true) {
            System.out.print("Digite o ID da consulta que deseja CANCELAR: ");
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido. Por favor, digite apenas números.");
            }
        }
    }
}