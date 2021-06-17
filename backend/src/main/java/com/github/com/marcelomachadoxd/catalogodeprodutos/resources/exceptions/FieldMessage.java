package com.github.com.marcelomachadoxd.catalogodeprodutos.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private String fieldName;
    private String fieldMessage;


    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String fieldMessage) {
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }
}
