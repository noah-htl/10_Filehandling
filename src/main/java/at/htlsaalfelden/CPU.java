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

    @Override
    public final boolean equals(Object object) {
        if (!( object instanceof CPU cpu )) return false;

        return id.equals(cpu.id) && speed.equals(cpu.speed) && ram.equals(cpu.ram) && cache.equals(cpu.cache);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + speed.hashCode();
        result = 31 * result + ram.hashCode();
        result = 31 * result + cache.hashCode();
        return result;
    }
}
