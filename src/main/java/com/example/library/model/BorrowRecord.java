package com.example.library.model;

public class BorrowRecord {
    private int bookId;
    private String readerName;
    private String borrowDate;
    private String returnDate;
    public BorrowRecord(int bookId, String readerName, String borrowDate) {
        this.bookId = bookId;
        this.readerName = readerName;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }
    public int getBookId() { return bookId; }
    public String getReaderName() { return readerName; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public boolean isReturned() { return returnDate != null; }
    public String toString() {
        String status = isReturned() ? "已归还(" + returnDate + ")" : "未归还";
        return "图书ID:" + bookId + " | 借阅人:" + readerName + " | 借出:" + borrowDate + " | " + status;
    }
}
