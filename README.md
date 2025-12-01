# Sistema de Agendamento de Consultas

## Sobre o Projeto
Este projeto é um sistema de agendamento de consultas médicas desenvolvido em **Java** para rodar no terminal (Console). 
O sistema foi construído utilizando a arquitetura **MVC (Model-View-Controller)** para organização do código e possui controle de acesso via login.

---

## Funcionalidades Principais

O sistema exige autenticação e apresenta opções diferentes para cada perfil:

### Paciente
- **Agendar Consulta:** Marcar uma nova consulta (o sistema valida o horário comercial: 08:00 às 18:00).
- **Cancelar Consulta:** Cancelar seus próprios agendamentos.
- **Visualizar Histórico:** Visualizar suas consultas agendadas.

### Recepcionista
- **Cadastrar Paciente:** Adicionar novos pacientes ao sistema.
- **Agendar Consulta:** Marcar consultas para qualquer paciente.
- **Cancelar Consulta:** Permissão administrativa para cancelar qualquer agendamento.

### Médico
- **Visualizar Agenda:** Consultar a lista de atendimentos marcados.

---

## Tecnologias Utilizadas

As seguintes ferramentas e conceitos foram usados na construção do projeto:

- **Linguagem:** Java 17+
- **Arquitetura:** MVC (Model-View-Controller)
- **Persistência:** Em memória (Simulação de Banco de Dados usando `HashMap`).

---

## Como Rodar e Testar

1. Importe o projeto na sua IDE.
2. Execute o arquivo `Main.java`.
3. Para facilitar os testes, o sistema já inicia com usuários cadastrados. Use as credenciais abaixo:

| Perfil | Email (Login) | Senha |
| :--- | :--- | :--- |
| **Paciente** | p | 1 |
| **Médico** | m | 1 |
| **Recepcionista** | r | 1 |

---

## Autores

Feito por **José Fernando Avelino e João Pedro Holanda**
