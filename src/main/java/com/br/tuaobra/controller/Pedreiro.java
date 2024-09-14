import java.util.ArrayList;
import java.util.List;

public class PedreiroController {

    private List<Pedreiro> pedreiros;

    // Construtor
    public PedreiroController() {
        this.pedreiros = new ArrayList<>();
    }

    // Método para adicionar um pedreiro
    public void adicionarPedreiro(Pedreiro pedreiro) {
        pedreiros.add(pedreiro);
        System.out.println("Pedreiro " + pedreiro.getNome() + " adicionado com sucesso.");
    }

    // Método para remover um pedreiro pelo ID
    public void removerPedreiro(Long id) {
        Pedreiro pedreiroARemover = null;
        for (Pedreiro p : pedreiros) {
            if (p.getId().equals(id)) {
                pedreiroARemover = p;
                break;
            }
        }

        if (pedreiroARemover != null) {
            pedreiros.remove(pedreiroARemover);
            System.out.println("Pedreiro " + pedreiroARemover.getNome() + " removido com sucesso.");
        } else {
            System.out.println("Pedreiro com ID " + id + " não encontrado.");
        }
    }

    // Método para listar todos os pedreiros
    public void listarPedreiros() {
        if (pedreiros.isEmpty()) {
            System.out.println("Nenhum pedreiro cadastrado.");
        } else {
            for (Pedreiro p : pedreiros) {
                System.out.println(p);
            }
        }
    }

    // Método para buscar um pedreiro pelo ID
    public Pedreiro buscarPedreiroPorId(Long id) {
        for (Pedreiro p : pedreiros) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        System.out.println("Pedreiro com ID " + id + " não encontrado.");
        return null;
    }

    // Método para atualizar as informações de um pedreiro
    public void atualizarPedreiro(Long id, String novaDescricao, double novaAvaliacao) {
        Pedreiro pedreiro = buscarPedreiroPorId(id);
        if (pedreiro != null) {
            pedreiro.setDescricao(novaDescricao);
            pedreiro.setAvaliacao(novaAvaliacao);
            System.out.println("Informações do pedreiro " + pedreiro.getNome() + " atualizadas com sucesso.");
        }
    }

    // Método para calcular a média de avaliação dos pedreiros
    public double calcularMediaAvaliacoes() {
        if (pedreiros.isEmpty()) {
            System.out.println("Nenhum pedreiro cadastrado para calcular a média.");
            return 0;
        }

        double somaAvaliacoes = 0;
        for (Pedreiro p : pedreiros) {
            somaAvaliacoes += p.getAvaliacao();
        }

        return somaAvaliacoes / pedreiros.size();
    }
}
