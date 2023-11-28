package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.dao.Contact;
import in.ashokit.entity.ContactEntity;
import in.ashokit.service.ContactService;

@Controller
public class ContactInfoController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping(value = {"/", "/addContact"})
	public String loadForm(Model model) {
		
		Contact c = new Contact();
		model.addAttribute("contact", c);
		return "contactInfo";
	}
	
	@PostMapping("/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact") Contact c, Model model) {
		
		boolean isSaved = contactService.saveContact(c);
		if(isSaved) {
			model.addAttribute("succMsg","Contact Saved");
		}else {
			model.addAttribute("errMsg","failed To save Contact");
		}
		
		return "contactInfo";
	}
	
	@GetMapping("/viewContacts")
	public String handleViewContactsLink(Model model) {
	
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contacts", contactList);
		
		return "viewContact";
	}
	
	@RequestMapping("/deleteContact")
	public String deleteContact(@RequestParam("cid") Integer cid) {
		boolean isDeleted = contactService.deletedContact(cid);
		if(isDeleted) {
			return "redirect:/viewContacts";
		}
		return null;
	}

}
