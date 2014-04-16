package net.onlineconsultations.controller.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllersInterceptor {
    private static final Logger log = Logger.getLogger(ControllersInterceptor.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(Exception e) {
        log.error("Default exception handler: " + e);

        return new ModelAndView("error", "reason", "Unexpected internal error");
    }

}
