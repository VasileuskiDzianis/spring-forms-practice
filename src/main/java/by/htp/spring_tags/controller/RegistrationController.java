package by.htp.spring_tags.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.service.skill.SkillService;

@Controller
public class RegistrationController {
	@Autowired
	SkillService skillService;
	
	@Value("#{countries}")
	Map<String,String> countries;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		User user = new User();
		Address address = new Address();
		List<Skill> skills = skillService.getAllSkills();
		user.setAddress(address);
		user.setSkills(skills);
		
		model.addAttribute("countries", countries);
		model.addAttribute("user", user);
		
		return "registration";
	}
}
