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
import by.htp.spring_tags.domain.UserStatus;
import by.htp.spring_tags.service.skill.SkillService;
import by.htp.spring_tags.service.user.UserService;

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
		List<Skill> skills = skillService.findAllSkills();

		model.addAttribute("countries", countries);
		model.addAttribute("skills", skills);
		model.addAttribute("user", user);

		return "registration";
	}

	@RequestMapping(value = { "/registration", "/details/id{userId}" }, method = RequestMethod.POST)
	public String confirmUserDataStoring(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			Locale locale, Model model) {

		if (bindingResult.hasErrors()) {
			if (isNoOneSkillSelected(user)) {
				bindingResult.addError(new FieldError("user", "skills", "one skill at least"));
			}


			if (user.getId() != 0) {
				Integer selectedSkillsNumber;
			
				List<Skill> chosenSkills = user.getSkills();
				removeNotSelectedSkills(chosenSkills);
				List<Skill> allSkills = skillService.findAllSkills();

				selectedSkillsNumber = chosenSkills.size();

				allSkills.removeAll(chosenSkills);
				chosenSkills.addAll(allSkills);

			
				model.addAttribute("selectedSkillsNumber", selectedSkillsNumber);
				
				return "details";
			} else {
				int[] chosenSkills = getChosenSkills(user);

				model.addAttribute("skills", skillService.findAllSkills());
				model.addAttribute("countries", countries);
				model.addAttribute("chosenSkills", chosenSkills);
				return "registration";
			}
		}
		removeNotSelectedSkills(user.getSkills());
		user.setLocale(locale.getLanguage());
		user.setStatus(UserStatus.ACTIVE);
		userService.saveUser(user);

		model.addAttribute("storedUser", userService.findUserById(user.getId()));

		return "confirmation";
	}

	@RequestMapping(value = "/registration/confirmation", method = RequestMethod.GET)
	public String confirmGet() {

		return "redirect:registration";
	}

	@RequestMapping(path = "/details/id{userId}", method = RequestMethod.GET)
	public String showDetails(Model model, @PathVariable int userId) {
		Integer selectedSkillsNumber;
		User user = userService.findUserById(userId);
		List<Skill> userSkills = user.getSkills();
		List<Skill> allSkills = skillService.findAllSkills();

		selectedSkillsNumber = userSkills.size();

		allSkills.removeAll(userSkills);
		userSkills.addAll(allSkills);

		model.addAttribute("user", user);
		model.addAttribute("selectedSkillsNumber", selectedSkillsNumber);

		return "details";
	}
	
	@RequestMapping(path = "/deletion", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("userId") int userId) {
		userService.disableUser(userService.findUserById(userId));

		return "redirect:/users";
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
