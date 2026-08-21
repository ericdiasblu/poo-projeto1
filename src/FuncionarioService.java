import java.util.List;

public class FuncionarioService {

    private final List<Funcionario> funcionarios;
    private final List<Obra> obras;

    public FuncionarioService(List<Funcionario> funcionarios, List<Obra> obras) {
        this.funcionarios = funcionarios;
        this.obras = obras;
    }

    public void cadastrarFuncionario(String nome, String cargo, double salario) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarios.size() + 1);
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setSalario(salario);
        funcionarios.add(funcionario);
    }

    public Funcionario buscarFuncionario(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    public void alocarFuncionario(int obraId, int funcionarioId) {
        Obra obra = buscarObra(obraId);
        Funcionario func = buscarFuncionario(funcionarioId);
        if (obra == null || func == null) {
            System.out.println("Obra ou funcionario nao encontrado.");
            return;
        }
        if (obra.getFuncionariosAlocados().contains(func)) {
            System.out.println("Funcionario " + func.getNome() + " ja esta alocado em " + obra.getNome());
            return;
        }
        obra.getFuncionariosAlocados().add(func);
        System.out.println("Funcionario " + func.getNome() + " alocado em " + obra.getNome());
    }

    private Obra buscarObra(int id) {
        for (Obra obra : obras) {
            if (obra.getId() == id) {
                return obra;
            }
        }
        return null;
    }
}
