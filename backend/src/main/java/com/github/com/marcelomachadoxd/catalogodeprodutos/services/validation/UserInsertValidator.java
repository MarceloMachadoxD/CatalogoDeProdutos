package com.github.com.marcelomachadoxd.catalogodeprodutos.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.UserInsertDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.User;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.UserRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;



public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());
        if (user != null){
            list.add(new FieldMessage("email", "e-mail " + dto.getEmail() + " já cadastrado na base de dados"));
        }



        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getFieldMessage()).addPropertyNode(e.getFieldName())
                .addConstraintViolation();
        }
        return list.isEmpty();
    }
}