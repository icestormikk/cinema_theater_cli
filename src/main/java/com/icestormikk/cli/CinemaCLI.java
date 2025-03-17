package com.icestormikk.cli;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.domain.cinema.Session;
import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.domain.cinema.User;
import com.icestormikk.services.AdminService;
import com.icestormikk.services.UserService;
import com.icestormikk.services.implementations.AdminServiceImpl;
import com.icestormikk.services.implementations.UserServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CinemaCLI {
    private final UserService userService;
    private final AdminService adminService;
    private final Scanner scanner;
    private Cinema cinema;
    private User user;
    private Admin admin;

    public CinemaCLI() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserServiceImpl();
        this.adminService = new AdminServiceImpl();
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
        try {
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
        } catch (Exception e) {
            System.err.println("Error registering user: " + e.getMessage());
        }
    }

    private void loginUser() {
        try {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            user = userService.getUserByName(username);

            if (user != null) {
                System.out.print("Logged in as User: " + user.getUsername());
                userMenu();
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            System.err.println("Error logging in: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        try {
            isAdmin();

            System.out.println("All users:");
            for (User user : userService.getUsers()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println("Error viewing all users: " + e.getMessage());
        }
    }

    private void updateUser() {
        try {
            isAdmin();

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

            userService.updateUserById(user.getId(), firstname, lastName, username);
            System.out.println("User updated: " + user);
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    private void deleteUser() {
        try {
            isAdmin();

            System.out.print("Enter user name to delete: ");
            String name = scanner.nextLine();
            User user = userService.getUserByName(name);
            userService.deleteUserById(user.getId());
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    // ADMIN

    private void registerAdmin() {
        try {
            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            adminService.createAdmin(firstName, lastName, username);

            System.out.println("Successfully registered admin: " + username + " (" + firstName + " " + lastName + ")");
        } catch (Exception e) {
            System.err.println("Error registering admin: " + e.getMessage());
        }
    }

    private void loginAdmin() {
        try {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            admin = adminService.getAdminByName(username);

            System.out.print("Logged in as Admin: " + admin.getUsername());

            adminMenu();
        } catch (Exception e) {
            System.err.println("Error logging admin: " + e.getMessage());
        }
    }

    private void viewAllAdmins() {
        try {
            isAdmin();

            System.out.println("All admins:");
            for (Admin admin : adminService.getAdmins()) {
                System.out.println(admin);
            }
        } catch (Exception e) {
            System.err.println("Error viewing all admins: " + e.getMessage());
        }
    }

    private void updateAdmin() {
        try {
            isAdmin();

            System.out.print("Enter admin name to update: ");
            String name = scanner.nextLine();
            Admin admin = adminService.getAdminByName(name);
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

            adminService.updateAdminById(admin.getId(), firstname, lastName, userName);

            System.out.println("Admin updated: " + admin);
        } catch (Exception e) {
            System.err.println("Error updating admin: " + e.getMessage());
        }
    }

    private void deleteAdmin() {
        try {
            isAdmin();

            System.out.print("Enter admin name to delete: ");
            String name = scanner.nextLine();

            Admin admin = adminService.getAdminByName(name);

            adminService.deleteAdminById(admin.getId());
        } catch (Exception e) {
            System.err.println("Error deleting admin: " + e.getMessage());
        }
    }

    // CINEMA

    private void viewAllCinemas() {
        try {
            isAdmin();

            System.out.println("All cinemas:");
            for (Cinema cinema: admin.getCinemas()) {
                System.out.println(cinema);
            }
        } catch (Exception e) {
            System.err.println("Error viewing cinemas: " + e.getMessage());
        }
    }

    private void createCinema() {
        try {
            isAdmin();

            System.out.print("Enter cinema name: ");
            String cinemaName = scanner.nextLine();
            System.out.print("Enter cinema address: ");
            String cinemaAddress = scanner.nextLine();

            Cinema newCinema = new Cinema(admin.getCinemas().size() + 1, cinemaName, cinemaAddress);
            admin.getCinemas().add(newCinema);
            cinema = newCinema;

            System.out.println("Cinema created: " + cinema);
        } catch (Exception e) {
            System.err.println("Error creating cinema: " + e.getMessage());
        }
    }

    private void updateCinema() {
        try {
            isAdmin();

            Set<Cinema> cinemas = admin.getCinemas();

            System.out.print("Enter cinema title to update: ");
            String title = scanner.nextLine();

            Cinema cinema = null;
            for (Cinema c: cinemas) {
                if(c.getTitle().equalsIgnoreCase(title)) {
                    cinema = c;
                }
            }

            if (cinema == null) {
                System.out.println("Cinema not found!");
                return;
            }

            System.out.print("Enter new cinema title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new address: ");
            String address = scanner.nextLine();

            cinemas.remove(cinema);
            cinema.setAddress(address).setTitle(newTitle);
            cinemas.add(cinema);

            System.out.println("Cinema updated: " + cinema);
        } catch (Exception e) {
            System.err.println("Error updating cinema: " + e.getMessage());
        }
    }

    private void deleteCinema() {
        try {
            isAdmin();

            System.out.print("Enter cinema name to delete: ");
            String name = scanner.nextLine();

            Cinema cinema = null;
            for (Cinema c: admin.getCinemas()) {
                if(c.getTitle().equalsIgnoreCase(name)) {
                    cinema = c;
                }
            }

            if (cinema != null) {
                cinema.getSessions().forEach((session) ->
                    userService.getUsers().forEach((user) ->
                        user.getTickets().removeIf((ticket) -> ticket.getSession().equals(session))
                    )
                );

                admin.getCinemas().remove(cinema);
                System.out.println("Cinema deleted: " + cinema);
            } else {
                System.err.println("Cinema not found!");
            }
        } catch (Exception e) {
            System.err.println("Error deleting cinema: " + e.getMessage());
        }
    }

    // HALL

    private void addHall() {
        try {
            isCinemaSelected();

            System.out.print("Enter hall number: ");
            int hallNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter number of seats: ");
            int seats = Integer.parseInt(scanner.nextLine());

            Hall hall = new Hall(cinema.getHalls().size() + 1, hallNumber, seats);
            cinema.addHall(hall);

            System.out.println("Hall added: " + hall);
        } catch (Exception e) {
            System.err.println("Error creating hall: " + e.getMessage());
        }
    }

    private void viewAllHalls() {
        try {
            isCinemaSelected();

            System.out.println("All halls:");
            for(Hall hall: cinema.getHalls()) {
                System.out.println(hall);
            }
        } catch (Exception e) {
            System.err.println("Error viewing all halls: " + e.getMessage());
        }
    }

    private void updateHall() {
        try {
            isCinemaSelected();

            System.out.print("Enter hall number to update: ");
            int hallNumber = Integer.parseInt(scanner.nextLine());

            Hall hall = null;
            for (Hall h: cinema.getHalls()) {
                if(h.getHallNumber() == hallNumber) {
                    hall = h;
                }
            }
            if (hall == null) {
                System.err.println("Hall not found!");
                return;
            }

            System.out.print("Enter new hall number: ");
            int newHallNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new number of seats: ");
            int seats = Integer.parseInt(scanner.nextLine());

            hall.setHallNumber(newHallNumber).setSeats(seats);

            System.out.println("Hall updated: " + hall);
        } catch (Exception e) {
            System.err.println("Error updating hall: " + e.getMessage());
        }
    }

    private void deleteHall() {
        try {
            isCinemaSelected();

            System.out.print("Enter hall number to delete: ");
            int hallNumber = Integer.parseInt(scanner.nextLine());

            Hall hall = null;
            for(Hall h: cinema.getHalls()) {
                if(h.getHallNumber() == hallNumber) {
                    hall = h;
                }
            }

            if (hall == null)
                throw new Exception("Hall not found!");

            List<Session> sessionsToRemove = new ArrayList<>();
            for (Session session : cinema.getSessions()) {
                if(session.getHall().equals(hall)) {
                    sessionsToRemove.add(session);
                }
            }
            for (Session session : sessionsToRemove) {
                for (User user : userService.getUsers()) {
                    Set<Ticket> tickets = user.getTickets();
                    tickets.removeIf(ticket -> ticket.getSession().equals(session));
                }
                cinema.getSessions().remove(session);
            }

            cinema.getHalls().remove(hall);
            System.out.println("Hall deleted: " + hall);
        } catch (Exception e) {
            System.err.println("Error deleting hall: " + e.getMessage());
        }
    }

    // MOVIE

    private void viewAllMovies() {
        try {
            isCinemaSelected();

            System.out.println("All movies:");
            for (Movie m: cinema.getMovies()) {
                System.out.println(m);
            }
        } catch (Exception e) {
            System.err.println("Error viewing all movies: " + e.getMessage());
        }
    }

    private void addMovie() {
        try {
            isCinemaSelected();

            System.out.print("Enter movie title: ");
            String title = scanner.nextLine();
            System.out.print("Enter movie duration (minutes): ");
            int duration = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter movie genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter movie rating: ");
            double rating = Double.parseDouble(scanner.nextLine());

            Movie movie = new Movie(cinema.getMovies().size() + 1, title, genre, Duration.of(duration, ChronoUnit.MINUTES), rating);
            cinema.addMovie(movie);

            System.out.println("Movie added: " + movie);
        } catch (Exception e) {
            System.err.println("Error adding movie: " + e.getMessage());
        }
    }

    private void searchMovie() {
        try {
            isCinemaSelected();

            System.out.print("Enter movie title to search: ");
            String title = scanner.nextLine();

            Movie movie = cinema.getMovieByTitle(title);

            System.out.print("Successfully found a movie: " + movie);
        } catch (Exception e) {
            System.err.println("Error searching movie: " + e.getMessage());
        }
    }

    private void editMovie() {
        try {
            isAdmin();
            isCinemaSelected();

            System.out.print("Enter movie title to edit: ");
            String title = scanner.nextLine();

            Movie movie = cinema.getMovieByTitle(title);

            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new duration (minutes): ");
            int duration = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter new rating: ");
            double rating = Double.parseDouble(scanner.nextLine());

            movie.setTitle(newTitle).setDurationInMin(Duration.of(duration, ChronoUnit.MINUTES)).setGenre(genre).setRating(rating);

            System.out.println("Movie updated: " + movie);
        } catch (Exception e) {
            System.err.println("Error editing movie: " + e.getMessage());
        }
    }

    private void deleteMovie() {
        try {
            isCinemaSelected();

            System.out.print("Enter movie title to delete: ");
            String title = scanner.nextLine();

            Movie movie = null;
            for (Movie m: cinema.getMovies()) {
                if (m.getTitle().equalsIgnoreCase(title)) {
                    movie = m;
                }
            }

            if(movie == null)
                throw new Exception("Movie not found!");

            cinema.getMovies().remove(movie);
            System.out.println("Movie deleted: " + movie);
        } catch (Exception e) {
            System.err.println("Error deleting movie: " + e.getMessage());
        }
    }

    // SESSION

    private void viewAllSessions() {
        try {
            isCinemaSelected();

            System.out.println("All sessions for cinema " + cinema.getTitle() + ": ");
            for (Session s: cinema.getSessions()) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.err.println("Error viewing all sessions: " + e.getMessage());
        }
    }

    private void addSession() {
        try {
            isCinemaSelected();

            System.out.print("Enter movie title: ");
            String title = scanner.nextLine();

            Movie movie = null;
            for (Movie m : cinema.getMovies()) {
                if (m.getTitle().equalsIgnoreCase(title)) {
                    movie = m;
                }
            }
            if (movie == null)
                throw new Exception("Movie not found!");

            System.out.print("Enter hall number: ");
            int hallNumber = Integer.parseInt(scanner.nextLine());

            Hall hall = null;
            for (Hall h : cinema.getHalls()) {
                if (h.getHallNumber() == hallNumber) {
                    hall = h;
                }
            }
            if (hall == null)
                throw new Exception("Hall not found!");

            System.out.print("Enter start time (format: 2007-12-03T10:15:30): ");
            String startTime = scanner.nextLine();
            System.out.print("Enter end time (format: 2007-12-03T11:15:30): ");
            String endTime = scanner.nextLine();

            Session session = new Session(cinema.getSessions().size() + 1, movie, hall, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
            cinema.addSession(session);

            System.out.println("Session added: " + session);
        } catch (Exception e) {
            System.err.println("Error adding session: " + e.getMessage());
        }
    }

    private void updateSession() {
        try {
            isCinemaSelected();

            System.out.print("Enter session movie title to update: ");
            String title = scanner.nextLine();

            Session session = cinema.getSessions().stream()
                    .filter(s -> s.getMovie().getTitle().equals(title))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Session not found!"));

            System.out.print("Enter new start time: ");
            String startTime = scanner.nextLine();
            System.out.print("Enter new end time: ");
            String endTime = scanner.nextLine();

            session.setStartTime(LocalDateTime.parse(startTime)).setEndTime(LocalDateTime.parse(endTime));

            System.out.println("Session updated: " + session);
        } catch (Exception e) {
            System.err.println("Error updating session: " + e.getMessage());
        }
    }

    private void deleteSession() {
        try {
            isAdmin();

            System.out.print("Enter session movie title to delete: ");
            String title = scanner.nextLine();

            Session session = cinema.getSessions().stream()
                    .filter(s -> s.getMovie().getTitle().equals(title))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Session not found!"));

            for (User user : userService.getUsers()) {
                var tickets = user.getTickets();
                tickets.removeIf((ticket) -> ticket.getSession().equals(session));
            }

            cinema.getSessions().remove(session);
            System.out.println("Session deleted: " + session);
        } catch (Exception e) {
            System.err.println("Error deleting session: " + e.getMessage());
        }
    }

    private void viewAvailableSeats() {
        isCinemaSelected();

        try {
            System.out.print("Enter session movie title: ");
            String title = scanner.nextLine();

            List<Session> sessions = getSessionsByMovieTitle(title);

            for (Session session : sessions) {
                System.out.println("Available seats for session: " + session);
                for (int seat = 1; seat <= session.getHall().getSeats(); seat++) {
                    if (!session.getBookedSeats().contains(seat)) {
                        System.out.print(seat + " ");
                    }
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println("Error viewing available seats: " + e.getMessage());
        }
    }

    // TICKET

    public void viewUserTickets() {
        try {
            isUser();

            System.out.println("Your tickets:");
            for (Ticket ticket : user.getTickets()) {
                System.out.println(ticket);
            }
        } catch (Exception e) {
            System.err.println("Error viewing user tickets: " + e.getMessage());
        }
    }

    private void bookTicket() {
        try {
            isUser();
            isCinemaSelected();

            System.out.print("Enter session movie title: ");
            String title = scanner.nextLine();

            List<Session> sessions = getSessionsByMovieTitle(title);
            for (Session session : sessions) {
                System.out.println(session);
            }
            System.out.print("Enter session id: ");
            int id = Integer.parseInt(scanner.nextLine());

            Session session = sessions
                    .stream()
                    .filter((s) -> s.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Session not found!"));

            System.out.print("Enter seat number: ");
            int seat = Integer.parseInt(scanner.nextLine());

            session.bookSeat(seat);

            Ticket ticket = new Ticket(user.getTickets().size() + 1, session, seat);
            user.bookTicket(ticket);

            System.out.println("Ticket booked successfully: " + ticket);
        } catch (Exception e) {
            System.err.println("Error viewing user tickets: " + e.getMessage());
        }
    }

    private void cancelTicket() {
        try {
            isUser();

            System.out.println("Your tickets:");
            for(Ticket t: user.getTickets()) {
                System.out.println(t);
            }

            System.out.print("Enter the ticket id to cancel: ");
            int ticketId = Integer.parseInt(scanner.nextLine());

            Ticket ticket = user.getTickets()
                    .stream()
                    .filter((t) -> t.getId() == ticketId)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Ticket not found!"));

            ticket.getSession().cancelSeat(ticket.getSeat());
            user.cancelTicket(ticket);

            System.out.println("Ticket canceled: " + ticket);
        } catch (Exception e) {
            System.err.println("Error viewing user tickets: " + e.getMessage());
        }
    }

    private void purchaseTicket() {
        try {
            isUser();

            System.out.println("Your booked tickets:");
            for (Ticket t: user.getTickets()) {
                System.out.println(t);
            }

            System.out.print("Enter the ticket id to purchase: ");
            int ticketId = Integer.parseInt(scanner.nextLine());

            Ticket ticket = user.getTickets()
                    .stream()
                    .filter((t) -> t.getId() == ticketId)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Ticket not found!"));

            ticket.purchase();
            System.out.println("Ticket purchased: " + ticket);
        } catch (Exception e) {
            System.err.println("Error viewing user tickets: " + e.getMessage());
        }
    }

    // OTHER

    private void viewStatistics() {
        try {
            isAdmin();
            isCinemaSelected();

            int totalBookedTickets = 0;
            for (Session s: cinema.getSessions()) {
                int bookedSeats = s.getBookedSeats().size();
                totalBookedTickets += bookedSeats;
            }

            System.out.println("Total booked tickets: " + totalBookedTickets);
        } catch (Exception e) {
            System.err.println("Error viewing statistics: " + e.getMessage());
        }
    }

    private List<Session> getSessionsByMovieTitle(String title) {
        List<Session> sessions = new LinkedList<>();
        for (Session s: cinema.getSessions()) {
            if(s.getMovie().getTitle().equalsIgnoreCase(title)) {
                sessions.add(s);
            }
        }

        return sessions;
    }

    private void isUser() {
        if(user == null) {
            throw new RuntimeException("You must be logged in as User!");
        }
    }

    private void isAdmin() {
        if(admin == null) {
            throw new RuntimeException("You must be logged in as User!");
        }
    }

    private void isCinemaSelected() {
        if(cinema == null) {
            throw new RuntimeException("Select cinema first!");
        }
    }
}
