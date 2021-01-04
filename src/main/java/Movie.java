import java.io.Serializable;
import java.util.Scanner;

public class Movie implements Serializable {

    private static final long serialVersionUID = -5447420524604321921L;
    public String name;
    public Time duration;

    public Movie(String name, Time duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @SuppressWarnings("resource")
    public static Movie inputMovie() throws FailTimeException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название фильма:");
        String name = sc.nextLine();
        if (name.equals("")) {
            System.err.println("Неверное название фильма");
            name = "Фильм без названия";
        }
        System.out.println("Продолжительность фильма:");
        Time duration = Time.inputTime();
        return new Movie(name, duration);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Фильм - " + "\"" + name + "\"" + ", длительность сеанса - " + duration.toString();
    }

}