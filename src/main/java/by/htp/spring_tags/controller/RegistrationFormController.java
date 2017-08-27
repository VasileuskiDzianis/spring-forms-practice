package by.htp.spring_tags.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.service.skill.SkillService;

@Controller
public class RegistrationFormController {
	@Autowired
	SkillService skillService;
	
	@Value("#{countries}")
	Map<String,String> countries;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Locale locale, Model model) {
		User user = new User();
		List<Skill> skills = skillService.findAllSkills();

		model.addAttribute("countries", countries);
		model.addAttribute("skills", skills);
		model.addAttribute("user", user);
		
		return "registration";
	}
}
