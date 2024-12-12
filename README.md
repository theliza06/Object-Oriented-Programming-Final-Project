# Object-Oriented-Programming-Final-Project
This repository is for my Final Porject in OOP.

# I. Project Overview

<p align="justify">
A Dental Clinic Appointment System is a software solution that helps manage the scheduling and organization of patient appointments within a dental clinic. It allows patients to book, update, and cancel appointments by providing necessary details like name, age, and patient ID, along with the desired date and time for their visit. The system ensures that appointments are scheduled for the future, validates proper date and time formats, and checks for conflicts with existing appointments. Additionally, it enables clinic staff to view, update, and search appointments by date or patient ID, offering a seamless way to manage appointments. By automating these tasks, the system enhances efficiency, reduces errors, and improves the overall patient experience at the clinic.
  </p>

  # II. Explanation of how OOP principles were applied 
  
  ## Abstraction
<p align="justify">
The Person class is abstract and contains common attributes (name, age) and an abstract method showDetails(). The purpose of this abstraction is to represent a generic person (could be a patient or a doctor), without specifying the details. The concrete class Patient inherits from Person and provides a specific implementation for the showDetails() method.
</p>

## Encapsulation
<p align="justify">
The class Person has private attributes (name, age), and these are accessed and modified via public getter and setter methods. This ensures controlled access to the private data, enforcing better data integrity. For example, the getName() and setName() methods allow controlled modification of the name field.
</p>

## Inheritance
<p align="justify">
The Patient class inherits from the Person class, which allows the Patient class to reuse code from the Person class (e.g., the name and age fields). The Patient class adds additional attributes (like patientId) and behavior (the implementation of the showDetails() method), thus demonstrating the concept of inheritance.
</p>

## Polymorphism
<p align="justify">
Polymorphism is evident in the Appointment class, where the method showAppointmentDetails() calls the showDetails() method of Person, but since Patient overrides this method, it uses the showDetails() method from the Patient class. This allows different types of Person objects (e.g., Patient) to behave differently when showDetails() is invoked.
</p>

# III. Details of the chosen SDG and its integration into the project 
<p align="justify">
A Dental Clinic Appointment contributes to several Sustainable Development Goals (SDGs). Primarily, it aligns with SDG 3: Good Health and Well-being by promoting oral health, which is integral to overall health and quality of life. Regular dental check-ups help prevent and treat oral diseases, contributing to better nutrition and communication. It also supports SDG 4: Quality Education through patient education about oral hygiene and preventive care, empowering individuals and communities to maintain good oral health. Additionally, dental clinics can incorporate sustainable practices to address SDG 12: Responsible Consumption and Production, such as using eco-friendly materials, reducing waste, and ensuring proper disposal of medical waste. Furthermore, clinics that offer affordable or inclusive services contribute to SDG 10: Reduced Inequalities by ensuring access to dental care for underserved populations. Lastly, by promoting sustainable urban planning and accessibility, dental clinics support SDG 11: Sustainable Cities and Communities, fostering a healthier and more inclusive society.
</p>

# IV. Instructions for running the program 

### * Program Start (Main Menu)
  * The program starts by displaying a main menu with options to add, cancel, view, update, or search appointments. The user selects an option by entering a number.

### * Adding an Appointment (Option 1)
  * If the user chooses to add an appointment, they are prompted to enter the patient's details (name, age, and ID), the appointment date, and time. Input validation checks ensure that the date and time are correctly formatted and not in the past. If the details pass validation and no conflicts exist, the appointment is added to the system.

### * Cancelling an Appointment (Option 2)
  * If the user selects the cancel option, the system displays all existing appointments. The user enters the Patient ID of the appointment they want to cancel. The program searches for an appointment with the given Patient ID and removes it from the list.

### * Viewing Appointments (Option 3)
  *  Selecting this option displays all scheduled appointments. If no appointments exist, a message is displayed indicating that the schedule is empty.

### * Updating an Appointment (Option 4)
  * The program allows the user to modify an existing appointment. The user is prompted to enter the Patient ID of the appointment they want to update. After finding the appointment, the system asks for updated details such as patient name, age, ID, date, and time. Validation is performed to ensure the new details are valid and do not conflict with existing appointments.

### * Searching Appointments by Date (Option 5)
  * This option lets the user view all appointments scheduled for a specific date.
The user enters the desired date, and the system displays matching appointments.

### * Exiting the System (Option 6)
  * Selecting this option ends the program with a farewell message.
    



