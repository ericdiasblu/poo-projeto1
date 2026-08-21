public class Material {

    private int id;
    private String descricao;
    private String unidade;
    private double precoUnitario;
    private int estoqueAtual;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }

    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

    public int getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(int estoqueAtual) { this.estoqueAtual = estoqueAtual; }

    public double calcularCusto(int quantidade) {
        return precoUnitario * quantidade;
    }

    public boolean possuiEstoqueSuficiente(int quantidadeNecessaria) {
        return quantidadeNecessaria <= estoqueAtual;
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade < 0 || quantidade > estoqueAtual) {
            throw new IllegalStateException("Estoque insuficiente para o material: " + descricao);
        }
        estoqueAtual -= quantidade;
    }
}
