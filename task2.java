import java.io.PrintStream;                 
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

abstract class Pair {
    protected double a;
    protected double b;

    public Pair() {
        a = 0;
        b = 0;
    }

    public Pair(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public abstract Pair add(Pair p);
    public abstract Pair sub(Pair p);
    public abstract Pair mul(Pair p);

    public void Show() {
        System.out.println(this.toString());
    }
}

class Complex extends Pair {

    public Complex(double re, double im) {
        super(re, im);
    }

    public Complex(double in_re) {
        super(in_re, 0);
    }

    public Complex() {
    }

    @Override
    public Pair add(Pair p) {
        return new Complex(this.a + p.a, this.b + p.b);
    }

    @Override
    public Pair sub(Pair p) {
        return new Complex(this.a - p.a, this.b - p.b);
    }

    @Override
    public Pair mul(Pair p) {
        double re = this.a * p.a - this.b * p.b;
        double im = this.a * p.b + this.b * p.a;
        return new Complex(re, im);
    }

    @Override
    public String toString() {
        String s;
        if (b >= 0) {
            s = "Complex{} " + a + " + " + b + "i";
        } else {
            s = "Complex{} " + a + " - " + Math.abs(b) + "i";
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b_res = false;
        if (obj instanceof Complex) {
            Complex obj1 = (Complex) obj;
            if (this.a == obj1.a && this.b == obj1.b) {
                b_res = true;
            }
        }
        return b_res;
    }
}

class Rational extends Pair {

    public Rational(double num, double den) {
        super(num, den == 0 ? 1 : den); 
    }

    public Rational(double in_num) {
        super(in_num, 1);
    }

    public Rational() {
        super(0, 1);
    }

    @Override
    public Pair add(Pair p) {
        double num = this.a * p.b + p.a * this.b;
        double den = this.b * p.b;
        return new Rational(num, den);
    }

    @Override
    public Pair sub(Pair p) {
        double num = this.a * p.b - p.a * this.b;
        double den = this.b * p.b;
        return new Rational(num, den);
    }

    @Override
    public Pair mul(Pair p) {
        double num = this.a * p.a;
        double den = this.b * p.b;
        return new Rational(num, den);
    }

    @Override
    public String toString() {
        String s;
        s = "Rational{} " + (int) a + "/" + (int) b;
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b_res = false;
        if (obj instanceof Rational) {
            Rational obj1 = (Rational) obj;
            // Дроби a/b та c/d рівні, якщо a*d == b*c
            if (this.a * obj1.b == this.b * obj1.a) {
                b_res = true;
            }
        }
        return b_res;
    }
}

public class task2 {
    public static void main(String[] args) {


        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception e) {
            System.out.println("Помилка налаштування кодування виводу");
        }

        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8.name());
       

        System.out.print("Введіть кількість елементів масиву: ");
        int count = in.nextInt();
        in.nextLine();

        Pair[] arr = new Pair[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nОберіть тип об'єкта (1 - Complex, 2 - Rational): ");
            int type = in.nextInt();
            in.nextLine();

            if (type == 1) {
                System.out.print("Введіть дійсну частину (a): ");
                double re = in.nextDouble();
                System.out.print("Введіть уявну частину (b): ");
                double im = in.nextDouble();
                in.nextLine();
                arr[i] = new Complex(re, im);
            } else {
                System.out.print("Введіть чисельник (a): ");
                double num = in.nextDouble();
                System.out.print("Введіть знаменник (b): ");
                double den = in.nextDouble();
                in.nextLine();
                arr[i] = new Rational(num, den);
            }
        }

        System.out.println("\n Введений масив об'єктів");
        for (int i = 0; i < arr.length; i++) {
            arr[i].Show();
        }

        System.out.println("\n Демонстрація роботи методів Complex");
        Complex c1 = new Complex(4, 5);
        Complex c2 = new Complex(2, 3);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c1 + c2 = " + c1.add(c2));
        System.out.println("c1 - c2 = " + c1.sub(c2));
        System.out.println("c1 * c2 = " + c1.mul(c2));
        System.out.println("c1.equals(c2): " + c1.equals(c2));

        System.out.println("\n Демонстрація роботи методів Rational");
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(2, 4);
        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("r1 + r2 = " + r1.add(r2));
        System.out.println("r1 - r2 = " + r1.sub(r2));
        System.out.println("r1 * r2 = " + r1.mul(r2));
        System.out.println("r1.equals(r2) (перевірка 1/2 == 2/4): " + r1.equals(r2));

        in.close();
    }
}