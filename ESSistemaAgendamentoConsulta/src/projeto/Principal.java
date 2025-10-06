package projeto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Principal {
    public static void main(String[] args) {
        Especialidade cardio = new Especialidade(1L, "Cardiologia");
        Medico m1 = new Medico(1L, "Dra. Beatriz", "12345-PE", cardio);

        Paciente p1 = new Paciente(1L, "Ana Souza", "123.456.789-00",
                LocalDate.of(1998,5,12), "(81) 99999-0000");

        Consulta c1 = new Consulta(1L, LocalDateTime.now().plusDays(1),
                p1, m1, StatusConsulta.AGENDADA);

        System.out.println(cardio);
        System.out.println(m1);
        System.out.println(p1);
        System.out.println(c1);
    }
}