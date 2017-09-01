package by.htp.task2.controller;

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

import by.htp.task2.domain.User;
import by.htp.task2.domain.UserStatus;
import by.htp.task2.service.skill.SkillService;
import by.htp.task2.service.user.UserService;

@Controller
@RequestMapping("/users")
public class UserOperationController {

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

	@RequestMapping(method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		model.addAttribute("users", userService.findAllByStatus(UserStatus.ACTIVE));

		return "users";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(Locale locale, Model model) {
		User user = new User();
		user.setSkills(skillService.findAllSkills());

		model.addAttribute("countries", countries);
		model.addAttribute("user", user);

		return "registration";
	}

	@RequestMapping(value = { "/registration", "/details/id{userId}" }, method = RequestMethod.POST)
	public String confirmUserDataStoring(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			Locale locale, Model model) {

		skillService.removeSkillsWithZeroId(user.getSkills());

		if (skillService.isNoOneSkillSelected(user.getSkills())) {
			bindingResult.addError(new FieldError("user", "skills", "one skill at least"));
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("selectedSkillsNumber", user.getSkills().size());

			skillService.appendAvailableSkills(user.getSkills());
			
			model.addAttribute("countries", countries);

			if (userService.isUserRegistered(user)) {

				return "details";
			} else {

				return "registration";
			}
		}
		user.setLocale(locale.getLanguage());
		user.setStatus(UserStatus.ACTIVE);
		userService.saveUser(user);

		model.addAttribute("storedUser", userService.findUserById(user.getId()));

		return "confirmation";
	}

	@RequestMapping(path = "/details/id{userId}", method = RequestMethod.GET)
	public String showDetails(Model model, @PathVariable int userId) {
		User storedUser = userService.findUserById(userId);
		
		model.addAttribute("selectedSkillsNumber", storedUser.getSkills().size());
		
		skillService.appendAvailableSkills(storedUser.getSkills());

		model.addAttribute("user", storedUser);

		return "details";
	}

	@RequestMapping(path = "/deletion", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("delUserId") int userId, Model model) {
		User user = userService.findUserById(userId);
		model.addAttribute("deletedUser", user.getLogin());
		
		userService.disableUser(user);

		model.addAttribute("users", userService.findAllByStatus(UserStatus.ACTIVE));
		
		return "users";
	}
}
