
import java.util.List;
import java.util.Scanner;
public class Starter {

    Scanner scanner = new Scanner(System.in);

    // 5 10
    // 10 - 5 = 6 / 2 = 3

    public long start() {
        System.out.print("Введите нижнюю границу поиска (>0): ");
        int lowerBound = scanner.nextInt();
        System.out.print("Введите верхнюю границу поиска (больше нижней границы): ");
        int upperBound = scanner.nextInt();
        System.out.println("Введите количество потоков [1; 10]: ");
        int maxThreads = scanner.nextInt();

        PrimeCounter primeCounter = new PrimeCounter(lowerBound, upperBound, maxThreads);

        return primeCounter.run();

    }
}
