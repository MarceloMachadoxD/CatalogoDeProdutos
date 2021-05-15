package com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
