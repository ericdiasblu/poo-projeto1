import java.util.ArrayList;
import java.util.List;

public class Obra {

    private int id;
    private String nome;
    private String endereco;
    private double orcamento;
    private String responsavel;
    private String status;
    private List<Integer> materiaisIds;
    private List<Funcionario> funcionariosAlocados;

    public Obra() {
        this.materiaisIds = new ArrayList<>();
        this.funcionariosAlocados = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public double getOrcamento() { return orcamento; }
    public void setOrcamento(double orcamento) { this.orcamento = orcamento; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Integer> getMateriaisIds() { return materiaisIds; }
    public void setMateriaisIds(List<Integer> materiaisIds) { this.materiaisIds = materiaisIds; }

    public List<Funcionario> getFuncionariosAlocados() { return funcionariosAlocados; }
    public void setFuncionariosAlocados(List<Funcionario> funcionariosAlocados) {
        this.funcionariosAlocados = funcionariosAlocados;
    }
}
