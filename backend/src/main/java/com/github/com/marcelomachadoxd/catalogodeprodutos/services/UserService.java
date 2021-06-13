package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.RoleDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.UserDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.UserInsertDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Role;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.User;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.RoleRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.UserRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.DatabaseException;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);

        return list.map( x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        try {
            Optional<User> obj = userRepository.findById(id);
            User user = obj.orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

            return new UserDTO(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Not found id: " + id);

        }
    }

    @Transactional(readOnly = false)
    public UserDTO insert(UserInsertDTO dto) {
        User user = new User();

        copyDtoToEntity(dto, user);
        user.setPassword( passwordEncoder.encode(dto.getPassword()) );

        user = userRepository.save(user);
        return new UserDTO(user);

    }


    @Transactional(readOnly = false)
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User user = userRepository.getOne(id);

            copyDtoToEntity(dto, user);

            return new UserDTO(user);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Not found id: " + id);

        }
    }


    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Not found id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation trying to delete " + id);
        }
    }


    private void copyDtoToEntity(UserDTO dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        user.getRoles().clear();


        for (RoleDTO roleDTO : dto.getRole()) {

            Role role = roleRepository.getOne(roleDTO.getId());
            user.getRoles().add(role);
        }

    }

}