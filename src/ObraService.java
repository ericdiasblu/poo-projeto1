import java.util.ArrayList;
import java.util.List;

public class ObraService {

    private final List<Obra> obras;
    private final MaterialService materialService;

    public ObraService(List<Obra> obras, MaterialService materialService) {
        this.obras = obras;
        this.materialService = materialService;
    }

    public void cadastrarObra(String nome, String endereco, double orcamento, String responsavel) {
        Obra obra = new Obra.Builder()
                .id(obras.size() + 1)
                .nome(nome)
                .endereco(endereco)
                .orcamento(orcamento)
                .responsavel(responsavel)
                .status(StatusObra.PLANEJADA)
                .build();
        obras.add(obra);
        System.out.println("Obra cadastrada: " + nome);
    }

    public void atualizarStatus(int obraId, StatusObra novoStatus) {
        Obra obra = buscarObra(obraId);
        if (obra == null) {
            System.out.println("Obra nao encontrada.");
            return;
        }
        if (novoStatus == null || !obra.getStatus().podeTransicionarPara(novoStatus)) {
            System.out.println("Transicao de status invalida para a obra " + obra.getNome() + ": " + novoStatus);
            return;
        }
        obra.setStatus(novoStatus);
        System.out.println("Status atualizado: " + obra.getNome() + " -> " + novoStatus);
    }

    public void adicionarMaterialNaObra(int obraId, int materialId) {
        Obra obra = buscarObra(obraId);
        Material material = materialService.buscarMaterial(materialId);
        if (obra == null || material == null) {
            System.out.println("Obra ou material nao encontrado.");
            return;
        }

        boolean materialJaAssociado = false;
        for (ItemMaterial item : obra.getItensMaterial()) {
            if (item.getMaterial() != null && item.getMaterial().getId() == materialId) {
                materialJaAssociado = true;
                break;
            }
        }

        if (materialJaAssociado) {
            System.out.println("Material " + material.getDescricao() + " ja esta associado a obra " + obra.getNome());
            return;
        }

        obra.getItensMaterial().add(new ItemMaterial(material, 1));
        System.out.println("Material " + material.getDescricao() + " adicionado a " + obra.getNome());
    }

    public int cancelarObra(int obraId) {
        Obra obra = buscarObra(obraId);
        if (obra == null) {
            System.out.println("Obra nao encontrada.");
            return 0;
        }

        int materiaisLiberados = obra.getItensMaterial().size();
        obra.setStatus(StatusObra.CANCELADA);
        System.out.println("Obra cancelada: " + obra.getNome());
        return materiaisLiberados;
    }

    public double calcularCustoMateriais(int obraId) {
        Obra obra = buscarObra(obraId);
        if (obra == null) {
            return 0;
        }

        double total = 0;
        for (ItemMaterial item : obra.getItensMaterial()) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public Obra buscarObra(int id) {
        for (Obra obra : obras) {
            if (obra.getId() == id) {
                return obra;
            }
        }
        return null;
    }
}
