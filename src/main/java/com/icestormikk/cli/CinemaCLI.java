package com.icestormikk.cli;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.domain.cinema.Session;
import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.domain.cinema.TicketStatus;
import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.implementations.AdminRepository;
import com.icestormikk.repositories.implementations.CinemaRepository;
import com.icestormikk.repositories.implementations.HallRepository;
import com.icestormikk.repositories.implementations.MovieRepository;
import com.icestormikk.repositories.implementations.SessionRepository;
import com.icestormikk.repositories.implementations.TicketRepository;
import com.icestormikk.repositories.implementations.UserRepository;
import com.icestormikk.services.implementations.AdminService;
import com.icestormikk.services.implementations.CinemaService;
import com.icestormikk.services.implementations.HallService;
import com.icestormikk.services.implementations.MovieService;
import com.icestormikk.services.implementations.SessionService;
import com.icestormikk.services.implementations.TicketService;
import com.icestormikk.services.implementations.UserService;
import com.icestormikk.utils.StrictHashSet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CinemaCLI {
    private AdminService adminService;
    private CinemaService cinemaService;
    private HallService hallService;
    private MovieService movieService;
    private SessionService sessionService;
    private TicketService ticketService;
    private UserService userService;

    private AdminService adminServiceSnapshot;
    private CinemaService cinemaServiceSnapshot;
    private HallService hallServiceSnapshot;
    private MovieService movieServiceSnapshot;
    private SessionService sessionServiceSnapshot;
    private TicketService ticketServiceSnapshot;
    private UserService userServiceSnapshot;

    private final Scanner scanner;
    private Integer selectedCinemaId;
    private Integer loggedInUserId;

    public CinemaCLI() {
        this.scanner = new Scanner(System.in);
        this.adminService = new AdminService(new AdminRepository());
        this.cinemaService = new CinemaService(new CinemaRepository());
        this.hallService = new HallService(new HallRepository());
        this.movieService = new MovieService(new MovieRepository());
        this.sessionService = new SessionService(new SessionRepository());
        this.ticketService = new TicketService(new TicketRepository());
        this.userService = new UserService(new UserRepository());
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        System.out.println("Welcome to the Cinema CLI");
        while (true) {
            showMainMenu();
            String command = scanner.nextLine();
            try {
                saveSnapshot();
                handleMainMenuCommand(command);
            } catch (Exception e) {
                System.out.println("An error occurred while executing command: " + e.getMessage());
                rollback();
            }
        }
    }

    private void saveSnapshot() {
        adminServiceSnapshot = adminService;
        cinemaServiceSnapshot = cinemaService;
        hallServiceSnapshot = hallService;
        movieServiceSnapshot = movieService;
        sessionServiceSnapshot = sessionService;
        ticketServiceSnapshot = ticketService;
        userServiceSnapshot = userService;
    }

    private void rollback() {
        adminService = adminServiceSnapshot;
        cinemaService = cinemaServiceSnapshot;
        hallService = hallServiceSnapshot;
        movieService = movieServiceSnapshot;
        sessionService = sessionServiceSnapshot;
        ticketService = ticketServiceSnapshot;
        userService = userServiceSnapshot;
        System.out.println("Changes rolled back.");
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
            try {
                saveSnapshot();

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
                        loggedInUserId = null;
                        return;
                    default:
                        System.out.println("Invalid command!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while executing command: " + e.getMessage());
                rollback();
            }
        }
    }

    private void adminMenu() {
        while (true) {
            try {
                saveSnapshot();

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
                        loggedInUserId = null;
                        return;
                    default:
                        System.err.println("Invalid command!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while executing command: " + e.getMessage());
                rollback();
            }
        }
    }

    private void handleMainMenuCommand(String command) throws Exception {
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
                System.err.println("Invalid command!");
        }
    }

    // USERS

    private void registerUser() throws Exception {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        userService = UserService.create(userService, firstName, lastName, username);

        System.out.println("Successfully registered user: " + UserService.getByUsername(userService, username));
    }

    private void loginUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User loggedInUser = UserService.getByUsername(userService, username);

        if(loggedInUser == null)
            throw new RuntimeException("User not found");

        this.loggedInUserId = loggedInUser.getId();

        System.out.print("Logged in as User: " + loggedInUser.getUsername());

        userMenu();
    }

    private void viewAllUsers() {
        isAdmin();

        System.out.println("All users:");
        UserService.getAll(userService).forEach(System.out::println);
    }

    private void updateUser() throws Exception {
        isAdmin();

        System.out.print("Enter user name to update: ");
        String name = scanner.nextLine();

        User user = UserService.getByUsername(userService, name);

        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        userService = UserService.updateById(userService, user.getId(), user.withFirstName(firstname).withLastName(lastName).withUsername(username));

        System.out.println("User updated: " + UserService.getByUsername(userService, username));
    }

    private void deleteUser() throws Exception {
        isAdmin();

        System.out.print("Enter user name to delete: ");
        String name = scanner.nextLine();

        userService = UserService.deleteById(userService, UserService.getByUsername(userService, name).getId());

        System.out.println("User deleted: " + name);
    }

    // ADMIN

    private void registerAdmin() throws Exception {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        adminService = AdminService.create(adminService, firstName, lastName, username);

        System.out.println("Successfully registered admin: " + username + " (" + firstName + " " + lastName + ")");
    }

    private void loginAdmin() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        Admin loggedInAdmin = AdminService.getByUsername(adminService, username);
        loggedInUserId = loggedInAdmin.getId();

        System.out.print("Logged in as Admin: " + loggedInAdmin.getUsername());

        adminMenu();
    }

    private void viewAllAdmins() {
        isAdmin();

        System.out.println("All admins:");
        AdminService.getAll(adminService).forEach(System.out::println);
    }

    private void updateAdmin() throws Exception {
        isAdmin();

        System.out.print("Enter admin name to update: ");
        String name = scanner.nextLine();
        Admin admin = AdminService.getByUsername(adminService, name);

        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new user name: ");
        String userName = scanner.nextLine();

        Admin updatedAdmin = admin.withFirstName(firstname).withLastName(lastName).withUsername(userName);
        adminService = AdminService.updateById(adminService, admin.getId(), updatedAdmin);

        System.out.println("Admin updated: " + updatedAdmin);
    }

    private void deleteAdmin() throws Exception {
        isAdmin();

        System.out.print("Enter admin name to delete: ");
        String name = scanner.nextLine();

        Admin admin = AdminService.getByUsername(adminService, name);

        adminService = AdminService.deleteById(adminService, admin.getId());
        if(Objects.equals(admin.getId(), loggedInUserId))
            loggedInUserId = null;

        System.out.println("Admin deleted: " + name);
    }

    // CINEMA

    private void viewAllCinemas() {
        isAdmin();

        System.out.println("All cinemas:");
        AdminService.getById(adminService, loggedInUserId).getCinemaIds().stream()
            .map((id) -> CinemaService.getById(cinemaService, id))
            .forEach(System.out::println);
    }

    private void createCinema() throws Exception {
        isAdmin();

        System.out.print("Enter cinema title: ");
        String cinemaTitle = scanner.nextLine();
        System.out.print("Enter cinema address: ");
        String cinemaAddress = scanner.nextLine();

        cinemaService = CinemaService.create(cinemaService, cinemaTitle, cinemaAddress);
        Cinema cinema = CinemaService.getByTitle(cinemaService, cinemaTitle);
        selectedCinemaId = cinema.getId();

        Admin admin = AdminService.getById(adminService, loggedInUserId);

        adminService = AdminService.updateById(adminService, loggedInUserId, admin.addCinemaId(selectedCinemaId));

        System.out.println("Cinema created: " + cinema);
    }

    private void updateCinema() throws Exception {
        isAdmin();

        System.out.print("Enter cinema title to update: ");
        String title = scanner.nextLine();

        Cinema cinema = CinemaService.getByTitle(cinemaService, title);

        System.out.print("Enter new cinema title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        cinema = cinema.withAddress(address).withTitle(newTitle);
        cinemaService = CinemaService.updateById(cinemaService, cinema.getId(), cinema);

        System.out.println("Cinema updated: " + cinema);
    }

    private void deleteCinema() throws Exception {
        isAdmin();

        System.out.print("Enter cinema name to delete: ");
        String name = scanner.nextLine();

        Cinema cinema = CinemaService.getByTitle(cinemaService, name);

        cinema.getSessionIds().forEach((sessionId) -> UserService.getAll(userService).forEach((user) -> {
            try {
                deleteUserTicketsBySessionId(sessionId, user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        cinemaService = CinemaService.deleteById(cinemaService, cinema.getId());
        if(cinema.getId() == selectedCinemaId)
            selectedCinemaId = null;

        Admin admin = AdminService.getById(adminService, loggedInUserId);
        adminService = AdminService.updateById(adminService, loggedInUserId, admin.removeCinemaId(cinema.getId()));

        System.out.println("Cinema deleted: " + cinema);
    }

    // HALL

    private void addHall() throws Exception {
        isCinemaSelected();

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());

        hallService = HallService.create(hallService, selectedCinemaId, hallNumber, seats);
        Hall hall = HallService.getByCinemaIdAndNumber(hallService, selectedCinemaId, hallNumber);

        Cinema cinema = CinemaService.getById(cinemaService, selectedCinemaId);
        cinemaService = CinemaService.updateById(cinemaService, selectedCinemaId, cinema.addHallId(hall.getId()));

        System.out.println("Hall added: " + hall);
    }

    private void viewAllHalls() {
        isCinemaSelected();

        System.out.println("All halls:");
        CinemaService.getById(cinemaService, selectedCinemaId).getHallIds().stream()
            .map(hallId -> HallService.getById(hallService, hallId))
            .forEach(System.out::println);
    }

    private void updateHall() throws Exception {
        isCinemaSelected();

        System.out.print("Enter hall number to update: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = HallService.getByCinemaIdAndNumber(hallService, selectedCinemaId, hallNumber);

        System.out.print("Enter new hall number: ");
        int newHallNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());

        hall = hall.withHallNumber(newHallNumber).withSeats(seats);
        hallService = HallService.updateById(hallService, hall.getId(), hall);

        System.out.println("Hall updated: " + hall);
    }

    private void deleteHall() throws Exception {
        isCinemaSelected();

        System.out.print("Enter hall number to delete: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = HallService.getByCinemaIdAndNumber(hallService, selectedCinemaId, hallNumber);

        hall.getSessionIds().forEach((sessionId) -> UserService.getAll(userService).forEach((user) -> {
            try {
                deleteUserTicketsBySessionId(sessionId, user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        cinemaService = CinemaService.updateById(cinemaService, selectedCinemaId, CinemaService.getById(cinemaService, selectedCinemaId).removeHallId(hall.getId()));
        System.out.println("Hall deleted: " + hall);
    }

    // MOVIE

    private void viewAllMovies() {
        isCinemaSelected();

        System.out.println("All movies:");
        CinemaService.getById(cinemaService, selectedCinemaId).getMovieIds().stream()
            .map(movieId -> MovieService.getById(movieService, movieId))
            .forEach(System.out::println);
    }

    private void addMovie() throws Exception {
        isCinemaSelected();

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter movie duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter movie genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter movie rating: ");
        float rating = Float.parseFloat(scanner.nextLine());

        movieService = MovieService.create(movieService, title, genre, Duration.of(duration, ChronoUnit.MINUTES), rating);
        Movie movie = MovieService.getByTitle(movieService, title);

        cinemaService = CinemaService.updateById(cinemaService, selectedCinemaId, CinemaService.getById(cinemaService, selectedCinemaId).addMovieId(movie.getId()));

        System.out.println("Movie added: " + movie);
    }

    private void searchMovie() {
        isCinemaSelected();

        System.out.print("Enter movie title to search: ");
        String title = scanner.nextLine();

        System.out.print("Successfully found a movie: " + MovieService.getByTitle(movieService, title));
    }

    private void editMovie() throws Exception {
        isAdmin();
        isCinemaSelected();

        System.out.print("Enter movie title to edit: ");
        String title = scanner.nextLine();

        Movie movie = MovieService.getByTitle(movieService, title);

        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new rating: ");
        float rating = Float.parseFloat(scanner.nextLine());

        movie = movie.withTitle(newTitle).withDurationInMin(Duration.of(duration, ChronoUnit.MINUTES)).withGenre(genre).withRating(rating);
        movieService = MovieService.updateById(movieService, movie.getId(), movie);

        System.out.println("Movie updated: " + movie);
    }

    private void deleteMovie() throws Exception {
        isCinemaSelected();

        System.out.print("Enter movie title to delete: ");
        String title = scanner.nextLine();

        Movie movie = MovieService.getByTitle(movieService, title);

        SessionService.getAll(sessionService).forEach((session) -> {
            try {
                if (session.getMovieId().equals(movie.getId())) {
                    UserService.getAll(userService).forEach((user) -> {
                        try {
                            StrictHashSet<Integer> userTicketIds = user.getTicketIds();

                            for (Integer ticketId : userTicketIds) {
                                Ticket ticket = TicketService.getById(ticketService, ticketId);
                                if (ticket.getSessionId().equals(session.getId())) {
                                    ticketService = TicketService.deleteById(ticketService, ticketId);
                                    userTicketIds.remove(ticketId);
                                }
                            }

                            userService = UserService.updateById(userService, user.getId(), user.withTicketIds(userTicketIds));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    sessionService = SessionService.deleteById(sessionService, session.getId());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        movieService = MovieService.deleteById(movieService, movie.getId());
        cinemaService = CinemaService.updateById(cinemaService, selectedCinemaId, CinemaService.getById(cinemaService, selectedCinemaId).removeMovieId(movie.getId()));
        System.out.println("Movie deleted: " + movie);
    }

    // SESSION

    private void viewAllSessions() {
        isCinemaSelected();

        Cinema cinema = CinemaService.getById(cinemaService, selectedCinemaId);

        System.out.println("All sessions for cinema " + cinema.getTitle() + ": ");
        cinema.getSessionIds().stream()
            .map(sessionId -> SessionService.getById(sessionService, sessionId))
            .forEach(System.out::println);
    }

    private void addSession() throws Exception {
        isCinemaSelected();

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        Movie movie = MovieService.getByTitle(movieService, title);

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = HallService.getByCinemaIdAndNumber(hallService, selectedCinemaId, hallNumber);

        System.out.print("Enter start time (format: 2007-12-03T10:15:30): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (format: 2007-12-03T11:15:30): ");
        String endTime = scanner.nextLine();

        sessionService = SessionService.create(sessionService, movie.getId(), hall.getId(), LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        Session session = SessionService.getByMovieIdAndHallId(sessionService, movie.getId(), hall.getId());

        cinemaService = CinemaService.updateById(cinemaService, selectedCinemaId, CinemaService.getById(cinemaService, selectedCinemaId).addSessionId(session.getId()));

        System.out.println("Session added: " + session);
    }

    private void updateSession() throws Exception {
        isCinemaSelected();

        System.out.print("Enter session movie title to update: ");
        String title = scanner.nextLine();
        Movie movie = MovieService.getByTitle(movieService, title);

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        Hall hall = HallService.getByCinemaIdAndNumber(hallService, selectedCinemaId, hallNumber);

        Session session = SessionService.getByMovieIdAndHallId(sessionService, movie.getId(), hall.getId());

        System.out.print("Enter new start time (format: 2007-12-03T10:15:30): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter new end time (format: 2007-12-03T11:15:30): ");
        String endTime = scanner.nextLine();

        session = session.withStartTime(LocalDateTime.parse(startTime)).withEndTime(LocalDateTime.parse(endTime));
        sessionService = SessionService.updateById(sessionService, session.getId(), session);

        System.out.println("Session updated: " + session);
    }

    private void deleteSession() throws Exception {
        isAdmin();

        System.out.print("Enter session id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Session session = SessionService.getById(sessionService, id);

        UserService.getAll(userService).forEach((user) -> {
            try {
                deleteUserTicketsBySessionId(session.getId(), user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        sessionService = SessionService.deleteById(sessionService, id);
        cinemaService = CinemaService.updateById(cinemaService, selectedCinemaId, CinemaService.getById(cinemaService, selectedCinemaId).removeSessionId(session.getId()));
        System.out.println("Session deleted: " + session);
    }

    private void viewAvailableSeats() {
        isCinemaSelected();

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        SessionService.getByMovieId(sessionService, MovieService.getByTitle(movieService, title).getId()).forEach((session) -> {
            System.out.println("Available seats for session: " + session);
            IntStream.rangeClosed(1, HallService.getById(hallService, session.getHallId()).getSeats())
                .filter((seat) -> !session.getBookedSeats().contains(seat))
                .mapToObj((seat) -> seat + " ")
                .forEach(System.out::print);
            System.out.println();
        });
    }

    // TICKET

    private void viewUserTickets() {
        isUser();

        System.out.println("Your tickets:");
        UserService.getById(userService, loggedInUserId).getTicketIds().stream()
            .map((ticketId) -> TicketService.getById(ticketService, ticketId))
            .forEach(System.out::println);
    }

    private void bookTicket() throws Exception {
        isUser();
        isCinemaSelected();

        System.out.print("Enter session movie title: ");
        String title = scanner.nextLine();

        SessionService.getByMovieId(sessionService, MovieService.getByTitle(movieService, title).getId()).forEach(System.out::println);

        System.out.print("Enter session id: ");
        int sessionId = Integer.parseInt(scanner.nextLine());
        Session session = SessionService.getById(sessionService, sessionId);

        Hall hall = HallService.getById(hallService, session.getHallId());

        System.out.print("Enter seat number: ");
        int seat = Integer.parseInt(scanner.nextLine());
        if(seat < 1 || seat > hall.getSeats())
            System.out.println("Seat " + seat + " is out of bounds");

        ticketService = TicketService.create(ticketService, sessionId, seat);
        Ticket ticket = TicketService.getBySessionIdAndSeat(ticketService, sessionId, seat);

        sessionService = SessionService.updateById(sessionService, session.getId(), session.bookSeat(seat));
        userService = UserService.updateById(userService, loggedInUserId, UserService.getById(userService, loggedInUserId).addTicketId(ticket.getId()));

        System.out.println("Ticket booked successfully: " + ticket);
    }

    private void cancelTicket() throws Exception {
        isUser();

        System.out.println("Your tickets:");
        Set<Ticket> userTickets = UserService.getById(userService, loggedInUserId).getTicketIds().stream()
                .map((id) -> TicketService.getById(ticketService, id))
                .collect(Collectors.toSet());
        userTickets.forEach(System.out::println);

        System.out.print("Enter the ticket id to cancel: ");
        Integer ticketId = Integer.parseInt(scanner.nextLine());

        Ticket ticket = userTickets
                .stream()
                .filter((t) -> Objects.equals(t.getId(), ticketId))
                .findFirst()
                .orElseThrow(() -> new Exception("Ticket not found!"));

        Session session = SessionService.getById(sessionService, ticket.getSessionId());

        ticketService = TicketService.deleteById(ticketService, ticketId);
        sessionService = SessionService.updateById(sessionService, session.getId(), session.cancelBookSeat(ticket.getSeat()));
        userService = UserService.updateById(userService, loggedInUserId, UserService.getById(userService, loggedInUserId).removeTicketId(ticketId));

        System.out.println("Ticket canceled: " + ticket);
    }

    private void purchaseTicket() throws Exception {
        isUser();

        System.out.println("Your booked tickets:");
        Set<Ticket> bookedUserTickets = UserService.getById(userService, loggedInUserId).getTicketIds().stream()
                .map((id) -> TicketService.getById(ticketService, id))
                .filter((ticket) -> ticket.getStatus().equals(TicketStatus.Booked))
                .collect(Collectors.toSet());
        bookedUserTickets.forEach(System.out::println);

        System.out.print("Enter the ticket id to purchase: ");
        int ticketId = Integer.parseInt(scanner.nextLine());

        Ticket ticket = bookedUserTickets.stream().filter((t) -> t.getId() == ticketId)
                .findFirst()
                .orElseThrow(() -> new Exception("Ticket not found!"));

        ticketService = TicketService.updateById(ticketService, ticketId, ticket.withStatus(TicketStatus.Purchased));
        ticket = TicketService.getById(ticketService, ticketId);

        System.out.println("Ticket purchased: " + ticket);
    }

    // OTHER

    private void viewStatistics() {
        isAdmin();
        isCinemaSelected();

        int totalBookedTickets = CinemaService.getById(cinemaService, selectedCinemaId).getSessionIds().stream()
                .mapToInt((sessionId) -> SessionService.getById(sessionService, sessionId).getBookedSeats().size())
                .sum();

        System.out.println("Total booked tickets: " + totalBookedTickets);
    }

    private void deleteUserTicketsBySessionId(Integer sessionId, User user) throws Exception {
        StrictHashSet<Integer> userTicketIds = user.getTicketIds();

        for (Integer ticketId : userTicketIds) {
            Ticket ticket = TicketService.getById(ticketService, ticketId);
            if(ticket.getSessionId().equals(sessionId)) {
                ticketService = TicketService.deleteById(ticketService, ticketId);
                userTicketIds.remove(ticketId);
            }
        }

        userService = UserService.updateById(userService, user.getId(), user.withTicketIds(userTicketIds));
    }

    private void isUser() {
        if (loggedInUserId == null || UserService.getById(userService, loggedInUserId) == null)
            throw new RuntimeException("You must be logged in as User!");
    }

    private void isAdmin() {
        if (loggedInUserId == null || AdminService.getById(adminService, loggedInUserId) == null)
            throw new RuntimeException("You must be logged in as Admin!");
    }

    private void isCinemaSelected() {
        if (selectedCinemaId == null)
            throw new RuntimeException("Select cinema first!");
    }
}
