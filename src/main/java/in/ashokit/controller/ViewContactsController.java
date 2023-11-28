package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.dao.Contact;
import in.ashokit.service.ContactService;

@Controller
public class ViewContactsController {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/editContact")
	public String editContact(@RequestParam("cid") Integer contacId, Model model ) {
		
		Contact c = contactService.getContactById(contacId);
		model.addAttribute("contact" , c);
		return "contactInfo";
	}
	
	public String handleViewContactsLink(Model model) {
		List<Contact> contactsList = contactService.getAllContacts();
		model.addAttribute(contactsList);
		return "viewContacts";
	}

}
