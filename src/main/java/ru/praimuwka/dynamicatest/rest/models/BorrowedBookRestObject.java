package ru.praimuwka.dynamicatest.rest.models;

import java.util.*;

public class BorrowedBookRestObject {
    String clientName;
    String clientBirthday;
    String bookTitle;
    String bookAuthor;
    String bookIsbn;
    String borrowedDate;

    public BorrowedBookRestObject(final String clientName, final String clientBirthday, final String bookTitle,
                                  final String bookAuthor,final String bookIsbn, final String borrowedDate) {
        this.clientName = clientName;
        this.clientBirthday = clientBirthday;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.borrowedDate = borrowedDate;
    }
}
