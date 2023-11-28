package in.ashokit.service;

import java.util.List;

import in.ashokit.dao.Contact;
import in.ashokit.entity.ContactEntity;

public interface ContactService {
	
	boolean saveContact(Contact c);
	
	List<Contact> getAllContacts();
	
	Contact getContactById(Integer cid);
	
	boolean updateContact(Contact c);
	
	boolean deletedContact(Integer cid);
	
	
}
