package org.example.crud.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Произошла ошибка: " + exception.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ModelAndView handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not-found");
        modelAndView.addObject("errorMessage", "Страница не найдена: " + ex.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView handleBadRequestException(BadRequestException exception) {
        ModelAndView modelAndView = new ModelAndView("bad-request");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }
}
