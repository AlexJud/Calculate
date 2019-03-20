package ru.calculate.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.async.DeferredResult;
import ru.calculate.model.Expression;
import ru.calculate.model.TypeOperation;
import ru.calculate.service.Calculate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.calculate.Utils.TypeOperationConverter;

@Log4j2
@RestController
public class RootController {

    @Autowired
    private Calculate calculate;

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(TypeOperation.class, new TypeOperationConverter());
    }

    @PostMapping(value = "/{type}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult calculate(@PathVariable(value = "type") TypeOperation type, @RequestBody Expression expression) {
        log.info("new request " + expression.toString() + " type: " + type);
        return calculate.getResultAsync(expression, type);
    }

}
