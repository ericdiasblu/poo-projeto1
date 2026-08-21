public class ItemMaterial {

    private Material material;
    private int quantidade;

    public ItemMaterial(Material material, int quantidade) {
        this.material = material;
        this.quantidade = quantidade;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        if (material == null) {
            return 0.0;
        }
        return material.calcularCusto(quantidade);
    }
}
