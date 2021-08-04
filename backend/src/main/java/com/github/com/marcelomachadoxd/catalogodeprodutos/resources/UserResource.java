package com.github.com.marcelomachadoxd.catalogodeprodutos.resources;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.UserDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.UserInsertDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.UserUpdateDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll( Pageable pageable) {

        Page<UserDTO> list = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findByID(@PathVariable Long id){
        UserDTO newDto = userService.findById(id);

        return ResponseEntity.ok().body(newDto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(@Valid @RequestBody UserInsertDTO dto){
        UserDTO newDto = userService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto){
        UserDTO newDto = userService.update(id, dto);

        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }







}
