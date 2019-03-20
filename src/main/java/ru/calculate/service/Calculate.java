package ru.calculate.service;

import org.springframework.web.context.request.async.DeferredResult;
import ru.calculate.model.Expression;
import org.springframework.stereotype.Service;
import ru.calculate.model.TypeOperation;

@Service
public interface Calculate {
//    Expression getResult(Expression expression, TypeOperation type);
    DeferredResult<Expression> getResultAsync(Expression expression, TypeOperation type);
}
