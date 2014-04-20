package net.onlineconsultations.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllersInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ControllersInterceptor.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(Exception e) {
        log.error("Unhandled exception", e);

        return new ModelAndView("error", "reason", "Unexpected internal error");
    }

}
