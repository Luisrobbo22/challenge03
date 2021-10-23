package br.com.fiap.challenge03.controller;

import br.com.fiap.challenge03.dto.UsuarioDTO;
import br.com.fiap.challenge03.model.Usuario;
import br.com.fiap.challenge03.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> findAll(@RequestParam(required = false) String nome,
                                 @PageableDefault Pageable pageable) {
        return usuarioService.findAll(nome, pageable);
    }

    @PostMapping()
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario,
                                          UriComponentsBuilder componentsBuilder) {
        usuarioService.create(usuario);

        URI uri = componentsBuilder.
                path("api/usuario/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Usuario> findOne(@PathVariable Integer id ,
                                           @RequestParam(required = false) String cpf) {

        if (Optional.ofNullable(cpf).isPresent()
                && Optional.ofNullable(id).isPresent()) {

            Usuario usuario = usuarioService.findByCpf(cpf);
            if (!Optional.ofNullable(usuario).isPresent()){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(usuario);
        }

        return ResponseEntity.of(usuarioService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id,
                                          @RequestBody @Valid UsuarioDTO userDTO) {

        Usuario usuario = usuarioService.fromDTO(userDTO);
        usuario.setId(id);
        usuario = usuarioService.update(id, usuario);

        return ResponseEntity.ok(usuario);
    }
}
