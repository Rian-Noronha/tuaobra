import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/casasConstrucao")
public class CasaConstrucaoController {

    @Autowired
    private CasaConstrucaoService casaConstrucaoService;

    // Adicionando uma nova Casa de Construção
    @PostMapping
    public ResponseEntity<CasaConstrucao> adicionarCasaConstrucao(@RequestBody CasaConstrucao casaConstrucao) {
        CasaConstrucao novaCasaConstrucao = casaConstrucaoService.salvarCasaConstrucao(casaConstrucao);
        return new ResponseEntity<>(novaCasaConstrucao, HttpStatus.CREATED);
    }

    // Listando as Casas de Construção
    @GetMapping
    public ResponseEntity<List<CasaConstrucao>> listarTodasCasasConstrucao() {
        List<CasaConstrucao> casasConstrucao = casaConstrucaoService.buscarTodasCasasConstrucao();
        return new ResponseEntity<>(casasConstrucao, HttpStatus.OK);
    }

    // Buscar uma Casa de Construção pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<CasaConstrucao> buscarCasaConstrucaoPorId(@PathVariable Long id) {
        Optional<CasaConstrucao> casaConstrucao = casaConstrucaoService.buscarCasaConstrucaoPorId(id);
        if (casaConstrucao.isPresent()) {
            return new ResponseEntity<>(casaConstrucao.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar uma Casa de Construção existente
    @PutMapping("/{id}")
    public ResponseEntity<CasaConstrucao> atualizarCasaConstrucao(@PathVariable Long id, @RequestBody CasaConstrucao casaConstrucaoAtualizada) {
        Optional<CasaConstrucao> casaConstrucaoExistente = casaConstrucaoService.buscarCasaConstrucaoPorId(id);
        if (casaConstrucaoExistente.isPresent()) {
            CasaConstrucao casaConstrucao = casaConstrucaoExistente.get();
            casaConstrucao.setNome(casaConstrucaoAtualizada.getNome());
            casaConstrucao.setDescricao(casaConstrucaoAtualizada.getDescricao());
            casaConstrucao.setHorario(casaConstrucaoAtualizada.getHorario());
            casaConstrucao.setAvaliacao(casaConstrucaoAtualizada.getAvaliacao());
            casaConstrucao.setUrlImagemPerfil(casaConstrucaoAtualizada.getUrlImagemPerfil());
            casaConstrucao.setFrete(casaConstrucaoAtualizada.getFrete());
            casaConstrucao.setEmail(casaConstrucaoAtualizada.getEmail());
            casaConstrucao.setContatoWhatsApp(casaConstrucaoAtualizada.getContatoWhatsApp());
            casaConstrucao.setEndereco(casaConstrucaoAtualizada.getEndereco());
            CasaConstrucao casaConstrucaoSalva = casaConstrucaoService.salvarCasaConstrucao(casaConstrucao);
            return new ResponseEntity<>(casaConstrucaoSalva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletar uma Casa de Construção pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCasaConstrucao(@PathVariable Long id) {
        Optional<CasaConstrucao> casaConstrucao = casaConstrucaoService.buscarCasaConstrucaoPorId(id);
        if (casaConstrucao.isPresent()) {
            casaConstrucaoService.deletarCasaConstrucao(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
