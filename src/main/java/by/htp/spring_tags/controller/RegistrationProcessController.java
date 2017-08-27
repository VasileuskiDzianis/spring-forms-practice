package by.htp.spring_tags.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.service.skill.SkillService;
import by.htp.spring_tags.service.user.UserService;

@Controller
public class RegistrationProcessController {

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
	public String confirmRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Locale locale,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			if (isNoOneSkillSelected(user)) {
				bindingResult.addError(new FieldError("user", "skills", "one skill at least"));
			}
			
			int[] chosenSkills = getChosenSkills(user);
			List<Skill> availableSkills = skillService.findAllSkills();
			model.addAttribute("skills", availableSkills);
			model.addAttribute("countries", countries);
			model.addAttribute("chosenSkills", chosenSkills);
			
			return "registration";
		}
		removeNotSelectedSkills(user.getSkills());
		user.setLocale(locale.getLanguage());
		userService.saveUser(user);
		
		model.addAttribute("storedUser", userService.findUserById(user.getId()));
		
		return "confirmation";
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirmGet() {

		return "redirect:register";
	}

	private void removeNotSelectedSkills(List<Skill> skills) {
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getId() == 0) {
				skills.remove(i);
				i--;
			}
		}
	}
	
	private boolean isNoOneSkillSelected(User user) {
		List<Skill> selectedSkills = user.getSkills();
		int numberOfSelectedSkills = 0;

		for (Skill skill : selectedSkills) {
			if (skill.getId() > 0) {
				numberOfSelectedSkills++;
			}
		}
		if (numberOfSelectedSkills == 0) {

			return true;
		}
		return false;
	}

	private int[] getChosenSkills(User user) {
		List<Skill> skills = user.getSkills();
		int[] chosenSkills = new int[skills.size()];
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getId() != 0) {
				chosenSkills[i] = 1;
			}
		}
		return chosenSkills;
	}
}
