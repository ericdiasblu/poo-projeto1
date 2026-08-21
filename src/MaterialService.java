import java.util.List;

public class MaterialService {

    private final List<Material> materiais;

    public MaterialService(List<Material> materiais) {
        this.materiais = materiais;
    }

    public void cadastrarMaterial(String descricao, String unidade, double precoUnitario) {
        Material material = new Material();
        material.setId(materiais.size() + 1);
        material.setDescricao(descricao);
        material.setUnidade(unidade);
        material.setPrecoUnitario(precoUnitario);
        material.setEstoqueAtual(0);
        materiais.add(material);
    }

    public Material buscarMaterial(int id) {
        for (Material material : materiais) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }
}
