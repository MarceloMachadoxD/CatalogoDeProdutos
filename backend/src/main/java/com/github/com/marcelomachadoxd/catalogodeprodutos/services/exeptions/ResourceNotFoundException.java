package com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
