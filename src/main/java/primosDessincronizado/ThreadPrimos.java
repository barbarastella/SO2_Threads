package primosDessincronizado;

import java.util.ArrayList;

public class ThreadPrimos implements Runnable {

    private Integer start;
    private Integer end;

    private ArrayList<Integer> primes;

    public ThreadPrimos(Integer start, Integer end, ArrayList<Integer> primes) {
        this.start = start;
        this.end = end;
        this.primes = primes;
    }

    private boolean isPrime(int num) {

        if (num <= 1) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {

            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }
}
