package com.edubridge.contact_app.service;

import java.util.List;

import com.edubridge.contact_app.dto.Contact;

public interface ContactServiceI {
	int addContact(Contact contact);
	List<Contact> getAllContacts();
	Contact getContactByName(String name);
	int updateContact(Contact contact);
	int deleteContactByName(String name);
}
