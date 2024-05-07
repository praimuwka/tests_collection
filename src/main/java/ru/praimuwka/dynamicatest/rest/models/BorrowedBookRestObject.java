package ru.praimuwka.dynamicatest.rest.models;

import java.util.*;

public class BorrowedBookRestObject {
    String clientName;
    Date clientBirthday;
    String bookTitle;
    String bookAuthor;
    String bookIsbn;
    Date borrowedDate;

    public BorrowedBookRestObject(final String clientName, final Date clientBirthday, final String bookTitle,
                                  final String bookAuthor,final String bookIsbn, final Date borrowedDate) {
        this.clientName = clientName;
        this.clientBirthday = clientBirthday;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.borrowedDate = borrowedDate;
    }
}
