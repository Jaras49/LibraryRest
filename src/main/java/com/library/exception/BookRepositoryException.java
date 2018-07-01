package com.library.exception;

public class BookRepositoryException extends Exception {

    public static final String ERR_INVALID_BOOK_ID = "Book with specified ID doesn`t exists";

    public BookRepositoryException(String message) {
        super(message);
    }
}
