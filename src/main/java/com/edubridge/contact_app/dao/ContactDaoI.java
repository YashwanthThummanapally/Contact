package com.edubridge.contact_app.dao;

import java.util.List;

import com.edubridge.contact_app.dto.Contact;

public interface ContactDaoI {
	int addContact(Contact contact);
	List<Contact> getAllContacts();
	Contact getContactByName(String name);
	int updateContact(Contact contact);
	int deleteContactByName(String name);
}
