
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// Requisito 2 - Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Funcionario extends Pessoa implements Comparable<Funcionario> {

    private double salario;

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    private String funcao;

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Funcionario(String nome, LocalDate nascimento, double salario, String funcao) {
        super(nome, nascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Funcionário: " + this.getNome();
    }

    public String ShowSalario() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String salarioStr = df.format(this.salario);
        return Colors.COLOR_YELLOW + salarioStr + Colors.COLOR_RESET;
    }

    public String Show() {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = dateFormatter.format(getNascimento());
        return this.getNome() + "\t [" + date + "]\tR$ " + ShowSalario() + " \t" + Colors.COLOR_PURPLE + this.getFuncao() + Colors.COLOR_RESET;
    }

    public String Show2() {
        // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // String date = dateFormatter.format(getNascimento());
        return this.getNome() + "\t [" + calcularIdade() + " anos]";
    }

    public int calcularIdade() {
        LocalDate dataAtual = LocalDate.now(); // Pega a data atual
        Period periodo = Period.between(this.getNascimento(), dataAtual); // Calcula o período entre as datas
        return periodo.getYears(); // Retorna a idade em anos
    }

    @Override
    public int compareTo(Funcionario o) {
        return this.getNome().compareTo(o.getNome());
    }
}
