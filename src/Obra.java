import java.util.ArrayList;
import java.util.List;

public class Obra {

    private int id;
    private String nome;
    private String endereco;
    private double orcamento;
    private String responsavel;
    private StatusObra status;
    private List<ItemMaterial> itensMaterial;
    private List<Funcionario> funcionariosAlocados;

    public Obra() {
        this.status = StatusObra.PLANEJADA;
        this.itensMaterial = new ArrayList<>();
        this.funcionariosAlocados = new ArrayList<>();
    }

    public static class Builder {
        private int id;
        private String nome = "";
        private String endereco = "";
        private double orcamento;
        private String responsavel = "";
        private StatusObra status = StatusObra.PLANEJADA;
        private List<ItemMaterial> itensMaterial = new ArrayList<>();
        private List<Funcionario> funcionariosAlocados = new ArrayList<>();

        public Builder id(int id) { this.id = id; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder endereco(String endereco) { this.endereco = endereco; return this; }
        public Builder orcamento(double orcamento) { this.orcamento = orcamento; return this; }
        public Builder responsavel(String responsavel) { this.responsavel = responsavel; return this; }
        public Builder status(StatusObra status) { this.status = status; return this; }
        public Builder itensMaterial(List<ItemMaterial> itensMaterial) { this.itensMaterial = itensMaterial; return this; }
        public Builder funcionariosAlocados(List<Funcionario> funcionariosAlocados) { this.funcionariosAlocados = funcionariosAlocados; return this; }

        public Obra build() {
            Obra obra = new Obra();
            obra.id = this.id;
            obra.nome = this.nome;
            obra.endereco = this.endereco;
            obra.orcamento = this.orcamento;
            obra.responsavel = this.responsavel;
            obra.status = this.status != null ? this.status : StatusObra.PLANEJADA;
            obra.itensMaterial = this.itensMaterial != null ? new ArrayList<>(this.itensMaterial) : new ArrayList<>();
            obra.funcionariosAlocados = this.funcionariosAlocados != null ? new ArrayList<>(this.funcionariosAlocados) : new ArrayList<>();
            return obra;
        }
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

    public StatusObra getStatus() { return status; }
    public void setStatus(StatusObra status) { this.status = status; }

    public List<ItemMaterial> getItensMaterial() { return itensMaterial; }
    public void setItensMaterial(List<ItemMaterial> itensMaterial) { this.itensMaterial = itensMaterial; }

    public List<Funcionario> getFuncionariosAlocados() { return funcionariosAlocados; }
    public void setFuncionariosAlocados(List<Funcionario> funcionariosAlocados) {
        this.funcionariosAlocados = funcionariosAlocados;
    }

    public List<Integer> getMateriaisIds() {
        List<Integer> ids = new ArrayList<>();
        for (ItemMaterial item : itensMaterial) {
            if (item.getMaterial() != null) {
                ids.add(item.getMaterial().getId());
            }
        }
        return ids;
    }

    public void setMateriaisIds(List<Integer> materiaisIds) {
        this.itensMaterial = new ArrayList<>();
        for (Integer materialId : materiaisIds) {
            this.itensMaterial.add(new ItemMaterial(new Material(), materialId));
        }
    }
}