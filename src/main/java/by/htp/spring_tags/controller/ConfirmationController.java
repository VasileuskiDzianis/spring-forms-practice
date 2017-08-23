package by.htp.spring_tags.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.service.skill.SkillService;
import by.htp.spring_tags.service.user.UserService;

@Controller
public class ConfirmationController {

	@Autowired
	UserService userService;

	@Autowired
	SkillService skillService;

	@Value("#{countries}")
	Map<String, String> countries;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Locale locale,
			Model model) {

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("countries", countries);
			
			return "registration";
		}

		List<Skill> skills = user.getSkills();

		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getId() == 0) {
				skills.remove(i);
			}
		}
		user.setLocale(locale);
		userService.saveUserAndSetId(user);
		User storedUser = userService.findUserById(user.getId());

		model.addAttribute("storedUser", storedUser);

		return "confirmation";
	}
}
