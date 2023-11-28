package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dao.Contact;
import in.ashokit.entity.ContactEntity;
import in.ashokit.exception.PhoneBookException;
import in.ashokit.repository.ContactDtlsRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactDtlsRepository contactRepo;

	@Override
	public boolean saveContact(Contact c) {
		boolean isSaved = false;
		
		/*if(c.getContactId()!=null) {
			Optional<ContactEntity> findById = contactRepo.findById(entity);
			entity = findById.get();		}
		*/
		try {
			//int i=10/0;
			ContactEntity entity = new ContactEntity();
			BeanUtils.copyProperties(c, entity);
			ContactEntity saveEntity = contactRepo.save(entity);
			if(saveEntity.getContactId()!=null) {
				isSaved=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PhoneBookException("Save Failed");
		}
		
		
		return isSaved;
	}

	@Override
	public List<Contact> getAllContacts() {
		List<ContactEntity> entities = contactRepo.findAll();
		List<Contact> contacts = new ArrayList<Contact>();
		// legacy approach
		/*for(ContactEntity entity:entities) {
			Contact contact = new Contact();
			BeanUtils.copyProperties(entity, contact);
			contacts.add(contact);
		}*/
		// Java 8 approach
		return entities.stream().map(entity ->{
			Contact contact = new Contact();
			BeanUtils.copyProperties(entity, contact);
			return contact;
		}).collect(Collectors.toList());
		
	}

	@Override
	public Contact getContactById(Integer cid) {
		
		Optional<ContactEntity> findById = contactRepo.findById(cid);
		if(findById.isPresent()) {
			ContactEntity entity = findById.get();
			Contact c = new Contact();
			BeanUtils.copyProperties(entity, c);
			return c;
		}
		return null;
	}

	@Override
	public boolean updateContact(Contact c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletedContact(Integer cid) {
		contactRepo.deleteById(cid);
		return true;
	}

}
