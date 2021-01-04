import java.io.Serializable;
import java.util.Scanner;

public class Time implements Serializable, Comparable<Time> {

    private static final long serialVersionUID = -5638271945196164126L;
    public int hour;
    public int min;

    public Time() {

    }

    public Time(int hour, int min) throws FailTimeException {
        if ((hour >= 0 && hour < 24) && (min >= 0 && min < 60)) {
            this.hour = hour;
            this.min = min;
        } else {
            throw new FailTimeException();
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hour;
        result = prime * result + min;
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
        Time other = (Time) obj;
        if (hour != other.hour)
            return false;
        if (min != other.min)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%2d", hour) + ":" + String.format("%02d", min);
    }

    @Override
    public int compareTo(Time anotherTime) {
        return timeToMinutes(this).compareTo(timeToMinutes(anotherTime));
    }

    public static Integer timeToMinutes(Time time) {
        return time.getHour() * 60 + time.getMin();
    }

    public static Time sumTime(Time time1, Time time2) throws FailTimeException {
        int hour = time1.getHour() + time2.getHour();
        int min = time1.getMin() + time2.getMin();

        if (min > 60) {
            hour += 1;
            min = min - 60;
        }

        if (hour >= 24) {
            hour = hour - 24;
        }

        return new Time(hour, min);
    }

    public static Time inputTime() throws FailTimeException {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Ввод времени (часы 0...23):");
        int hour = sc.nextInt();
        System.out.println("Ввод времени ( минуты 0...59):");
        int min = sc.nextInt();
        return new Time(hour, min);
    }

    public static boolean compareTime(Time time1, Time time2) {
        if (time1.hour > time2.hour)
            return true;
        else if (time1.hour >= time2.hour && time1.min > time2.min)
            return true;
        return false;
    }

    public static boolean checkTimeInterval(Time timeOneBegin, Time timeOneEnd, Time timeTwoBegin, Time timeTwoEnd) {
        if (compareTime(timeOneBegin, timeTwoBegin) && compareTime(timeOneBegin, timeTwoEnd)
                && compareTime(timeOneEnd, timeTwoBegin) && compareTime(timeOneEnd, timeTwoEnd))
            return true;
        if (!compareTime(timeOneBegin, timeTwoBegin) && !compareTime(timeOneBegin, timeTwoEnd)
                && !compareTime(timeOneEnd, timeTwoBegin) && !compareTime(timeOneEnd, timeTwoEnd))
            return true;
        return false;
    }

}

@SuppressWarnings("serial")
class FailTimeException extends Exception {
    static final String string = "Время должно быть в пределах: 0 ... 24 для часов, 0 ... 60 для минут";

    public FailTimeException() {
        super(string);
    }
}