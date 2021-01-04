import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.Iterator;
import java.util.Optional;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 2345118647876720052L;
    public Set<Seance> seances;

    public Schedule() {
        this.seances = new TreeSet<Seance>();
    }

    public void addSeance(Seance seance) throws FailTimeException {
        Iterator<Seance> iterator = seances.iterator();
        while (iterator.hasNext()) {
            Seance next = iterator.next();
            if (!Time.checkTimeInterval(seance.getStartTime(), seance.getEndTime(), next.getStartTime(),
                    next.getEndTime()))
                throw new FailTimeException();
        }
        seances.add(seance);
        System.out.println("Сеанс успешно добавлен в расписание");
    }

    public Optional<Seance> findSeance(Seance otherSeance) {
        Predicate<Seance> isEqual = seance -> seance.getMovie().getName().equalsIgnoreCase(
                otherSeance.getMovie().getName()) && seance.getStartTime().equals(otherSeance.getStartTime())
                && seance.getEndTime().equals(otherSeance.getEndTime());
        Optional<Seance> seanceFound = seances.stream().filter(isEqual).findFirst();
        return seanceFound;
    }

    public void removeSeance(Movie movie) throws FailTimeException {
        Seance removingSeance = Seance.inputSeance(movie);

        Optional<Seance> removingSeanceFound = findSeance(removingSeance);

        if (removingSeanceFound.isPresent()) {
            seances.remove(removingSeanceFound.get());
            System.out.println("Сеанс удален");
        } else {
            System.out.println("Сеанса не существует");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seances == null) ? 0 : seances.hashCode());
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
        Schedule other = (Schedule) obj;
        if (seances == null) {
            if (other.seances != null)
                return false;
        } else if (!seances.equals(other.seances))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Seance seance : seances) {
            sb.append(seance.toString() + "\n");
        }
        return sb.toString();
    }

}
