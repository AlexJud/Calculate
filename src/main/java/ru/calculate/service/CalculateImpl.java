package ru.calculate.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.context.request.async.DeferredResult;
import ru.calculate.model.Expression;
import indev.test.job.calc.ICalc;
import indev.test.job.calc.ThreadCalc;
import org.springframework.stereotype.Component;
import ru.calculate.model.TypeOperation;

import java.util.concurrent.*;


@Log4j2
@Component
public class CalculateImpl implements Calculate {

    private static ExecutorService service = Executors.newSingleThreadExecutor();



    public DeferredResult<Expression> getResultAsync(Expression expression, TypeOperation type) {
        DeferredResult<Expression> deferredResult = new DeferredResult<>();

        CompletableFuture.supplyAsync(() -> {
            log.trace("case operation " + expression.toString() + " type: " + type);
            ICalc calc = ThreadCalc.Instance();
            try {
                switch (type) {
                    case ADD:
                        expression.setResult(calc.Add(expression.getA(), expression.getB()));
                        break;
                    case MUL: ;
                        expression.setResult(calc.Multiply(expression.getA(), expression.getB()));
                        break;
                    case DIV:
                        expression.setResult(calc.Divide(expression.getA(), expression.getB()));
                        break;
                    case SUB:
                        expression.setResult(expression.getA() - expression.getB());
                        break;
                    default:
                        log.info("case DEFAULT operation " + expression.toString()+ " type: " + type);
                }
            } catch (Exception e) {
                log.warn("error " + expression + " type " + type + " message: " + e.getMessage());
            }
            return expression;
        }, service)
                .whenCompleteAsync((result, throwable) -> {
                    if (throwable != null) {
                        log.warn("Wrong result " + expression + " type " + type + " throwable " + throwable.getMessage());
                    }
                    log.info("Result operation: "+ expression.toString());
                    deferredResult.setResult(expression);
                });

        return deferredResult;
    }


   /* public Expression getResult(Expression expression, TypeOperation type) {

        Callable<Double> task = () -> {
            log.trace("case operation " + expression.toString() + " type: " + type);
            ICalc calc = ThreadCalc.Instance();
            try {
                switch (type) {
                    case ADD:
                        return calc.Add(expression.getA(), expression.getB());
                    case MUL:
                        return calc.Multiply(expression.getA(), expression.getB());
                    case DIV:
                        return calc.Divide(expression.getA(), expression.getB());
                    case SUB:
                        return expression.getA() - expression.getB();
                    default:
                        log.info("case DEFAULT operation " + expression.toString()+ " type: " + type);
                }
            } catch (Exception e) {
                log.warn("Exception " + e.getMessage());
            }
            return 0D;
        };
        try {
            expression.setResult(service.submit(task).get());
        } catch (Exception e) {
            log.warn("Exception " + e.getMessage());
        }
        return expression;
    }*/

}
