package main;

import java.util.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentSieveOfEratosthenes {
    public List<Long> sieve(int maximumValue) {
        List<Long> primeNumbers = new ArrayList<>();

        try {
            final List<Callable<List<Long>>> partitions = new ArrayList<>();
            final ExecutorService executorPool = Executors.newFixedThreadPool(Configuration.instance.threads);
            int sliceSize = maximumValue / Configuration.instance.threads;

            for (int i = 2;i <= maximumValue;i+=sliceSize) {
                final int from = i;
                int to = i + sliceSize;
                if (to > maximumValue)
                    to = maximumValue;
                final int end = to;
                partitions.add(new Callable<List<Long>>() {
                    public ArrayList<Long> call() { return sieveInRange(from,end); }
                });
            }

            final List<Future<List<Long>>> resultFromParts = executorPool.invokeAll(partitions,10000,TimeUnit.SECONDS);
            executorPool.shutdown();

            for (final Future<List<Long>> result : resultFromParts)
                primeNumbers.addAll(result.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return primeNumbers;
    }

    public ArrayList<Long> sieveInRange(int minimumValue, int maximumValue) {
        int size = (maximumValue - minimumValue + 1);
        boolean[] isPrime = new boolean[size];

        if(minimumValue % 2 == 0) {
            for (int i = 1; i < size; i += 2)
                isPrime[i] = true;
        }
        else {
            for (int i = 0; i < size; i += 2)
                isPrime[i] = true;
        }

        for (int i = 3;i * i <= maximumValue;i+= 2) {
            int first = minimumValue;
            while(first % i != 0) {
                first++;
            }
            if (first == i ) {
                for (int j = first +i; j <= maximumValue; j += i) {
                    isPrime[j - minimumValue] = false;
                }
            }
            else {
                for (int j = first; j <= maximumValue; j += i) {
                    isPrime[j - minimumValue] = false;
                }
            }
        }

        ArrayList<Long> values = new ArrayList<>();

        for (int i = 0;i < size;i++)
            if (isPrime[i])
                values.add((Long)(long)i + minimumValue);

        if (minimumValue == 2)
            values.add(0, (Long)(long)2);

        return values;
    }

}