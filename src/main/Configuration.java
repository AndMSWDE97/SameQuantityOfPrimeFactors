package main;

public enum Configuration {
    instance;
    public final int maximum = 1_000_000_000 * 2;
    public int threads = Runtime.getRuntime().availableProcessors() * 2;
}