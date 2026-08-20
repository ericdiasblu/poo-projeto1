import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Gerenciador central de obras e empreendimentos.
 * Responsável por: cadastro, atualização de status, alocação de funcionários,
 * controle de materiais, geração de relatórios e persistência.
 */
public class GestorObras {

    private List<Obra> obras;
    private List<Funcionario> funcionarios;
    private List<Material> materiais;

    public GestorObras() {
        this.obras = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.materiais = new ArrayList<>();
    }

    public void cadastrarObra(String nome, String endereco, double orcamento, String responsavel) {
        Obra o = new Obra();
        o.setId(obras.size() + 1);
        o.setNome(nome);
        o.setEndereco(endereco);
        o.setOrcamento(orcamento);
        o.setResponsavel(responsavel);
        o.setStatus("PLANEJADA");
        o.setMateriaisIds(new ArrayList<>());
        obras.add(o);
        System.out.println("Obra cadastrada: " + nome);
    }

    /**
     * Atualiza o status de uma obra.
     * BUG: aceita qualquer String — sem validacao de transicoes permitidas.
     * Ex: e possivel setar "CONCLUIDA" direto sem passar por "EM_ANDAMENTO".
     */
    public void atualizarStatus(int obraId, String novoStatus) {
        Obra obra = buscarObra(obraId);
        if (obra == null) {
            System.out.println("Obra nao encontrada.");
            return;
        }
        obra.setStatus(novoStatus);
        System.out.println("Status atualizado: " + obra.getNome() + " -> " + novoStatus);
    }

    public void cadastrarMaterial(String descricao, String unidade, double precoUnitario) {
        Material m = new Material();
        m.setId(materiais.size() + 1);
        m.setDescricao(descricao);
        m.setUnidade(unidade);
        m.setPrecoUnitario(precoUnitario);
        materiais.add(m);
    }

    /**
     * Associa um material a uma obra pelo ID.
     * BUG: armazena apenas o ID inteiro — para exibir detalhes do material
     *      e necessario fazer nova busca; dados ficam desacoplados e sujeitos
     *      a inconsistencia se um material for removido.
     */
    public void adicionarMaterialNaObra(int obraId, int materialId) {
        Obra obra = buscarObra(obraId);
        Material material = buscarMaterial(materialId);
        if (obra == null || material == null) {
            System.out.println("Obra ou material nao encontrado.");
            return;
        }
        obra.getMateriaisIds().add(materialId);
        System.out.println("Material " + material.getDescricao() + " adicionado a " + obra.getNome());
    }

    public void cadastrarFuncionario(String nome, String cargo, double salario) {
        Funcionario f = new Funcionario();
        f.setId(funcionarios.size() + 1);
        f.setNome(nome);
        f.setCargo(cargo);
        f.setSalario(salario);
        funcionarios.add(f);
    }

    public void alocarFuncionario(int obraId, int funcionarioId) {
        Obra obra = buscarObra(obraId);
        Funcionario func = buscarFuncionario(funcionarioId);
        if (obra == null || func == null) {
            System.out.println("Obra ou funcionario nao encontrado.");
            return;
        }
        System.out.println("Funcionario " + func.getNome() + " alocado em " + obra.getNome());
    }

    /**
     * Cancela uma obra e retorna o numero de materiais liberados.
     * BUG: sempre retorna 1 independente de quantos materiais a obra possui.
     *      O status e setado corretamente, mas o retorno e incorreto.
     */
    public int cancelarObra(int obraId) {
        Obra obra = buscarObra(obraId);
        if (obra == null) {
            System.out.println("Obra nao encontrada.");
            return 0;
        }
        obra.setStatus("CANCELADA");
        System.out.println("Obra cancelada: " + obra.getNome());
        return 1; // BUG: deveria retornar obra.getMateriaisIds().size()
    }

    public double calcularCustoMateriais(int obraId) {
        Obra obra = buscarObra(obraId);
        if (obra == null) return 0;
        double total = 0;
        for (int matId : obra.getMateriaisIds()) {
            Material m = buscarMaterial(matId);
            if (m != null) total += m.getPrecoUnitario();
        }
        return total;
    }

    public void gerarRelatorio() {
        System.out.println("\n=== Relatorio de Obras ===");
        for (Obra o : obras) {
            System.out.printf("%-30s | Status: %-15s | Orcamento: R$ %,.2f | Materiais: %d%n",
                    o.getNome(), o.getStatus(), o.getOrcamento(), o.getMateriaisIds().size());
        }
    }

    public void salvarEmArquivo(String caminho) {
        try (FileWriter fw = new FileWriter(caminho)) {
            fw.write("=== Obras ===\n");
            for (Obra o : obras) {
                fw.write(o.getNome() + " | " + o.getStatus() + " | R$" + o.getOrcamento() + "\n");
            }
            System.out.println("Relatorio salvo em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public Obra buscarObra(int id) {
        for (Obra o : obras) {
            if (o.getId() == id) return o;
        }
        return null;
    }

    public Material buscarMaterial(int id) {
        for (Material m : materiais) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public Funcionario buscarFuncionario(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    public List<Obra> getObras() { return obras; }
    public List<Material> getMateriais() { return materiais; }
    public List<Funcionario> getFuncionarios() { return funcionarios; }
}
