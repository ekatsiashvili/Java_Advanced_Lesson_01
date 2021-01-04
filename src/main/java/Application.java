import java.io.File;
import java.util.Scanner;

public class Application {

    public static int menu() {
        System.out.println("Введите 10 для создания кинотеатра");
        System.out.println("Введите 11 - сохранить кинотеатр в файл");
        System.out.println("Введите 12 - добавление кинотеатра из файла");
        System.out.println("Введите 13 - добавление фильма");
        System.out.println("Введите 14 - удаление фильма");
        System.out.println("Введите 15 - вывод всех фильмов");
        System.out.println("Введите 16 - добавление сеанса");
        System.out.println("Введите 17 - удаление сеанса");
        System.out.println("Введите 18 - вывод списка дневных сеансов");
        System.out.println("Введите 19 для ввода расписания");
        System.out.println("Введите 0 для выхода");

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ваш выбор: ");
        int menuChoise = scanner.nextInt();

        return menuChoise;
    }

    public static void main(String[] args) throws Exception {
        Cinema cinema = null;
        Days days;
        Schedule schedule;

        while (true) {
            switch (menu()) {

                case 10: {
                    cinema = Cinema.inputCinema();
                    break;
                }

                case 11: {
                    SerializeMethods.serialize(cinema, new File("Cinema.txt"));
                    System.out.println("Кинотеатр \"" + cinema.getName() + "\" сохранено в файл\n");
                    break;
                }

                case 12: {
                    cinema = (Cinema) SerializeMethods.deserialize(new File("Cinema.txt"));
                    System.out.println("Кінотеатр \"" + cinema.getName() + "\" загружен из файла\n");
                    break;
                }

                case 13: {
                    cinema.addMovie();
                    break;
                }

                case 14: {
                    Movie movie = Movie.inputMovie();
                    cinema.removeMovie(movie);
                    break;
                }

                case 15: {
                    cinema.printAllMovie();
                    break;
                }

                case 16: {
                    System.out.println("Ввведите день сеанса:");
                    days = Days.inputDay();
                    Movie movie = Movie.inputMovie();
                    cinema.addSeance(movie, days);
                    break;
                }
                case 17: {
                    System.out.println("Введите день удаляемого сеанса");
                    days = Days.inputDay();
                    for (Days day1 : Days.values()) {
                        if (day1.name().equals(days.name().toUpperCase())) {
                            cinema.removeSeance(day1);
                        }
                    }
                    break;
                }

                case 18: {
                    Days day = Days.inputDay();
                    System.out.println("Расписание сеансов на " + day.name());
                    schedule = cinema.getScheduleMap().get(day);
                    cinema.getSchedule(schedule);
                    break;
                }

                case 19: {
                    for (Days day1 : Days.values()) {
                        System.out.println();
                        System.out.println(day1.name());
                        schedule = cinema.getScheduleMap().get(day1);
                        cinema.getSchedule(schedule);
                    }
                    break;
                }

                case 0: {
                    System.out.println("Благодарим за посещение кинотеатра\n");
                    System.exit(0);
                    break;
                }

                default: {
                    System.out.println("Пункт не найден");
                    break;
                }
            }
        }
    }
}
