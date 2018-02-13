package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        long runtimeStart = System.currentTimeMillis();

        System.out.println("maximum        : " + Configuration.instance.maximum);

        List<Long> primes = new ConcurrentSieveOfEratosthenes().sieve(Configuration.instance.maximum);

        Long[] primeArray = new Long[primes.size()];
        primeArray = primes.toArray(primeArray);
        primes = null;
        //Set<Integer> primes = new ConcurrentSieveOfEratosthenes().sieveInRange(2, Configuration.instance.maximum);

        System.out.println("runtime (ms)   : " + (System.currentTimeMillis() - runtimeStart));
        for (int i = 0; i < 100 && i < primeArray.length; i++) {
            System.out.println((i +1) + ": " + primeArray[i]);
        }
        System.out.println("primeFactors of 8:");
        SameQuantatySolution solver = new SameQuantatySolution();
        Long[] factors = solver.primeFactorisation(8, primeArray);
        for(long factor : factors) {
            System.out.println(factor);
        }
        factors = null;

        System.out.println("Solution for k = 3");
        ArrayList<SolutionElement> solution = solver.findSolutionInRange(3, 0,100, primeArray);
        System.out.println("length: " + solution.size());
        for(SolutionElement element : solution) {
            System.out.println(element);
        }

        System.out.println("Solution for k = 4");
        solution = solver.findSolutionInRange(4, 0,1000000, primeArray);
        System.out.println("length: " + solution.size());
        for(SolutionElement element : solution) {
            System.out.println(element);
        }

        System.out.println("Solution for k = 5");
        solution = solver.findSolutionInRange(5, 0,1000000, primeArray);
        System.out.println("length: " + solution.size());
        for(SolutionElement element : solution) {
            System.out.println(element);
        }

        System.out.println("Solution for k = 6");
        solution = solver.findSolutionInRange(6, 0,1000000, primeArray);
        System.out.println("length: " + solution.size());
        for(SolutionElement element : solution) {
            System.out.println(element);
        }

        System.out.println("Solution for k = 7");
        solution = solver.findSolutionInRange(7, 0,1000000, primeArray);
        System.out.println("length: " + solution.size());
        for(SolutionElement element : solution) {
            System.out.println(element);
        }
    }
}
