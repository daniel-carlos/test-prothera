
import java.time.LocalDate;

// Requisito 1 - Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
public class Pessoa {

    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    private LocalDate nascimento;
    public LocalDate getNascimento() {
        return nascimento;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Pessoa(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "Pessoa: " + this.getNome();
    }
}
