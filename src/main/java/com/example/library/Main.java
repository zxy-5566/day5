package com.example.library;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import com.example.library.utils.InputUtil;
public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        BorrowService borrowService = new BorrowService();
        bookService.getAllBooks().add(new Book(
            1, "Java核心技术", "Cay S. Horstmann", 119.0, "编程"));
        bookService.getAllBooks().add(new Book(
            2, "Vue.js设计与实现", "霍春阳", 89.0, "编程"));
        bookService.getAllBooks().add(new Book(
            3, "三体", "刘慈欣", 23.0, "科幻"));
        bookService.getAllBooks().add(new Book(
            4, "活着", "余华", 20.0, "文学"));
        bookService.setNextId(5);
        System.out.println("================================");
        System.out.println("    📚 图书管理系统 v1.0");
        System.out.println("================================");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = InputUtil.readInt("请选择功能：");
            switch (choice) {
                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    bookService.listAllBooks();
                    break;
                case 3:
                    bookService.searchBooks();
                    break;
                case 4:
                    bookService.deleteBook();
                    break;
                case 5:
                    bookService.categoryStatistics();
                    break;
                case 6:
                    borrowService.borrowBook(bookService);
                    break;
                case 7:
                    borrowService.returnBook(bookService);
                    break;
                case 8:
                    borrowService.listRecords();
                    break;
                case 0:
                    running = false;
                    System.out.println("感谢使用，再见！");
                    break;
                default:
                    System.out.println("⚠ 无效选择，请重新输入。");
            }
        }
        InputUtil.close();
    }
    private static void showMenu() {
        System.out.println("\n========== 菜单 ==========");
        System.out.println("1. 添加图书");
        System.out.println("2. 查看所有图书");
        System.out.println("3. 搜索图书");
        System.out.println("4. 删除图书");
        System.out.println("5. 分类统计");
        System.out.println("6. 借书");
        System.out.println("7. 还书");
        System.out.println("8. 借阅记录");
        System.out.println("0. 退出");
        System.out.println("==========================");
    }
}
