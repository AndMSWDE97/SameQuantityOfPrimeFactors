package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SameQuantatySolution {
    public ArrayList<SolutionElement> findSolutionInRange(int k, int minimumValue, int maximumValue, Long[] primeArray) {
        int numberOfPrimesNeeded = k+1;
        ArrayList<SolutionElement> solution = new ArrayList<>();
        for(long i = minimumValue; i <= maximumValue -numberOfPrimesNeeded && i < primeArray.length -numberOfPrimesNeeded; i++) {
            long[] summs = new long[k];
            for(int j = 0; j <k; j++) {
                summs[j] = primeArray[(int)i+j] +primeArray[(int)i+j + 1];
                Long[] factors = primeFactorisation(summs[j],primeArray);
                if(factors.length == k) {
                    solution.add(new SolutionElement(new long[] {primeArray[(int)i+j], primeArray[(int)i+j+1]}, summs[j], factors));
                } else {
                    solution.clear();
                    break;
                }
            }
            if (!solution.isEmpty()) break;
        }
        return solution;
    }

    public ArrayList<SolutionElement> findSolution(int k, int maximum, List<Long> primes) {
        return null;
    }

    public Long[] primeFactorisation(long n, Long[] primeArray) {
        ArrayList<Long> factors = new ArrayList<>();
        long sqrt = (long) Math.sqrt(n);
        for(int i = 0; primeArray[i] <= sqrt; i++) {
            while (n % primeArray[i] == 0) {
                factors.add(primeArray[i]);
                n = n / primeArray[i];
            }
        }
        if(n > 1) factors.add(n);
        Long[] result = {};
        return factors.toArray(result);
    }
}
