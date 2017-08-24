package by.htp.spring_tags.controller.error;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	private static final Logger LOGGER = Logger.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler
	public String handle(Exception e, Model model) {

		LOGGER.error("Exception occur, ", e);
		
		model.addAttribute("message", "Oops. Internal error occured...");

		e.printStackTrace();

		return "message";
	}
}
