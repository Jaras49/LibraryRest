package com.library.exception;

public class RentException extends Exception {

    public static final String INVALID_BOOK_ID = "Invalid book id";
    public static final String INVALID_RENT_ID = "Invalid rent id";
    public static final String INVALID_USER_ID = "Invalid user id";
    public static final String NOTHING_RENTED = "Nothing rented";

    public RentException(String message) {
        super(message);
    }
}
