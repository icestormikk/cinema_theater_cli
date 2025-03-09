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
import java.util.Scanner;

public class CinemaCLI {
    private Cinema cinema;
    private UserService userService;
    private AdminService adminService;
    private User user;
    private Admin admin;
    private Scanner scanner;

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

    private void loginAdmin() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        admin = adminService.findAdminByUsername(username);
        if (admin != null) {
            System.out.print("Logged in as Admin: " + admin.getUsername());
            adminMenu();
        } else {
            System.out.println("Admin not found!");
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. View My Tickets");
            System.out.println("3. Logout");
            System.out.println("4. View Available Seats");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Purchase Ticket");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    bookTicket();
                    break;
                case "2":
                    viewUserTickets();
                    break;
                case "3":
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
            System.out.println("2. Add Hall");
            System.out.println("3. Add Movie");
            System.out.println("4. Add Session");
            System.out.println("5. Logout");
            System.out.println("6. Delete Movie");
            System.out.println("7. Delete Session");
            System.out.println("8. View Statistics");
            System.out.println("9. Edit Movie");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    createCinema();
                    break;
                case "2":
                    addHall();
                    break;
                case "3":
                    addMovie();
                    break;
                case "4":
                    addSession();
                    break;
                case "5":
                    user = null;
                    return;
                default:
                    System.out.println("Invalid command!");
            }
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

    public void viewUserTickets() {
        if (user == null) {
            System.out.println("You must be logged in as User!");
            return;
        }
        System.out.println("Your tickets:");
        user.getTickets().forEach(System.out::println);
    }

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

    private void deleteSession() {
        if (admin == null) {
            System.out.println("You must be logged in as Admin!");
            return;
        }

        System.out.print("Enter session movie title to delete: ");
        String title = scanner.nextLine();

        Session session = null;
        for (Session s: cinema.getSessions()) {
            if(s.getMovie().getTitle().equalsIgnoreCase(title)) {
                session = s;
            }
        }
        if (session != null) {
            cinema.getSessions().remove(session);
            System.out.println("Session deleted: " + session);
        } else {
            System.out.println("Session not found!");
        }
    }

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
}
