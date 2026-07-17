package com.example.library.service;
import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.utils.InputUtil;
import java.util.*;
public class BorrowService {
    private List<BorrowRecord> records = new ArrayList<>();
    public void borrowBook(BookService bookService) {
        System.out.println("\n===== 借书 =====");
        int bookId = InputUtil.readInt("输入要借的图书ID：");
        Book book = bookService.findById(bookId);
        if (book == null) {
            System.out.println("⚠ 未找到该图书。");
            return;
        }
        if (book.isBorrowed()) {
            System.out.println("⚠ 该图书已被借出。");
            return;
        }
        String readerName = InputUtil.readString("借阅人姓名：");
        String date = InputUtil.readString("借出日期（如 2026-07-17）：");
        book.setBorrowed(true);
        records.add(new BorrowRecord(bookId, readerName, date));
        System.out.println("✅ 借书成功！《" + book.getTitle() + "》已借给 " + readerName);
    }
    public void returnBook(BookService bookService) {
        System.out.println("\n===== 还书 =====");
        int bookId = InputUtil.readInt("输入要归还的图书ID：");
        Book book = bookService.findById(bookId);
        if (book == null || !book.isBorrowed()) {
            System.out.println("⚠ 该图书未被借出或不存在。");
            return;
        }
        BorrowRecord record = null;
        for (BorrowRecord r : records) {
            if (r.getBookId() == bookId && !r.isReturned()) {
                record = r;
                break;
            }
        }
        if (record == null) {
            System.out.println("⚠ 未找到借阅记录。");
            return;
        }
        String returnDate = InputUtil.readString("归还日期（如 2026-07-20）：");
        record.setReturnDate(returnDate);
        book.setBorrowed(false);
        System.out.println("✅ 还书成功！《" + book.getTitle() + "》已归还。");
    }
    public void listRecords() {
        System.out.println("\n===== 借阅记录 =====");
        if (records.isEmpty()) {
            System.out.println("暂无借阅记录。");
            return;
        }
        List<BorrowRecord> active = new ArrayList<>();
        List<BorrowRecord> returned = new ArrayList<>();
        for (BorrowRecord r : records) {
            if (r.isReturned()) {
                returned.add(r);
            } else {
                active.add(r);
            }
        }
        System.out.println("--- 未归还 (" + active.size() + ") ---");
        for (BorrowRecord r : active) {
            System.out.println(r);
        }
        System.out.println("--- 已归还 (" + returned.size() + ") ---");
        for (BorrowRecord r : returned) {
            System.out.println(r);
        }
    }
}
