package by.htp.task2.controller.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	private static final Logger LOGGER = LogManager.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler
	public String handle(Exception e, Model model) {
		
		LOGGER.error("Exception occured, ", e);
		
		model.addAttribute("message", "Oops. Internal error occured...");

		e.printStackTrace();

		return "message";
	}
}
