import java.text.ParseException; //used in the isPastDateTime method to catch parsing errors when converting a string (representing a date and time) into a Date object.
import java.text.SimpleDateFormat;//to parse a date and time string into a Date object and compare it with the current date.
import java.util.ArrayList;// It allows adding, removing, and iterating through appointments dynamically.
import java.util.Date;//method to compare the given appointment date and time with the current system time.
import java.util.Scanner;//used extensively to take inputs for patient details, appointment details, and user menu choices.
import java.util.regex.Matcher;//to validate date and time strings against specific patterns
import java.util.regex.Pattern;//It's used in conjunction with Matcher to perform regex-based operations.

// Abstract Class (Abstraction)
abstract class Person {
    private String name;// Encapsulation: private variables accessible only via getters and setters
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Encapsulation: Getters and Setters provide controlled access to private fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Abstract method (Abstraction): Implemented by child classes
    public abstract void showDetails();
}

// Inheritance: Patient extends Person
class Patient extends Person {
    private String patientId;

    public Patient(String name, int age, String patientId) {
        super(name, age); // Inheritance: Calls the constructor of the parent class (Person)
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // Polymorphism: Overriding the abstract method from the Person class

    @Override
    public void showDetails() {
        System.out.println("PATIENT DETAILS");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Patient ID: " + patientId);
    }
}

// Appointment Class
class Appointment {
    private final Person person;
    private String date;
    private String time;

    public Appointment(Person person, String date, String time) {
        this.person = person;
        this.date = date;
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void showAppointmentDetails() {
        person.showDetails(); // Polymorphism: Calls Patient's showDetails()
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
    }
}

// Dental Clinic System
public class DentalClinicSystem {
    private static final ArrayList<Appointment> appointments = new ArrayList<>();

    public static void addAppointment(Scanner scanner) {
        System.out.println("\n******ADD APPOINTMENTS*****\n");
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
    
        Patient patient = new Patient(name, age, patientId);
    
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Appointment Time (HH:MM AM/PM): ");
        String time = scanner.nextLine();
    
        // Validate date format
        if (!isValidDate(date)) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }
    
        // Validate time format
        if (!isValidTime(time)) {
            System.out.println("Invalid time format. Please use HH:MM AM/PM.");
            return;
        }
    
        // Check if the appointment date and time are in the past
        if (isPastDateTime(date, time)) {
            System.out.println("Cannot schedule an appointment in the past.");
            return;
        }
    
        // Check for conflicts
        for (Appointment existing : appointments) {
            if (existing.getDate().equals(date) && existing.getTime().equals(time)) {
                System.out.println("Conflict: Another appointment is scheduled at this date and time.");
                return;
            }
        }
    
        appointments.add(new Appointment(patient, date, time));
        System.out.println("Appointment added successfully!");
    }
    
    // Helper method to check if the date is in the correct format
    private static boolean isValidDate(String date) {
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
    
    // Helper method to check if the time is in the correct format
    private static boolean isValidTime(String time) {
        String timePattern = "^([01]?[0-9]|2[0-3]):([0-5][0-9]) (AM|PM)$";
        Pattern pattern = Pattern.compile(timePattern);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
    
    // Helper method to check if the date and time are in the past
    private static boolean isPastDateTime(String date, String time) {
        try {
            String dateTimeStr = date + " " + time;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            Date appointmentDateTime = formatter.parse(dateTimeStr);
            Date currentDateTime = new Date();
    
            return appointmentDateTime.before(currentDateTime);
        } catch (ParseException e) {
            System.out.println("Error parsing date or time.");
            return true; // Treat as invalid if error occurs
        }
    }

    public static void cancelAppointment(Scanner scanner) {
        System.out.println("\n*****CANCEL APPOINTMENTS*****");
    
        // Display all current appointments
        System.out.println("\nCurrent Appointments\n");
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }
    
        // Display each appointment with its details
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            appointment.showAppointmentDetails();
            System.out.println("Appointment Number: " + (i + 1));
            System.out.println("-----------------------");
        }
    
        // Ask for the patient ID of the appointment to cancel
        System.out.print("Enter Patient ID of the appointment you want to cancel: ");
        String patientId = scanner.nextLine();
    
        // Remove the appointment if it matches the patient ID
        boolean removed = false;
        for (Appointment appointment : appointments) {
            if (appointment.getPerson() instanceof Patient patient && patient.getPatientId().equals(patientId)) {
                appointments.remove(appointment);
                removed = true;
                break; // Exit loop once the appointment is canceled
            }
        }
    
        if (removed) {
            System.out.println("Appointment canceled successfully!");
        } else {
            System.out.println("No appointment found with the given Patient ID.");
        }
    }
    

