package com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg) {
        super(msg);
    }
}
