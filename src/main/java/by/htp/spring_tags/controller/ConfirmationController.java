package by.htp.spring_tags.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.service.user.UserService;

@Controller
public class ConfirmationController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(@ModelAttribute("user") User user, Locale locale, Model model) {
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
