package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.utils.InputUtil;
import java.util.*;
import java.util.stream.Collectors;

public class BookService {
    private List<Book> books = new ArrayList<>();
    private int nextId = 1;
    public void addBook() {
        System.out.println("\n===== 添加图书 =====");
        String title = InputUtil.readString("书名：");
        String author = InputUtil.readString("作者：");
        double price = InputUtil.readDouble("价格：");
        String category = InputUtil.readString("分类：");

        Book book = new Book(nextId++, title, author, price, category);
        books.add(book);
        System.out.println("✅ 添加成功！" + book);
    }
    public void listAllBooks() {
        System.out.println("\n===== 所有图书 =====");
        if (books.isEmpty()) {
            System.out.println("暂无图书。");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("共 " + books.size() + " 本图书。");
    }
    public void searchBooks() {
        System.out.println("\n===== 搜索图书 =====");
        String keyword = InputUtil.readString("输入书名或作者关键词：");
        List<Book> results = books.stream()
            .filter(b -> b.getTitle().contains(keyword) || b.getAuthor().contains(keyword))
            .collect(Collectors.toList());
        if (results.isEmpty()) {
            System.out.println("未找到相关图书。");
        } else {
            System.out.println("找到 " + results.size() + " 本：");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }
    public void deleteBook() {
        System.out.println("\n===== 删除图书 =====");
        int id = InputUtil.readInt("输入要删除的图书ID：");
        Book toDelete = null;
        for (Book book : books) {
            if (book.getId() == id) {
                toDelete = book;
                break;
            }
        }
        if (toDelete == null) {
            System.out.println("⚠ 未找到ID为 " + id + " 的图书。");
            return;
        }
        if (toDelete.isBorrowed()) {
            System.out.println("⚠ 该图书已被借出，无法删除。");
            return;
        }
        if (InputUtil.readConfirm("确认删除《" + toDelete.getTitle() + "》？")) {
            books.remove(toDelete);
            System.out.println("✅ 删除成功！");
        } else {
            System.out.println("已取消删除。");
        }
    }
    public void categoryStatistics() {
        System.out.println("\n===== 分类统计 =====");
        if (books.isEmpty()) {
            System.out.println("暂无图书。");
            return;
        }
        Map<String, Integer> categoryCount = new HashMap<>();
        Map<String, Double> categoryTotalPrice = new HashMap<>();
        for (Book book : books) {
            String cat = book.getCategory();
            categoryCount.put(cat, categoryCount.getOrDefault(cat, 0) + 1);
            categoryTotalPrice.put(cat, categoryTotalPrice.getOrDefault(cat, 0.0) + book.getPrice());
        }
        System.out.printf("%-10s %-6s %-10s%n", "分类", "数量", "总价值");
        System.out.println("-------------------------------");
        for (String cat : categoryCount.keySet()) {
            System.out.printf("%-10s %-6d ¥%-9.2f%n", cat, categoryCount.get(cat), categoryTotalPrice.get(cat));
        }
        System.out.println("-------------------------------");
        System.out.printf("合计：%-3d 本，总价值 ¥%.2f%n", books.size(),
            books.stream().mapToDouble(Book::getPrice).sum());
    }
    public Book findById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }
    public List<Book> getAllBooks() { return books; }
    public int getNextId() { return nextId; }
    public void setNextId(int nextId) { this.nextId = nextId; }
}
