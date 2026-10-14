import java.util.Scanner;

public class Main {
    public static ComplexMatrix inputMatrix(Scanner scanner, String name) {
        System.out.println("Ввод матрицы " + name);
        System.out.println("Введите количество строк ");
        int rows = scanner.nextInt();
        System.out.println("Введите количество столбцов ");
        int cols = scanner.nextInt();
        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Элемент [" + i + "][" + j + "]:");
                System.out.print("  Действительная часть (re): ");
                double re = scanner.nextDouble();
                System.out.print("  Мнимая часть (im): ");
                double im = scanner.nextDouble();
                matrix.setElem(i, j, new ComplexNum(re, im));
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nСписок операций");
            System.out.println("1. Сложение матриц (A + B)");
            System.out.println("2. Умножение матриц (A * B)");
            System.out.println("3. Деление матриц (A / B)");
            System.out.println("4. Транспонирование матрицы");
            System.out.println("5. Вычисление определителя матрицы");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int action = scanner.nextInt();

            if (action == 0) {
                System.out.println("Программа завершена");
                break;
            }

            try {
                switch (action) {
                    case 1: {
                        ComplexMatrix a = inputMatrix(scanner, "A");
                        ComplexMatrix b = inputMatrix(scanner, "B");
                        ComplexMatrix result = a.add(b);
                        System.out.println("Результат A + B:");
                        result.display();
                        break;
                    }
                    case 2: {
                        ComplexMatrix a = inputMatrix(scanner, "A");
                        ComplexMatrix b = inputMatrix(scanner, "B");
                        ComplexMatrix result = a.multiply(b);
                        System.out.println("Результат A * B:");
                        result.display();
                        break;
                    }
                    case 3: {
                        ComplexMatrix a = inputMatrix(scanner, "A");
                        ComplexMatrix b = inputMatrix(scanner, "B");
                        ComplexMatrix result = a.divide(b);
                        System.out.println("Результат A / B:");
                        result.display();
                        break;
                    }
                    case 4: {
                        ComplexMatrix a = inputMatrix(scanner, "A");
                        ComplexMatrix result = a.transp();
                        System.out.println("Транспонированная матрица А:");
                        result.display();
                        break;
                    }
                    case 5: {
                        ComplexMatrix a = inputMatrix(scanner, "A");
                        ComplexNum result = a.det();
                        System.out.println("Определитель матрицы А: " + result.display());
                        break;
                    }
                    default:
                        System.out.println("Неверный пункт меню!");
                        break;
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
