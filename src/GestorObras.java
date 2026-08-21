import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorObras {

    private final List<Obra> obras;
    private final List<Funcionario> funcionarios;
    private final List<Material> materiais;
    private final ObraService obraService;
    private final MaterialService materialService;
    private final FuncionarioService funcionarioService;
    private final RelatorioService relatorioService;

    public GestorObras() {
        this.obras = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.materiais = new ArrayList<>();
        this.materialService = new MaterialService(this.materiais);
        this.obraService = new ObraService(this.obras, this.materialService);
        this.funcionarioService = new FuncionarioService(this.funcionarios, this.obras);
        this.relatorioService = new RelatorioService(this.obras);
    }

    public void cadastrarObra(String nome, String endereco, double orcamento, String responsavel) {
        obraService.cadastrarObra(nome, endereco, orcamento, responsavel);
    }

    public void atualizarStatus(int obraId, StatusObra novoStatus) {
        obraService.atualizarStatus(obraId, novoStatus);
    }

    public void cadastrarMaterial(String descricao, String unidade, double precoUnitario) {
        materialService.cadastrarMaterial(descricao, unidade, precoUnitario);
    }

    public void adicionarMaterialNaObra(int obraId, int materialId) {
        obraService.adicionarMaterialNaObra(obraId, materialId);
    }

    public void cadastrarFuncionario(String nome, String cargo, double salario) {
        funcionarioService.cadastrarFuncionario(nome, cargo, salario);
    }

    public void alocarFuncionario(int obraId, int funcionarioId) {
        funcionarioService.alocarFuncionario(obraId, funcionarioId);
    }

    public int cancelarObra(int obraId) {
        return obraService.cancelarObra(obraId);
    }

    public double calcularCustoMateriais(int obraId) {
        return obraService.calcularCustoMateriais(obraId);
    }

    public void gerarRelatorio() {
        relatorioService.gerarRelatorio();
    }

    public void salvarEmArquivo(String caminho) {
        relatorioService.salvarEmArquivo(caminho);
    }

    public Obra buscarObra(int id) {
        return obraService.buscarObra(id);
    }

    public Material buscarMaterial(int id) {
        return materialService.buscarMaterial(id);
    }

    public Funcionario buscarFuncionario(int id) {
        return funcionarioService.buscarFuncionario(id);
    }

    public List<Obra> getObras() { return obras; }
    public List<Material> getMateriais() { return materiais; }
    public List<Funcionario> getFuncionarios() { return funcionarios; }
}
