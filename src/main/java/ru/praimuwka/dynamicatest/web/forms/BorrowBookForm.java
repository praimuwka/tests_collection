package ru.praimuwka.dynamicatest.web.forms;

public class BorrowBookForm {
    Long bookId;
    Long clientId;
    public Long getBookId() {
        return bookId;
    }
    public Long getClientId() {
        return clientId;
    }
    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }
    public void setClientId(final Long clientId) {
        this.clientId = clientId;
    }
}