    public static void viewAppointments() {
        System.out.println("\n*****VIEW ALL APPOINTMENTS*****\n");
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            for (Appointment appointment : appointments) {
                appointment.showAppointmentDetails();
                System.out.println("-----------------------");
            }
        }
    }

    public static void searchAppointmentsByDate(Scanner scanner) {
        System.out.println("\n*****SEARCH APPOINTMENTS BY DATE*****\n");
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String searchDate = scanner.nextLine();

        if (!isValidDate(searchDate)) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        boolean found = false;
        System.out.println("\nAppointments for " + searchDate + ":\n");

        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(searchDate)) {
                appointment.showAppointmentDetails();
                System.out.println("-----------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for the given date.");
        }
    }

    public static void updateAppointment(Scanner scanner) {
        System.out.println("\n*****UPDATE APPOINTMENTS*****");
    
        // Display all current appointments before proceeding with update
        System.out.println("\nCurrent Appointments\n");
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }
    
        // Display each appointment with its details
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            appointment.showAppointmentDetails();
            System.out.println("Appointment Number: " + (i + 1));
            System.out.println("-----------------------");
        }
    
        // Ask for the patient ID of the appointment to update
        System.out.print("Enter Patient ID of the appointment you want to update: ");
        String patientId = scanner.nextLine();
    
        // Find the appointment by patient ID
        for (Appointment appointment : appointments) {
            if (appointment.getPerson() instanceof Patient patient && patient.getPatientId().equals(patientId)) {
    
                // Display existing appointment details before updating
                System.out.println("Existing Appointment Details:\n");
                appointment.showAppointmentDetails();
    
                // Update Patient Details
                System.out.print("Enter Updated Name: ");
                String newName = scanner.nextLine();
                if (!newName.isBlank()) {
                    patient.setName(newName);
                }
    
                System.out.print("Enter Updated Age: ");
                int newAge = scanner.nextInt();
                if (newAge != -1) {
                    patient.setAge(newAge);
                }
                scanner.nextLine(); // Consume newline
    
                System.out.print("Enter Updated Patient ID: ");
                String newPatientId = scanner.nextLine();
                if (!newPatientId.isBlank()) {
                    patient.setPatientId(newPatientId);
                }
    
                // Update Appointment Details
                System.out.print("Enter New Appointment Date (YYYY-MM-DD): ");
                String newDate = scanner.nextLine();
                if (!newDate.isBlank()) {
                    if (!isValidDate(newDate)) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        return;
                    }
                    if (isPastDateTime(newDate, appointment.getTime())) {
                        System.out.println("Cannot update to a past appointment time.");
                        return;
                    }
                    appointment.setDate(newDate);
                }
    
                System.out.print("Enter New Appointment Time (HH:MM AM/PM): ");
                String newTime = scanner.nextLine();
                if (!newTime.isBlank()) {
                    if (!isValidTime(newTime)) {
                        System.out.println("Invalid time format. Please use HH:MM AM/PM.");
                        return;
                    }
                    // Check for conflicts
                    for (Appointment existing : appointments) {
                        if (existing != appointment && existing.getDate().equals(newDate) && existing.getTime().equals(newTime)) {
                            System.out.println("Conflict: Another appointment is already scheduled at this date and time.");
                            return;
                        }
                    }
                    appointment.setTime(newTime);
                }
    
                System.out.println("Appointment updated successfully!");
                return;
            }
        }
    
        System.out.println("No appointment found with the given Patient ID.");
    }
    

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ");
                System.out.println("\n\t\t\t\t\t\tWELCOME TO OPEN WIDE, BOOK EASY DENTAL CLINIC APPOINTMENT\n");
                System.out.println("\t\t\t\t\tAt Open Wide, Book Easy we believe booking a dental appointment should be");
                System.out.println("\t\t\t\tas easy as brushing your teeth. Our user-friendly platform allows you to quickly schedule,");
                System.out.println("\t\t\t\treschedule, or cancel your appointments with just a few clicks. Whether it's for a routine");
                System.out.println("\t\t\t\t\tcheckup or an urgent dental need, we're here to make your experience hassle-free,");
                System.out.println("\t\t\t\t\t\t\tso you can keep smiling confidently!\n");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n");
                System.out.println("1. Add Appointment");
                System.out.println("2. Cancel Appointment");
                System.out.println("3. View Appointments");
                System.out.println("4. Update Appointment");
                System.out.println("5. Search Appointments by Date");
                System.out.println("6. Exit\n");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addAppointment(scanner);
                    case 2 -> cancelAppointment(scanner);
                    case 3 -> viewAppointments();
                    case 4 -> updateAppointment(scanner);
                    case 5 -> searchAppointmentsByDate(scanner);
                    case 6 -> System.out.println("Exiting the system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);
        }
    }
}
