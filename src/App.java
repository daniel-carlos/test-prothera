
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class App {

    public static void Seed(List<Funcionario> funcs) {
        funcs.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), 2009.445556, "Operador"));
        funcs.add(new Funcionario("João", LocalDate.of(1990, 5, 12), 2284.38, "Operador"));
        funcs.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), 9836.14, "Coordenador"));
        funcs.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), 19119.88, "Diretor"));
        funcs.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), 2234.68, "Recepcionista"));
        funcs.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), 1582.72, "Operador"));
        funcs.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), 4071.84, "Contador"));
        funcs.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), 3017.45, "Gerente"));
        funcs.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), 1606.85, "Eletricista"));
        funcs.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), 2799.93, "Gerente"));
    }

    public static void ShowFuncionarios(List<Funcionario> funcs) {
        System.out.println("\n\n== Funcionários ======================================================");
        for (Funcionario f : funcs) {
            System.out.println(f.Show());
        }
        System.out.println("======================================================================\n\n");
    }

    public static void RemoveFuncionario(List<Funcionario> funcs, String nome) {
        Stream<Funcionario> fs = funcs.stream().filter(f -> f.getNome().equals(nome));
        Optional<Funcionario> found = fs.findFirst();
        if (!found.isEmpty()) {
            funcs.remove(found.get());
        }
    }

    public static void AplicarAumento(List<Funcionario> funcs, double percentual) {
        for (Funcionario f : funcs) {
            f.setSalario(f.getSalario() * (1.0 + percentual / 100.0));
        }
    }

    public static Map<String, List<Funcionario>> GroupedFuncs(List<Funcionario> funcs) {
        Set<String> todasFuncoes = new HashSet<>();

        for (Funcionario f : funcs) {
            todasFuncoes.add(f.getFuncao());
        }

        Map<String, List<Funcionario>> grouped = new HashMap<>();

        for (String funcName : todasFuncoes) {
            grouped.put(funcName, funcs.stream().filter(f -> f.getFuncao().equals(funcName)).toList());
        }
        return grouped;
    }

    public static void ShowGroups(Map<String, List<Funcionario>> grouped) {
        System.out.println("\n\n== Funcionários por Função ===========================================");
        for (String funcName : grouped.keySet()) {
            System.out.println(funcName);
            for (Funcionario f : grouped.get(funcName)) {
                System.out.println("\t- " + f.Show());
            }
            System.out.println();
        }
        System.out.println("======================================================================\n\n");
    }

    public static void PrintFuncsByBirthMonth(List<Funcionario> funcs, int month) {
        for (Funcionario f : funcs) {
            if (f.getNascimento().getMonthValue() == month) {
                System.out.println("\t" + f.Show());
            }
        }
    }

    public static void PrintMaisVelho(List<Funcionario> funcs) {
        if (funcs.isEmpty()) {
            System.out.println("A lista está vazia");
            return;
        }
        Funcionario maisVelho = funcs.get(0);
        for (Funcionario f : funcs) {
            if (f.getNascimento().compareTo(maisVelho.getNascimento())
                    < 0) {
                maisVelho = f;
            }
        }

        System.out.println("\t" + maisVelho.Show2());
    }

    public static double TotalSalarios(List<Funcionario> funcs) {
        DoubleStream streamSalarios = funcs.stream().mapToDouble(Funcionario::getSalario);
        return streamSalarios.reduce(0.0, (a, b) -> a + b);
    }

    public static void PrinstSalariosMininoms(List<Funcionario> funcs, double salarioMinimo) {
        System.out.println("\nSalários Mínimos:");
        DecimalFormat df = new DecimalFormat("#.##");
        for (Funcionario f : funcs) {
            String salarioStr = df.format(f.getSalario() / salarioMinimo);
            System.out.println("\t- " + f.getNome() + " " + salarioStr + " salários mínimos");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Colors.COLOR_RESET);

        List<Funcionario> funcs = new ArrayList<>();
        // Requisito 3.1 - Inserir todos os funcionários, na mesma ordem e informações
        // da tabela acima.
        Seed(funcs);
        System.out.println();

        // Requisito 3.2 - Remover o funcionário “João” da lista.
        RemoveFuncionario(funcs, "João");
        System.out.println();

        // Requisito 3.3 – Imprimir todos os funcionários com todas suas informações
        ShowFuncionarios(funcs);
        System.out.println();

        // Requisito 3.4 – Os funcionários receberam 10% de aumento de salário,
        // atualizar a lista de funcionários com novo valor.
        AplicarAumento(funcs, 10);

        // Requisito 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a
        // “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> groupedFuncs = GroupedFuncs(funcs);
        // Requisito 3.6 – Imprimir os funcionários, agrupados por função.
        ShowGroups(groupedFuncs);

        // Requisito 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e
        // 12.
        System.out.println("Aniversariantes de Outubro e Dezembro:");
        PrintFuncsByBirthMonth(funcs, 10);
        PrintFuncsByBirthMonth(funcs, 12);
        System.out.println();

        // Requisito 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("Funcionário de maior idade:");
        PrintMaisVelho(funcs);

        // Requisito 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        List<Funcionario> orderedFuncs = funcs.stream().sorted().toList();
        ShowFuncionarios(orderedFuncs);

        // Requisito 3.11 – Imprimir o total dos salários dos funcionários.
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String salarioStr = df.format(TotalSalarios(funcs));
        System.out.println("Total de Salários: R$ " + salarioStr);

        // Requisito 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$ 1212.00.
        PrinstSalariosMininoms(funcs, 1212.0);
    }
}
