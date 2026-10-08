import java.io.PrintStream;                 
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class PrintedEdition {
    private String name;
    private int year;
    private int pages;

    public static int num_r = 0;

    public PrintedEdition(String n, int y, int p) {
        name = n;
        year = y;
        pages = p;
        num_r++;
    }

    public PrintedEdition(String in_name) {
        name = in_name;
        year = 2020;
        pages = 100;
        num_r++;
    }

    public PrintedEdition() {
        name = "Видання";
        year = 2000;
        pages = 50;
        num_r++;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public void Show() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String s;
        s = name + " " + year + " " + pages;
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj instanceof PrintedEdition) {
            PrintedEdition obj1 = (PrintedEdition) obj;
            if (name.equals(obj1.getName()) && year == obj1.getYear() && pages == obj1.getPages())
                b = true;
        }
        return b;
    }
}

class Magazine extends PrintedEdition {
    private int number;

    public Magazine(String n, int y, int p, int num) {
        super(n, y, p);
        number = num;
    }

    public Magazine(String in_name) {
        super(in_name);
        number = 1;
    }

    public Magazine() {
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Magazine{} №" + number + " " + super.toString();
    }
}

class Book extends PrintedEdition {
    private String author;

    public Book(String n, int y, int p, String a) {
        super(n, y, p);
        author = a;
    }

    public Book(String in_name) {
        super(in_name);
        author = "Невідомий";
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{} " + author + " " + super.toString();
    }
}

class Textbook extends Book {
    private String subject;

    public Textbook(String n, int y, int p, String a, String subj) {
        super(n, y, p, a);
        subject = subj;
    }

    public Textbook(String in_name) {
        super(in_name);
        subject = "Загальний";
    }

    public Textbook() {
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Textbook{} [" + subject + "] " + super.toString();
    }
}

public class task1 {
    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception e) {
            System.out.println("Помилка налаштування кодування виводу");
        }


        Scanner in = new Scanner(System.in);


        System.out.print("Введіть кількість елементів масиву: ");
        int count = in.nextInt();
        in.nextLine();

        PrintedEdition[] arr = new PrintedEdition[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nОберіть тип (1 - Видання, 2 - Журнал, 3 - Книга, 4 - Підручник): ");
            int type = in.nextInt();
            in.nextLine();

            System.out.print("Введіть назву: ");
            String name = in.nextLine();

            System.out.print("Введіть рік: ");
            int year = in.nextInt();

            System.out.print("Введіть кількість сторінок: ");
            int pages = in.nextInt();
            in.nextLine();

            if (type == 2) {
                System.out.print("Введіть номер журналу: ");
                int num = in.nextInt();
                in.nextLine();
                arr[i] = new Magazine(name, year, pages, num);
            } else if (type == 3) {
                System.out.print("Введіть автора: ");
                String author = in.nextLine();
                arr[i] = new Book(name, year, pages, author);
            } else if (type == 4) {
                System.out.print("Введіть автора: ");
                String author = in.nextLine();
                System.out.print("Введіть предмет: ");
                String subj = in.nextLine();
                arr[i] = new Textbook(name, year, pages, author, subj);
            } else {
                arr[i] = new PrintedEdition(name, year, pages);
            }
        }

        System.out.println("\n--- Введений масив ---");
        for (int i = 0; i < arr.length; i++) {
            arr[i].Show();
        }

        in.close();
        System.out.println("Кількість створених об'єктів = " + PrintedEdition.num_r);
    }
}