package main;

public class SolutionElement {
    private long[] summands;
    private long number;
    private Long[] factors;
    
    public SolutionElement(long[] summands, long number, Long[] factors) {
        this.factors = factors;
        this.summands = summands;
        this.number = number;
    }

    public long[] getSummands() {
        return summands;
    }

    public void setSummands(long[] summands) {
        this.summands = summands;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Long[] getFactors() {
        return factors;
    }

    public void setFactors(Long[] factors) {
        this.factors = factors;
    }

    @Override
    public String toString() {
        if (summands.length == 0) return "empty Element";
        String summandsString = "" + summands[0];
        for(int i = 1; i < summands.length; i++)
            summandsString = summandsString.concat(" + " +summands[i]);
        String factorsString = " = " + factors[0];
        for(int i = 1; i < factors.length; i++)
            factorsString = factorsString.concat(" * " + factors[i]);
        return "element: " + summandsString + " = " + number + factorsString;
    }
}
