package com.icestormikk.cli;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.domain.cinema.Session;
import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.domain.cinema.User;
import com.icestormikk.services.implementations.AdminService;
import com.icestormikk.services.implementations.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaCLI {
    private final UserService userService;
    private final AdminService adminService;
    private final Scanner scanner;
    private Cinema cinema;
    private User user;
    private Admin admin;

    public CinemaCLI() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
        this.adminService = new AdminService();
    }

    public void start() {
        System.out.println("Welcome to the Cinema CLI");
        while (true) {
            showMainMenu();
            String command = scanner.nextLine();
            handleMainMenuCommand(command);
        }
    }

    private void showMainMenu() {
        System.out.println("\n1. Register User");
        System.out.println("2. Register Admin");
        System.out.println("3. Login as User");
        System.out.println("4. Login as Admin");
        System.out.println("5. Exit");
    }

    private void userMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View All Movies");
            System.out.println("2. Search Movie");
            System.out.println("3. View All Sessions");
            System.out.println("4. View Available Seats");
            System.out.println("5. Book Ticket");
            System.out.println("6. View My Tickets");
            System.out.println("7. Cancel Ticket");
            System.out.println("8. Purchase Ticket");
            System.out.println("9. Logout");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    viewAllMovies();
                    break;
                case "2":
                    searchMovie();
                    break;
                case "3":
                    viewAllSessions();
                    break;
                case "4":
                    viewAvailableSeats();
                    break;
                case "5":
                    bookTicket();
                    break;
                case "6":
                    viewUserTickets();
                    break;
                case "7":
                    cancelTicket();
                    break;
                case "8":
                    purchaseTicket();
                    break;
                case "9":
                    user = null;
                    return;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Create Cinema");
            System.out.println("2. View All Cinemas");
            System.out.println("3. Update Cinema");
            System.out.println("4. Delete Cinema");
            System.out.println("5. Add Hall");
            System.out.println("6. View All Halls");
            System.out.println("7. Update Hall");
            System.out.println("8. Delete Hall");
            System.out.println("9. Add Movie");
            System.out.println("10. View All Movies");
            System.out.println("11. Update Movie");
            System.out.println("12. Delete Movie");
            System.out.println("13. Add Session");
            System.out.println("14. View All Sessions");
            System.out.println("15. Update Session");
            System.out.println("16. Delete Session");
            System.out.println("17. View All Users");
            System.out.println("18. Update User");
            System.out.println("19. Delete User");
            System.out.println("20. View All Admins");
            System.out.println("21. Update Admin");
            System.out.println("22. Delete Admin");
            System.out.println("23. View Statistics");
            System.out.println("24. Logout");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    createCinema();
                    break;
                case "2":
                    viewAllCinemas();
                    break;
                case "3":
                    updateCinema();
                    break;
                case "4":
                    deleteCinema();
                    break;
                case "5":
                    addHall();
                    break;
                case "6":
                    viewAllHalls();
                    break;
                case "7":
                    updateHall();
                    break;
                case "8":
                    deleteHall();
                    break;
                case "9":
                    addMovie();
                    break;
                case "10":
                    viewAllMovies();
                    break;
                case "11":
                    editMovie();
                    break;
                case "12":
                    deleteMovie();
                    break;
                case "13":
                    addSession();
                    break;
                case "14":
                    viewAllSessions();
                    break;
                case "15":
                    updateSession();
                    break;
                case "16":
                    deleteSession();
                    break;
                case "17":
                    viewAllUsers();
                    break;
                case "18":
                    updateUser();
                    break;
                case "19":
                    deleteUser();
                    break;
                case "20":
                    viewAllAdmins();
                    break;
                case "21":
                    updateAdmin();
                    break;
                case "22":
                    deleteAdmin();
                    break;
                case "23":
                    viewStatistics();
                    break;
                case "24":
                    admin = null;
                    return;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private void handleMainMenuCommand(String command) {
        switch (command) {
            case "1":
                registerUser();
                break;
            case "2":
                registerAdmin();
                break;
            case "3":
                loginUser();
                break;
            case "4":
                loginAdmin();
                break;
            case "5":
                System.exit(0);
            default:
                System.out.println("Invalid command!");
        }
    }

    // USERS

    private void registerUser() {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        User user = userService.createUser(firstName, lastName, username);

        if(user != null) {
            System.out.println("Successfully registered user: " + username + " (" + firstName + " " + lastName + ")");
        }
    }

    private void loginUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        user = userService.getUserByName(username);
        if (user != null) {
            System.out.print("Logged in as User: " + user.getUsername());
            userMenu();
        } else {
            System.out.println("User not found!");
        }
    }

    private void viewAllUsers() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.println("All users:");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
    }

    private void updateUser() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.print("Enter user name to update: ");
        String name = scanner.nextLine();
        User user = userService.getUserByName(name);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        user.setFirstName(firstname)
            .setLastName(lastName)
            .setUsername(username);

        System.out.println("User updated: " + user);
    }

    private void deleteUser() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.print("Enter user name to delete: ");
        String name = scanner.nextLine();
        User user = userService.getUserByName(name);

        if (user != null) {
            userService.getAllUsers().remove(user);
            System.out.println("User deleted: " + user);
        } else {
            System.out.println("User not found!");
        }
    }

    // ADMIN

    private void registerAdmin() {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        adminService.createAdmin(firstName, lastName, username);

        System.out.println("Successfully registered admin: " + username + " (" + firstName + " " + lastName + ")");
    }

    private void loginAdmin() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            System.out.print("Logged in as Admin: " + admin.getUsername());
            adminMenu();
        } else {
            System.out.println("Admin not found!");
        }
    }

    private void viewAllAdmins() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.println("All admins:");
        for (Admin admin : adminService.getAllAdmins()) {
            System.out.println(admin);
        }
    }

    private void updateAdmin() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.print("Enter admin name to update: ");
        String name = scanner.nextLine();
        Admin admin = adminService.getAdminByUsername(name);
        if (admin == null) {
            System.out.println("Admin not found!");
            return;
        }

        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new user name: ");
        String userName = scanner.nextLine();

        admin.setFirstName(firstname)
            .setLastName(lastName)
            .setUsername(userName);

        System.out.println("Admin updated: " + admin);
    }

    private void deleteAdmin() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.print("Enter admin name to delete: ");
        String name = scanner.nextLine();
        Admin admin = adminService.getAdminByUsername(name);
        if (admin != null) {
            adminService.getAllAdmins().remove(admin);
            System.out.println("Admin deleted: " + admin);
        } else {
            System.out.println("Admin not found!");
        }
    }

    // CINEMA

    private void viewAllCinemas() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.println("All cinemas:");
        for (Cinema cinema: admin.getCinemas()) {
            System.out.println(cinema);
        }
    }

    private void createCinema() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }

        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        System.out.print("Enter cinema address: ");
        String cinemaAddress = scanner.nextLine();
        cinema = new Cinema(cinemaName, cinemaAddress);
        admin.addCinema(cinema);

        System.out.println("Cinema created: " + cinema);
    }

    private void updateCinema() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.print("Enter cinema name to update: ");
        String name = scanner.nextLine();

        Cinema cinema = null;
        for (Cinema c: admin.getCinemas()) {
            if(c.getTitle().equalsIgnoreCase(name)) {
                cinema = c;
            }
        }
        if (cinema == null) {
            System.out.println("Cinema not found!");
            return;
        }

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();
        cinema.setTitle(name)
              .setAddress(address);
        System.out.println("Cinema updated: " + cinema);
    }

    private void deleteCinema() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }
        System.out.print("Enter cinema name to delete: ");
        String name = scanner.nextLine();

        Cinema cinema = null;
        for (Cinema c: admin.getCinemas()) {
            if(c.getTitle().equalsIgnoreCase(name)) {
                cinema = c;
            }
        }
        if (cinema != null) {
            cinema.getSessions().forEach(session ->
                userService.getAllUsers().forEach(user ->
                    user.getTickets().removeIf(ticket -> ticket.getSession().equals(session))
                )
            );

            admin.getCinemas().remove(cinema);
            System.out.println("Cinema deleted: " + cinema);
        } else {
            System.out.println("Cinema not found!");
        }
    }

    // HALL

    private void addHall() {
        if (cinema == null) {
            System.out.println("Create a cinema first!");
            return;
        }
        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());
        Hall hall = new Hall(hallNumber, seats);
        cinema.addHall(hall);
        System.out.println("Hall added: " + hall);
    }

    private void viewAllHalls() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.println("All halls:");

        for(Hall hall: cinema.getHalls()) {
            System.out.println(hall);
        }
    }

    private void updateHall() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.print("Enter hall number to update: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = null;
        for (Hall h: cinema.getHalls()) {
            if(h.getHallNumber() == hallNumber) {
                hall = h;
            }
        }
        if (hall == null) {
            System.out.println("Hall not found!");
            return;
        }

        System.out.print("Enter new number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());

        hall.setHallNumber(hallNumber)
            .setSeats(seats);

        System.out.println("Hall updated: " + hall);
    }

    private void deleteHall() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.print("Enter hall number to delete: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = null;
        for(Hall h: cinema.getHalls()) {
            if(h.getHallNumber() == hallNumber) {
                hall = h;
            }
        }

        if (hall != null) {
            List<Session> sessionsToRemove = new ArrayList<>();
            for (Session session : cinema.getSessions()) {
                if(session.getHall().equals(hall)) {
                    sessionsToRemove.add(session);
                }
            }
            for (Session session : sessionsToRemove) {
                for (User user : userService.getAllUsers()) {
                    List<Ticket> tickets = user.getTickets();
                    tickets.removeIf(ticket -> ticket.getSession().equals(session));
                }
                cinema.getSessions().remove(session);
            }

            cinema.getHalls().remove(hall);
            System.out.println("Hall deleted: " + hall);
        } else {
            System.out.println("Hall not found!");
        }
    }

    // MOVIE

    private void viewAllMovies() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.println("All movies:");
        for (Movie m: cinema.getMovies()) {
            System.out.println(m);
        }
    }

    private void addMovie() {
        if (cinema == null) {
            System.out.println("Create a cinema first!");
            return;
        }
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter movie duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter movie genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter movie rating: ");
        double rating = Double.parseDouble(scanner.nextLine());
        Movie movie = new Movie(title, genre, duration, rating);
        cinema.addMovie(movie);
        System.out.println("Movie added: " + movie);
    }

    private void searchMovie() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }

        System.out.print("Enter movie title to search: ");
        String title = scanner.nextLine();

        Movie movie = null;
        for (Movie m: cinema.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                movie = m;
            }
        }
        if (movie != null) {
            System.out.print("Successfully found a movie: " + movie);
        } else {
            System.out.println("Movie not found!");
        }
    }

    private void editMovie() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }

        System.out.print("Enter movie title to edit: ");
        String title = scanner.nextLine();
        Movie movie = null;
        for (Movie m: cinema.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                movie = m;
            }
        }
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        System.out.print("Enter new duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new rating: ");
        double rating = Double.parseDouble(scanner.nextLine());

        movie = new Movie(title, genre, duration, rating);
        System.out.println("Movie updated: " + movie);
    }

    private void deleteMovie() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.print("Enter movie title to delete: ");
        String title = scanner.nextLine();

        Movie movie = null;
        for (Movie m: cinema.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                movie = m;
            }
        }
        if (movie != null) {
            cinema.getMovies().remove(movie);
            System.out.println("Movie deleted: " + movie);
        } else {
            System.out.println("Movie not found!");
        }
    }

    // SESSION

    private void viewAllSessions() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.println("All sessions for cinema " + cinema.getTitle() + ": ");
        for (Session s: cinema.getSessions()) {
            System.out.println(s);
        }
    }

    private void addSession() {
        if (cinema == null) {
            System.out.println("Create a cinema first!");
            return;
        }

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        Movie movie = null;
        for (Movie m : cinema.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                movie = m;
            }
        }
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = null;
        for (Hall h : cinema.getHalls()) {
            if (h.getHallNumber() == hallNumber) {
                hall = h;
            }
        }
        if (hall == null) {
            System.out.println("Hall not found!");
            return;
        }

        System.out.print("Enter start time: ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time: ");
        String endTime = scanner.nextLine();

        Session session = new Session(movie, hall, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        cinema.addSession(session);
        System.out.println("Session added: " + session);
    }

    private void updateSession() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.print("Enter session movie title to update: ");
        String title = scanner.nextLine();
        Session session = cinema.getSessions().stream()
                .filter(s -> s.getMovie().getTitle().equals(title))
                .findFirst()
                .orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }
        System.out.print("Enter new start time: ");
        String startTime = scanner.nextLine();
        System.out.print("Enter new end time: ");
        String endTime = scanner.nextLine();

        session.setStartTime(LocalDateTime.parse(startTime))
               .setEndTime(LocalDateTime.parse(endTime));

        System.out.println("Session updated: " + session);
    }

    private void deleteSession() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }

        System.out.print("Enter session movie title to delete: ");
        String title = scanner.nextLine();

        Session session = cinema.getSessions().stream()
                .filter(s -> s.getMovie().getTitle().equals(title))
                .findFirst()
                .orElse(null);
        for (Session s: cinema.getSessions()) {
            if(s.getMovie().getTitle().equalsIgnoreCase(title)) {
                session = s;
            }
        }
        if (session != null) {
            for (User user : userService.getAllUsers()) {
                var tickets = user.getTickets();
                for (Ticket ticket : tickets) {
                    if (ticket.getSession().equals(session)) {
                        tickets.remove(ticket);
                    }
                }
            }

            cinema.getSessions().remove(session);
            System.out.println("Session deleted: " + session);
        } else {
            System.out.println("Session not found!");
        }
    }

    private void viewAvailableSeats() {
        if (cinema == null) {
            System.out.println("No cinema available!");
            return;
        }
        System.out.print("Enter session movie title: ");
        String movieTitle = scanner.nextLine();
        Session session = null;
        for (Session s: cinema.getSessions()) {
            if(s.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                session = s;
            }
        }
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }

        System.out.println("Available seats for session: " + session);
        for (int seat = 1; seat <= session.getHall().getSeats(); seat++) {
            if (!session.getBookedSeats().contains(seat)) {
                System.out.print(seat + " ");
            }
        }
        System.out.println();
    }

    // TICKET

    public void viewUserTickets() {
        if (user == null) {
            System.out.println("You must be logged in as User!");
            return;
        }
        System.out.println("Your tickets:");
        for (Ticket ticket : user.getTickets()) {
            System.out.println(ticket);
        }
    }

    private void bookTicket() {
        if (user == null) {
            System.out.println("You must be logged in as User!");
            return;
        }
        if (cinema == null) {
            System.out.println("No cinema found!");
            return;
        }

        System.out.print("Enter session movie title: ");
        String title = scanner.nextLine();

        Session session = null;
        for (Session s: cinema.getSessions()) {
            if(s.getMovie().getTitle().equalsIgnoreCase(title)) {
                session = s;
            }
        }
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }

        System.out.print("Enter seat number: ");
        int seat = Integer.parseInt(scanner.nextLine());
        if(session.bookSeat(seat)) {
            Ticket ticket = new Ticket(session, seat);
            user.bookTicket(ticket);
            System.out.println("Ticket booked successfully: " + ticket);
        } else {
            System.out.println("Seat booking failed!");
        }
    }

    private void cancelTicket() {
        if (user == null) {
            System.out.println("You must be logged in as User!");
            return;
        }
        System.out.println("Your tickets:");
        for(Ticket t: user.getTickets()) {
            System.out.println(t);
        }

        System.out.print("Enter the ticket number to cancel: ");
        int ticketNumber = Integer.parseInt(scanner.nextLine());
        if (ticketNumber >= 0 && ticketNumber < user.getTickets().size()) {
            Ticket ticket = user.getTickets().get(ticketNumber);
            ticket.getSession().cancelSeat(ticket.getSeat());
            user.cancelTicket(ticket);
            System.out.println("Ticket canceled: " + ticket);
        } else {
            System.out.println("Invalid ticket number!");
        }
    }

    private void purchaseTicket() {
        if (user == null) {
            System.out.println("You must be logged in as User!");
            return;
        }
        System.out.println("Your booked tickets:");
        for (Ticket t: user.getTickets()) {
            System.out.println(t);
        }

        System.out.print("Enter the ticket number to purchase: ");
        int ticketNumber = Integer.parseInt(scanner.nextLine());
        if (ticketNumber >= 0 && ticketNumber < user.getTickets().size()) {
            Ticket ticket = user.getTickets().get(ticketNumber);
            ticket.purchase();
            System.out.println("Ticket purchased: " + ticket);
        } else {
            System.out.println("Invalid ticket number!");
        }
    }

    // OTHER

    private void viewStatistics() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }

        int totalBookedTickets = 0;
        for (Session s: cinema.getSessions()) {
            int bookedSeats = s.getBookedSeats().size();
            totalBookedTickets += bookedSeats;
        }

        System.out.println("Total booked tickets: " + totalBookedTickets);
    }
}
