package com.edubridge.contact_app.service;

import java.util.List;

import com.edubridge.contact_app.dao.ContactDao;
import com.edubridge.contact_app.dao.ContactDaoI;
import com.edubridge.contact_app.dto.Contact;

public class ContactService implements ContactServiceI {
	private ContactDaoI dao = new ContactDao(); 
	
	@Override
	public int addContact(Contact contact) {
		return dao.addContact(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return dao.getAllContacts();
	}

	@Override
	public Contact getContactByName(String name) {
		return dao.getContactByName(name);
	}

	@Override
	public int updateContact(Contact contact) {
		return dao.updateContact(contact);
	}

	@Override
	public int deleteContactByName(String name) {
		return dao.deleteContactByName(name);
	}
}
