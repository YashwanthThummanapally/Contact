package com.edubridge.contact_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.contact_app.dto.Contact;
import com.edubridge.contact_app.util.DBUtil;

public class ContactDao implements ContactDaoI {
	private static Connection con;

	static {
		con = DBUtil.getConnection();
	}
	
	@Override
	public int addContact(Contact contact) {
		String sql = "insert into contact(name, email, mobile) values(?,?,?)";
		int status = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contact.getContactName());
			ps.setString(2, contact.getEmailId());
			ps.setLong(3, contact.getMobileNumber());
			
			status = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public List<Contact> getAllContacts() {
		String sql = "select * from contact";
		List<Contact> contacts = new ArrayList<Contact>();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setContactId(rs.getInt("id"));
				contact.setContactName(rs.getString("name"));
				contact.setEmailId(rs.getString("email"));
				contact.setMobileNumber(rs.getLong("mobile"));
				
				contacts.add(contact);
			}			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}

	@Override
	public Contact getContactByName(String name) {
		String sql = "select * from contact where name=?";
		Contact contact = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("id"));
				contact.setContactName(rs.getString("name"));
				contact.setEmailId(rs.getString("email"));
				contact.setMobileNumber(rs.getLong("mobile"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return contact;
	}

	@Override
	public int updateContact(Contact contact) {
		String sql = "update contact set email=?, mobile=? where id=?";
		int status = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contact.getEmailId());
			ps.setString(2, contact.getEmailId());
			ps.setLong(3, contact.getMobileNumber());
			status = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public int deleteContactByName(String name) {
		String sql = "delete from contact where name=?";
		int status = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			status = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
}
