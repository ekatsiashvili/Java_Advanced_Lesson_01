import java.io.Serializable;

public class Seance implements Comparable<Seance>, Serializable {

    private static final long serialVersionUID = -5979005967952124320L;
    public Movie movie;
    public Time startTime;
    public Time endTime;

    public Seance() {

    }

    public Seance(Movie movie, Time startTime) throws FailTimeException {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = Time.sumTime(startTime, movie.duration);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Seance otherSeance) {
        if (Time.timeToMinutes(this.getStartTime()) > Time.timeToMinutes(otherSeance.getStartTime())) {
            return 1;
        } else if (Time.timeToMinutes(this.getStartTime()) < Time.timeToMinutes(otherSeance.getStartTime())) {
            return -1;
        } else {
            if (this.getMovie().getName().compareTo(otherSeance.getMovie().getName()) > 0) {
                return 1;
            } else if (this.getMovie().getName().compareTo(otherSeance.getMovie().getName()) < 0) {
                return -1;
            } else {
                if (Time.timeToMinutes(this.getEndTime()) > Time.timeToMinutes(otherSeance.getEndTime())) {
                    return 1;
                } else if (Time.timeToMinutes(this.getEndTime()) < Time.timeToMinutes(otherSeance.getEndTime())) {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return startTime.toString() + "-" + endTime.toString() + " - " + movie.toString();
    }

    public static Seance inputSeance(Movie movie) throws FailTimeException {
        System.out.println("Начало сеанса:");
        Time startTime = Time.inputTime();
        return new Seance(movie, startTime);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
        Seance other = (Seance) obj;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (movie == null) {
            if (other.movie != null)
                return false;
        } else if (!movie.equals(other.movie))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }

}