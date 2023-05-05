import java.util.List;

public class PrimeCounter {
    private final int lowerBound;
    private final int upperBound;
    private final int maxThreads;

    public PrimeCounter(int lowerBound, int upperBound, int maxThreads) {
        if (lowerBound < 1) {
            throw new IllegalArgumentException("Нижняя граница должна быть > 0");
        }
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("Верхняя граница должна быть больше нижней гранциы");
        }
        if (!(0 < maxThreads && maxThreads <= 10)) {
            throw new IllegalArgumentException("Количество потоков должно быть между [1; 10]");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.maxThreads = maxThreads;
    }

    public long run() {
        int blockSize = (upperBound - lowerBound + 1) / maxThreads;
        // Создание списков потоков и задач(наследников интерфейса Runnable) которые должен выполнить поток
        Thread[] threads = new Thread[maxThreads];
        PrimeRunnable[] primeRunnables = new PrimeRunnable[maxThreads];
        //Добавление потоков и задач в список
        for (int i = 0; i < maxThreads; i++) {
            int start = lowerBound + i * blockSize;
            int end = i == maxThreads - 1 ? upperBound : start + blockSize - 1;
            primeRunnables[i] = new PrimeRunnable(start, end, i);
            threads[i] = new Thread(primeRunnables[i]);
        }
        // Старт потоков
        long time0 = System.currentTimeMillis();
        for (int i = 0; i < maxThreads; i++) {
            threads[i].start();
        }

        //Убеждаемся что потоки выполнятся в правильном порядке
        for (int i = 0; i < maxThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Заняло времени: " + (System.currentTimeMillis() - time0));
        // Вывод
        long totalPrimeNumbers = 0L;
        for (int i = 0; i < maxThreads; i++) {
            System.out.print("Простых чисел в блоке " + i + ": ");
            List<Integer> list = primeRunnables[i].getSimplePrimes();
            System.out.println(list.size());
            System.out.println(list);
            totalPrimeNumbers += list.size();
        }
        return totalPrimeNumbers;
    }
}
