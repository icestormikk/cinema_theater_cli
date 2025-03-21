package com.icestormikk.cli;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.domain.cinema.Session;
import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.domain.cinema.TicketStatus;
import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.implementations.AdminRepositoryImpl;
import com.icestormikk.repositories.implementations.CinemaRepositoryImpl;
import com.icestormikk.repositories.implementations.HallRepositoryImpl;
import com.icestormikk.repositories.implementations.MovieRepositoryImpl;
import com.icestormikk.repositories.implementations.SessionRepositoryImpl;
import com.icestormikk.repositories.implementations.TicketRepositoryImpl;
import com.icestormikk.repositories.implementations.UserRepositoryImpl;
import com.icestormikk.services.AdminService;
import com.icestormikk.services.CinemaService;
import com.icestormikk.services.HallService;
import com.icestormikk.services.MovieService;
import com.icestormikk.services.SessionService;
import com.icestormikk.services.TicketService;
import com.icestormikk.services.UserService;
import com.icestormikk.services.implementations.AdminServiceImpl;
import com.icestormikk.services.implementations.CinemaServiceImpl;
import com.icestormikk.services.implementations.HallServiceImpl;
import com.icestormikk.services.implementations.MovieServiceImpl;
import com.icestormikk.services.implementations.SessionServiceImpl;
import com.icestormikk.services.implementations.TicketServiceImpl;
import com.icestormikk.services.implementations.UserServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Integer cinemaId;
    private Integer userId;
    private Integer adminId;

    public CinemaCLI() {
        this.scanner = new Scanner(System.in);
        this.adminService = new AdminServiceImpl(new AdminRepositoryImpl());
        this.cinemaService = new CinemaServiceImpl(new CinemaRepositoryImpl());
        this.hallService = new HallServiceImpl(new HallRepositoryImpl());
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
        this.sessionService = new SessionServiceImpl(new SessionRepositoryImpl());
        this.ticketService = new TicketServiceImpl(new TicketRepositoryImpl());
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
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
                        userId = null;
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
                        adminId = null;
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

        userService = userService.create(firstName, lastName, username);

        if (userId != null)
            System.out.println("Successfully registered user: " + userService.getByUsername(username));
    }

    private void loginUser() throws Exception {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User loggedInUser = userService.getByUsername(username);
        userId = loggedInUser.getId();

        System.out.print("Logged in as User: " + loggedInUser.getUsername());

        userMenu();
    }

    private void viewAllUsers() {
        isAdmin();

        System.out.println("All users:");
        for (User user : userService.getAll())
            System.out.println(user);
    }

    private void updateUser() throws Exception {
        isAdmin();

        System.out.print("Enter user name to update: ");
        String name = scanner.nextLine();

        User user = userService.getByUsername(name);

        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        userService = (UserService) userService.updateById(user.getId(), user.withFirstName(firstname).withLastName(lastName).withUsername(username));

        System.out.println("User updated: " + userService.getByUsername(username));
    }

    private void deleteUser() throws Exception {
        isAdmin();

        System.out.print("Enter user name to delete: ");
        String name = scanner.nextLine();

        userService = (UserService) userService.deleteById(userService.getByUsername(name).getId());

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

        adminService = adminService.create(firstName, lastName, username);

        System.out.println("Successfully registered admin: " + username + " (" + firstName + " " + lastName + ")");
    }

    private void loginAdmin() throws Exception {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        Admin loggedInAdmin = adminService.getByUsername(username);
        adminId = loggedInAdmin.getId();

        System.out.print("Logged in as Admin: " + loggedInAdmin.getUsername());

        adminMenu();
    }

    private void viewAllAdmins() {
        isAdmin();

        System.out.println("All admins:");
        for (Admin admin : adminService.getAll())
            System.out.println(admin);
    }

    private void updateAdmin() throws Exception {
        isAdmin();

        System.out.print("Enter admin name to update: ");
        String name = scanner.nextLine();
        Admin admin = adminService.getByUsername(name);

        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new user name: ");
        String userName = scanner.nextLine();

        Admin updatedAdmin = admin.withFirstName(firstname).withLastName(lastName).withUsername(userName);
        adminService = (AdminService) adminService.updateById(admin.getId(), updatedAdmin);

        System.out.println("Admin updated: " + updatedAdmin);
    }

    private void deleteAdmin() throws Exception {
        isAdmin();

        System.out.print("Enter admin name to delete: ");
        String name = scanner.nextLine();

        Admin admin = adminService.getByUsername(name);

        adminService = (AdminService) adminService.deleteById(adminService.getByUsername(name).getId());
        if(Objects.equals(admin.getId(), adminId))
            adminId = null;

        System.out.println("Admin deleted: " + name);
    }

    // CINEMA

    private void viewAllCinemas() {
        isAdmin();

        System.out.println("All cinemas:");
        for (Integer id: adminService.getById(adminId).getCinemaIds())
            System.out.println(cinemaService.getById(id));
    }

    private void createCinema() throws Exception {
        isAdmin();

        System.out.print("Enter cinema title: ");
        String cinemaTitle = scanner.nextLine();
        System.out.print("Enter cinema address: ");
        String cinemaAddress = scanner.nextLine();

        cinemaService = cinemaService.create(cinemaTitle, cinemaAddress);
        Cinema cinema = cinemaService.getByTitle(cinemaTitle);
        cinemaId = cinema.getId();

        adminService = (AdminService) adminService.updateById(adminId, adminService.getById(adminId).addCinemaId(cinemaId));

        System.out.println("Cinema created: " + cinema);
    }

    private void updateCinema() throws Exception {
        isAdmin();

        System.out.print("Enter cinema title to update: ");
        String title = scanner.nextLine();

        Cinema cinema = cinemaService.getByTitle(title);

        System.out.print("Enter new cinema title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        cinema = cinema.withAddress(address).withTitle(newTitle);
        cinemaService = (CinemaService) cinemaService.updateById(cinema.getId(), cinema);
        adminService = (AdminService) adminService.updateById(adminId, adminService.getById(adminId).addCinemaId(cinema.getId()));

        System.out.println("Cinema updated: " + cinema);
    }

    private void deleteCinema() throws Exception {
        isAdmin();

        System.out.print("Enter cinema name to delete: ");
        String name = scanner.nextLine();

        Cinema cinema = cinemaService.getByTitle(name);

        for (Integer cinemaSessionId: cinema.getSessionIds()) {
            for (User user: userService.getAll()) {
                Set<Integer> userTicketIds = user.getTicketIds();
                for (Integer ticketId: userTicketIds) {
                    Ticket ticket = ticketService.getById(ticketId);
                    if(ticket.getSessionId().equals(cinemaSessionId)) {
                        ticketService = (TicketService) ticketService.deleteById(ticketId);
                        userTicketIds.remove(ticketId);
                    }
                }
                userService = (UserService) userService.updateById(user.getId(), user.withTicketIds(userTicketIds));
            }
        }

        cinemaService = (CinemaService) cinemaService.deleteById(cinema.getId());
        if(cinema.getId() == cinemaId)
            cinemaId = null;

        adminService = (AdminService) adminService.updateById(adminId, adminService.getById(adminId).removeCinemaId(cinema.getId()));
        System.out.println("Cinema deleted: " + cinema);
    }

    // HALL

    private void addHall() throws Exception {
        isCinemaSelected();

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());

        hallService = hallService.create(cinemaId, hallNumber, seats);
        Hall hall = hallService.getByCinemaIdAndNumber(cinemaId, hallNumber);

        cinemaService = (CinemaService) cinemaService.updateById(cinemaId, cinemaService.getById(cinemaId).addHallId(hall.getId()));

        System.out.println("Hall added: " + hall);
    }

    private void viewAllHalls() {
        isCinemaSelected();

        System.out.println("All halls:");
        for(Integer hallId: cinemaService.getById(cinemaId).getHallIds())
            System.out.println(hallService.getById(hallId));
    }

    private void updateHall() throws Exception {
        isCinemaSelected();

        System.out.print("Enter hall number to update: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = hallService.getByCinemaIdAndNumber(cinemaId, hallNumber);

        System.out.print("Enter new hall number: ");
        int newHallNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());

        hall = hall.withHallNumber(newHallNumber).withSeats(seats);
        hallService = (HallService) hallService.updateById(hall.getId(), hall);

        System.out.println("Hall updated: " + hall);
    }

    private void deleteHall() throws Exception {
        isCinemaSelected();

        System.out.print("Enter hall number to delete: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = hallService.getByCinemaIdAndNumber(cinemaId, hallNumber);

        for (Integer hallSessionId: hall.getSessionIds()) {
            sessionService = (SessionService) sessionService.deleteById(hallSessionId);
            for (User user: userService.getAll()) {
                Set<Integer> userTicketIds = user.getTicketIds();
                for (Integer ticketId: userTicketIds) {
                    Ticket ticket = ticketService.getById(ticketId);
                    if(ticket.getSessionId().equals(hallSessionId)) {
                        ticketService = (TicketService) ticketService.deleteById(ticketId);
                        userTicketIds.remove(ticketId);
                    }
                }
                userService = (UserService) userService.updateById(user.getId(), user.withTicketIds(userTicketIds));
            }
        }

        cinemaService = (CinemaService) cinemaService.updateById(cinemaId, cinemaService.getById(cinemaId).removeHallId(hall.getId()));
        System.out.println("Hall deleted: " + hall);
    }

    // MOVIE

    private void viewAllMovies() {
        isCinemaSelected();

        System.out.println("All movies:");
        for (Integer movieId: cinemaService.getById(cinemaId).getMovieIds())
            System.out.println(movieService.getById(movieId));
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

        movieService = movieService.create(title, genre, Duration.of(duration, ChronoUnit.MINUTES), rating);
        Movie movie = movieService.getByTitle(title);

        cinemaService = (CinemaService) cinemaService.updateById(cinemaId, cinemaService.getById(cinemaId).addMovieId(movie.getId()));

        System.out.println("Movie added: " + movie);
    }

    private void searchMovie() {
        isCinemaSelected();

        System.out.print("Enter movie title to search: ");
        String title = scanner.nextLine();

        System.out.print("Successfully found a movie: " + movieService.getByTitle(title));
    }

    private void editMovie() throws Exception {
        isAdmin();
        isCinemaSelected();

        System.out.print("Enter movie title to edit: ");
        String title = scanner.nextLine();

        Movie movie = movieService.getByTitle(title);

        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new rating: ");
        float rating = Float.parseFloat(scanner.nextLine());

        movie = movie.withTitle(newTitle).withDurationInMin(Duration.of(duration, ChronoUnit.MINUTES)).withGenre(genre).withRating(rating);
        movieService = (MovieService) movieService.updateById(movie.getId(), movie);

        System.out.println("Movie updated: " + movie);
    }

    private void deleteMovie() throws Exception {
        isCinemaSelected();

        System.out.print("Enter movie title to delete: ");
        String title = scanner.nextLine();

        Movie movie = movieService.getByTitle(title);

        for (Session session : sessionService.getAll()) {
            if(session.getMovieId().equals(movie.getId())) {
                for(User user: userService.getAll()) {
                    Set<Integer> userTicketIds = user.getTicketIds();

                    for(Integer ticketId: userTicketIds) {
                        Ticket ticket = ticketService.getById(ticketId);
                        if (ticket.getSessionId().equals(session.getId())) {
                            ticketService = (TicketService) ticketService.deleteById(ticketId);
                            userTicketIds.remove(ticketId);
                        }
                    }

                    userService = (UserService) userService.updateById(user.getId(), user.withTicketIds(userTicketIds));
                }

                sessionService = (SessionService) sessionService.deleteById(session.getId());
            }
        }

        movieService = (MovieService) movieService.deleteById(movie.getId());
        cinemaService = (CinemaService) cinemaService.updateById(cinemaId, cinemaService.getById(cinemaId).removeMovieId(movie.getId()));
        System.out.println("Movie deleted: " + movie);
    }

    // SESSION

    private void viewAllSessions() {
        isCinemaSelected();

        Cinema cinema = cinemaService.getById(cinemaId);

        System.out.println("All sessions for cinema " + cinema.getTitle() + ": ");
        for (Integer sessionId: cinema.getSessionIds())
            System.out.println(sessionService.getById(sessionId));
    }

    private void addSession() throws Exception {
        isCinemaSelected();

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        Movie movie = movieService.getByTitle(title);

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());

        Hall hall = hallService.getByCinemaIdAndNumber(cinemaId, hallNumber);

        System.out.print("Enter start time (format: 2007-12-03T10:15:30): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (format: 2007-12-03T11:15:30): ");
        String endTime = scanner.nextLine();

        sessionService = sessionService.create(movie.getId(), hall.getId(), LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        Session session = sessionService.getByMovieIdAndHallId(movie.getId(), hall.getId());

        cinemaService = (CinemaService) cinemaService.updateById(cinemaId, cinemaService.getById(cinemaId).addSessionId(session.getId()));

        System.out.println("Session added: " + session);
    }

    private void updateSession() throws Exception {
        isCinemaSelected();

        System.out.print("Enter session movie title to update: ");
        String title = scanner.nextLine();
        Movie movie = movieService.getByTitle(title);

        System.out.print("Enter hall number: ");
        int hallNumber = Integer.parseInt(scanner.nextLine());
        Hall hall = hallService.getByCinemaIdAndNumber(cinemaId, hallNumber);

        Session session = sessionService.getByMovieIdAndHallId(movie.getId(), hall.getId());

        System.out.print("Enter new start time (format: 2007-12-03T10:15:30): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter new end time (format: 2007-12-03T11:15:30): ");
        String endTime = scanner.nextLine();

        session = session.withStartTime(LocalDateTime.parse(startTime)).withEndTime(LocalDateTime.parse(endTime));
        sessionService = (SessionService) sessionService.updateById(session.getId(), session);

        System.out.println("Session updated: " + session);
    }

    private void deleteSession() throws Exception {
        isAdmin();

        System.out.print("Enter session id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Session session = sessionService.getById(id);

        for (User user : userService.getAll()) {
            Set<Integer> ticketIds = user.getTicketIds();

            for (Integer ticketId: ticketIds) {
                if (ticketService.getById(ticketId).getSessionId().equals(session.getId())) {
                    ticketService = (TicketService) ticketService.deleteById(ticketId);
                    ticketIds.remove(ticketId);
                }
            }

            userService = (UserService) userService.updateById(user.getId(), user.withTicketIds(ticketIds));
        }

        sessionService = (SessionService) sessionService.deleteById(id);
        cinemaService = (CinemaService) cinemaService.updateById(cinemaId, cinemaService.getById(cinemaId).removeSessionId(session.getId()));
        System.out.println("Session deleted: " + session);
    }

    private void viewAvailableSeats() {
        isCinemaSelected();

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        Set<Session> sessions = sessionService.getByMovieId(movieService.getByTitle(title).getId());

        for (Session session : sessions) {
            System.out.println("Available seats for session: " + session);
            for (int seat = 1; seat <= hallService.getById(session.getHallId()).getSeats(); seat++) {
                if (!session.getBookedSeats().contains(seat)) {
                    System.out.print(seat + " ");
                }
            }
            System.out.println();
        }
    }

    // TICKET

    private void viewUserTickets() {
        isUser();

        System.out.println("Your tickets:");
        for (Integer ticketId : userService.getById(userId).getTicketIds())
            System.out.println(ticketService.getById(ticketId));
    }

    private void bookTicket() throws Exception {
        isUser();
        isCinemaSelected();

        System.out.print("Enter session movie title: ");
        String title = scanner.nextLine();

        Set<Session> sessions = sessionService.getByMovieId(movieService.getByTitle(title).getId());
        for (Session session : sessions)
            System.out.println(session);

        System.out.print("Enter session id: ");
        int sessionId = Integer.parseInt(scanner.nextLine());
        Session session = sessionService.getById(sessionId);

        Hall hall = hallService.getById(session.getHallId());

        System.out.print("Enter seat number: ");
        int seat = Integer.parseInt(scanner.nextLine());
        if(seat < 1 || seat > hall.getSeats())
            System.out.println("Seat " + seat + " is out of bounds");

        ticketService = ticketService.create(sessionId, seat);
        Ticket ticket = ticketService.getBySessionIdAndSeat(sessionId, seat);

        sessionService = (SessionService) sessionService.updateById(session.getId(), session.bookSeat(seat));
        userService = (UserService) userService.updateById(userId, userService.getById(userId).addTicketId(ticket.getId()));

        System.out.println("Ticket booked successfully: " + ticket);
    }

    private void cancelTicket() throws Exception {
        isUser();

        System.out.println("Your tickets:");
        Set<Ticket> userTickets = userService.getById(userId).getTicketIds().stream().map((id) -> ticketService.getById(id)).collect(Collectors.toSet());
        for (Ticket ticket : userTickets)
            System.out.println(ticket);

        System.out.print("Enter the ticket id to cancel: ");
        Integer ticketId = Integer.parseInt(scanner.nextLine());

        Ticket ticket = userTickets
                .stream()
                .filter((t) -> Objects.equals(t.getId(), ticketId))
                .findFirst()
                .orElseThrow(() -> new Exception("Ticket not found!"));

        Session session = sessionService.getById(ticket.getSessionId());

        ticketService = (TicketService) ticketService.deleteById(ticketId);
        sessionService = (SessionService) sessionService.updateById(session.getId(), session.cancelBookSeat(ticket.getSeat()));
        userService = (UserService) userService.updateById(userId, userService.getById(userId).removeTicketId(ticketId));

        System.out.println("Ticket canceled: " + ticket);
    }

    private void purchaseTicket() throws Exception {
        isUser();

        System.out.println("Your booked tickets:");
        Set<Ticket> bookedUserTickets = userService.getById(userId).getTicketIds().stream()
                .map((id) -> ticketService.getById(id))
                .filter((ticket) -> ticket.getStatus().equals(TicketStatus.Booked))
                .collect(Collectors.toSet());
        for (Ticket ticket : bookedUserTickets)
            System.out.println(ticket);

        System.out.print("Enter the ticket id to purchase: ");
        int ticketId = Integer.parseInt(scanner.nextLine());

        Ticket ticket = bookedUserTickets.stream().filter((t) -> t.getId() == ticketId)
                .findFirst()
                .orElseThrow(() -> new Exception("Ticket not found!"));

        ticketService = (TicketService) ticketService.updateById(ticketId, ticket.withStatus(TicketStatus.Purchased));
        ticket = ticketService.getById(ticketId);

        System.out.println("Ticket purchased: " + ticket);
    }

    // OTHER

    private void viewStatistics() {
        isAdmin();
        isCinemaSelected();

        int totalBookedTickets = 0;
        for (Integer sessionId: cinemaService.getById(cinemaId).getSessionIds()) {
            int bookedSeats = sessionService.getById(sessionId).getBookedSeats().size();
            totalBookedTickets += bookedSeats;
        }

        System.out.println("Total booked tickets: " + totalBookedTickets);
    }

    private void isUser() {
        if (userId == null)
            throw new RuntimeException("You must be logged in as User!");
    }

    private void isAdmin() {
        if (adminId == null)
            throw new RuntimeException("You must be logged in as User!");
    }

    private void isCinemaSelected() {
        if (cinemaId == null)
            throw new RuntimeException("Select cinema first!");
    }
}
