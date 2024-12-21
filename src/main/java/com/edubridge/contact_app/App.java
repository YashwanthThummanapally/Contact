package com.edubridge.contact_app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.edubridge.contact_app.dto.Contact;
import com.edubridge.contact_app.service.ContactService;
import com.edubridge.contact_app.service.ContactServiceI;
import com.edubridge.contact_app.util.DBUtil;

public class App {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ContactServiceI service = new ContactService();

		while (true) {
			System.out.println("===================");
			System.out.println("CONTACT APPLICATION");
			System.out.println("===================");
			System.out.println("1. Add Contact");
			System.out.println("2. View Contacts");
			System.out.println("3. Search Contact");
			System.out.println("4. Update Contact");
			System.out.println("5. Delete Contact");
			System.out.println("0. Exit");

			System.out.println("Please choose one option :");
			int option = in.nextInt();

			switch (option) {
			case 1:
				System.out.println("Enter contact name :");
				in.nextLine();
				String name = in.nextLine();

				System.out.println("Enter contact email :");
				String email = in.next();

				System.out.println("Enter contact mobile number :");
				long mobile = 0L;
				try {
					mobile = in.nextLong();
				} catch(InputMismatchException e) {
					System.out.println("Please provide valid mobile number");
				}				

				Contact contact = new Contact();
				contact.setContactName(name);
				contact.setEmailId(email);
				contact.setMobileNumber(mobile);

				int status = service.addContact(contact);
				if (status >= 1) {
					System.out.println("New contact saved!");
				} else {
					System.out.println("Failed!");
				}

				break;
			case 2:
				List<Contact> contacts = service.getAllContacts();
				
				if(contacts.size() == 0) {
					System.out.println("No contacts available");
					break;
				}

				for (Contact contactPerson : contacts) {
					System.out.println(contactPerson.getContactId() + "\t" + contactPerson.getContactName() + "\t" + contactPerson.getEmailId() + "\t" + contactPerson.getMobileNumber());
				}

				break;
			case 3:
				System.out.println("Enter contact name :");
				in.nextLine();
				name = in.nextLine();
				
				contact = service.getContactByName(name);
				if (contact != null) {
					System.out.println(
							contact.getContactName() + "\t" + contact.getEmailId() + "\t" + contact.getMobileNumber());
				} else {
					System.out.println("No contact available");
				}
				break;
			case 4:
				System.out.println("Enter contact name :");
				in.nextLine();
				name = in.nextLine();

				System.out.println("Enter updated email :");
				email = in.next();

				System.out.println("Enter updated mobile :");
				mobile = 0L;
				try {
					mobile = in.nextLong();
				}catch(InputMismatchException e) {
					System.out.println("Please provide valid mobile number");
				}

				contact = new Contact();
				contact.setContactName(name);
				contact.setEmailId(email);
				contact.setMobileNumber(mobile);

				status = service.updateContact(contact);
				if (status >= 1) {
					System.out.println("Contact info updated");
				} else {
					System.out.println("Cannot update");
				}
				break;
			case 5:
				System.out.println("Enter contact name :");
				in.nextLine();
				name = in.nextLine();

				status = service.deleteContactByName(name);
				if (status >= 1) {
					System.out.println("Contact deleted");
				} else {
					System.out.println("Cannot delete!");
				}
				break;
			case 0:
				System.out.println("Bye!!");

				in.close();
				DBUtil.closeConnection();

				System.exit(0);
			default:
				System.out.println("Invalid option!");
				break;
			}
		}
	}
}
