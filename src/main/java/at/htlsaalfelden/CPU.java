package at.htlsaalfelden;

public class CPU {
    private Integer id;
    private Double speed;
    private Double ram;
    private Double cache;

    private CPU() {}

    public CPU(int id, double speed, double ram, double cache) {
        this.id = id;
        this.speed = speed;
        this.ram = ram;
        this.cache = cache;
    }

    public int getId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    public double getRam() {
        return ram;
    }

    public double getCache() {
        return cache;
    }

    @Override
    public String toString() {
        return "CPU:" + id + "\t\t" + speed + "\t\t" + ram + "\t\t" + cache;
    }
}
