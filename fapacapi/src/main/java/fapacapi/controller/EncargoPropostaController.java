package fapacapi.controller;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fapacapi.model.EncargoProposta;
import fapacapi.service.EncargoPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/encargoproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Encargo proposta",
    description = "Endpoints para gerenciar  Encargo proposta do pesquisador"
)

public class EncargoPropostaController implements IController<EncargoProposta> {

    private final EncargoPropostaService servico;

    public EncargoPropostaController(EncargoPropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os Encargos Propostas ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas os Encargos Propostas cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<EncargoProposta>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        
        @ParameterObject Pageable page) {
        Page<EncargoProposta> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargo Proposta  encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Encargo Proposta não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o Encargo Proposta por ID",
        description = "Obtém o Encargo Proposta com o ID informado"
    )
    public ResponseEntity<EncargoProposta> get(@PathVariable("id") Long id) {
        EncargoProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Encargo Proposta",
        description = "Cadastra um novo Encargo Proposta "
    )
    public ResponseEntity<EncargoProposta> insert(@RequestBody EncargoProposta objeto) {
    EncargoProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um Encargo Proposta",
        description = "Atualiza um Encargo Proposta"
    )
    public ResponseEntity<EncargoProposta> update(@RequestBody EncargoProposta objeto) {
        EncargoProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um Encargo Proposta",
        description = "Deleta um Encargo proposta com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}









