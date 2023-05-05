import java.util.ArrayList;
import java.util.List;

public class PrimeRunnable implements Runnable {
    private int id;
    private int lowerBound;
    private int upperBound;
    private List<Integer> simplePrimes = new ArrayList<>();

    public PrimeRunnable(int lowerBound, int upperBound, int id) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = lowerBound; i <= upperBound; i++) {
            if (isPrime(i)) {
                simplePrimes.add(i);
            }
        }
    }

    public List<Integer> getSimplePrimes() {
        return simplePrimes;
    }
    // Проверка на простоту
    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i+= 1) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}