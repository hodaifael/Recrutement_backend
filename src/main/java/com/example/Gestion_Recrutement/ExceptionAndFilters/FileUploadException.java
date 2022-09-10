package com.example.Gestion_Recrutement.ExceptionAndFilters;

public class FileUploadException extends RuntimeException {

    public FileUploadException(String msg) {
        super(msg);
    }
}