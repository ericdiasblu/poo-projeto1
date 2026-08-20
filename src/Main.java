public class Main {

    public static void main(String[] args) {
        GestorObras gestor = new GestorObras();

        gestor.cadastrarObra("Residencial Parque Verde", "Rua das Flores, 100", 2500000.0, "Eng. Silva");
        gestor.cadastrarObra("Centro Comercial Norte", "Av. Principal, 500", 8000000.0, "Eng. Costa");

        gestor.cadastrarMaterial("Cimento CP-II", "saco 50kg", 42.0);
        gestor.cadastrarMaterial("Vergalhao CA-50 10mm", "kg", 8.5);
        gestor.cadastrarMaterial("Tijolo ceramico", "milheiro", 850.0);

        gestor.adicionarMaterialNaObra(1, 1);
        gestor.adicionarMaterialNaObra(1, 2);
        gestor.adicionarMaterialNaObra(1, 3);

        gestor.cadastrarFuncionario("Joao Pereira", "Pedreiro", 3200.0);
        gestor.cadastrarFuncionario("Maria Souza", "Engenheira", 9500.0);
        gestor.alocarFuncionario(1, 1);
        gestor.alocarFuncionario(1, 2);

        // BUG demonstrado: aceita qualquer String como status,
        // sem validar se a transicao e permitida.
        gestor.atualizarStatus(1, "QUALQUER_COISA_AQUI"); // nao deveria ser aceito
        gestor.atualizarStatus(1, "EM_ANDAMENTO");

        System.out.printf("\nCusto de materiais (Obra 1): R$ %,.2f%n",
                gestor.calcularCustoMateriais(1));

        // BUG demonstrado: cancelarObra retorna 1 sempre,
        // mesmo que a obra tenha 3 materiais associados.
        gestor.adicionarMaterialNaObra(2, 1);
        gestor.adicionarMaterialNaObra(2, 2);
        int liberados = gestor.cancelarObra(2);
        System.out.println("Materiais liberados: " + liberados
                + " (esperado: 2, mas retornou incorretamente 1)");

        gestor.gerarRelatorio();
        gestor.salvarEmArquivo("obras_dados.txt");
    }
}
