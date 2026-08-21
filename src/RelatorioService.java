import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioService {

    private final List<Obra> obras;

    public RelatorioService(List<Obra> obras) {
        this.obras = obras;
    }

    public void gerarRelatorio() {
        System.out.println("\n=== Relatorio de Obras ===");
        for (Obra obra : obras) {
            System.out.println(formatarLinhaRelatorio(obra));
        }
    }

    public void salvarEmArquivo(String caminho) {
        try (FileWriter fw = new FileWriter(caminho)) {
            fw.write("=== Obras ===\n");
            for (Obra obra : obras) {
                fw.write(obra.getNome() + " | " + obra.getStatus() + " | R$" + obra.getOrcamento() + "\n");
            }
            System.out.println("Relatorio salvo em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    private String formatarLinhaRelatorio(Obra obra) {
        return String.format("%-30s | Status: %-15s | Orcamento: R$ %,.2f | Materiais: %d",
                obra.getNome(), obra.getStatus(), obra.getOrcamento(), obra.getItensMaterial().size());
    }
}
