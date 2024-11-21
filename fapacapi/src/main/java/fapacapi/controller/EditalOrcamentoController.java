package fapacapi.controller;

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
import org.springframework.web.bind.annotation.RestController;

import fapacapi.controller.dto.EditalOrcamentoDto;
import fapacapi.controller.mapper.EditalOrcamentoMapper;
import fapacapi.model.Edital;
import fapacapi.service.EditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/editalOrcamento", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Orcamento do edital",
    description = "Endpoints para gerenciar as orçamentos do edital"
)
public class EditalOrcamentoController{

    private final EditalService servico;
    private final EditalOrcamentoMapper mapper;

    public EditalOrcamentoController(EditalService servico, EditalOrcamentoMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    @Operation(
        summary = "Obter a ou filtrar por termo de busca",
        description = "Obtém uma lista paginada de todos os profissionais cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<?> get(String termoBusca, boolean unpaged, Pageable page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "editalOrcamento encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "editalOrcamento não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter editalOrcamento por ID",
        description = "Obtém o editalOrcamento  com o ID informado"
    )
    public ResponseEntity<EditalOrcamentoDto> get(@PathVariable("id") Long id) {
        Edital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        EditalOrcamentoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<EditalOrcamentoDto> insert(@RequestBody @Valid EditalOrcamentoDto objeto) {
        Edital objetoConvertido = mapper.toEntity(objeto);
        Edital registro = servico.save(objetoConvertido);
        EditalOrcamentoDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping("/")
    @Operation(
        summary = "Atualizar chamada",
        description = "Atualiza os dados de uma chamada existente"
    )
    public ResponseEntity<EditalOrcamentoDto> update(@RequestBody @Valid EditalOrcamentoDto objeto) {
        Edital registroAtualizado = servico.updateOrcamento(objeto);

        // Converte a entidade atualizada para DTO e retorna a resposta
        EditalOrcamentoDto dto = mapper.toDto(registroAtualizado);
        return ResponseEntity.ok(dto);
        
    }
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir a chamada de um edital",
        description = "Exclui a chamada de um edital com a situação em edição"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.deleteChamadas(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
