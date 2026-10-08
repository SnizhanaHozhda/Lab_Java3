import java.io.PrintStream;                 
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

interface Pair {
    Pair add(Pair p);
    Pair sub(Pair p);
    Pair mul(Pair p);

    void Show();
    double getA();
    double getB();
}

class Complex implements Pair {
    private double a; 
    private double b; 

    public Complex(double re, double im) {
        a = re;
        b = im;
    }

    public Complex(double in_re) {
        a = in_re;
        b = 0;
    }

    public Complex() {
        a = 0;
        b = 0;
    }

    @Override
    public double getA() {
        return a;
    }

    @Override
    public double getB() {
        return b;
    }

    @Override
    public Pair add(Pair p) {
        return new Complex(this.a + p.getA(), this.b + p.getB());
    }

    @Override
    public Pair sub(Pair p) {
        return new Complex(this.a - p.getA(), this.b - p.getB());
    }

    @Override
    public Pair mul(Pair p) {
        double re = this.a * p.getA() - this.b * p.getB();
        double im = this.a * p.getB() + p.getA() * this.b;
        return new Complex(re, im);
    }

    @Override
    public void Show() {
        System.out.println(this.toString());
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
            if (this.a == obj1.getA() && this.b == obj1.getB()) {
                b_res = true;
            }
        }
        return b_res;
    }
}

class Rational implements Pair {
    private double a; 
    private double b; 

    public Rational(double num, double den) {
        a = num;
        b = (den == 0) ? 1 : den; 
    }

    public Rational(double in_num) {
        a = in_num;
        b = 1;
    }

    public Rational() {
        a = 0;
        b = 1;
    }

    @Override
    public double getA() {
        return a;
    }

    @Override
    public double getB() {
        return b;
    }

    @Override
    public Pair add(Pair p) {
        double num = this.a * p.getB() + p.getA() * this.b;
        double den = this.b * p.getB();
        return new Rational(num, den);
    }

    @Override
    public Pair sub(Pair p) {
        double num = this.a * p.getB() - p.getA() * this.b;
        double den = this.b * p.getB();
        return new Rational(num, den);
    }

    @Override
    public Pair mul(Pair p) {
        double num = this.a * p.getA();
        double den = this.b * p.getB();
        return new Rational(num, den);
    }

    @Override
    public void Show() {
        System.out.println(this.toString());
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
            if (this.a * obj1.getB() == this.b * obj1.getA()) {
                b_res = true;
            }
        }
        return b_res;
    }
}

public class task3 {
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

        // Масив посилань типу інтерфейсу Pair
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