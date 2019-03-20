package ru.calculate.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.async.DeferredResult;
import ru.calculate.CalculateApplication;
import ru.calculate.model.Expression;
import ru.calculate.model.TypeOperation;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculateApplication.class)
public class CalculateImplTest {

    @Autowired
    private Calculate calculate = new CalculateImpl();

    @Test
    public void getDivAsync() throws InterruptedException {
        DeferredResult result = calculate.getResultAsync(new Expression(100, 5), TypeOperation.DIV);
        while(!result.hasResult()){
            Thread.sleep(10);
        }
        Assert.assertNotNull(result.getResult());
        Assert.assertEquals(Expression.class,result.getResult().getClass());
        Expression expression = (Expression) result.getResult();
        Assert.assertEquals(20,expression.getResult(),0.1);
    }
    @Test
    public void getAddAsync() throws InterruptedException {
        DeferredResult result = calculate.getResultAsync(new Expression(100, 25), TypeOperation.ADD);
        while(!result.hasResult()){
            Thread.sleep(10);
        }
        Assert.assertNotNull(result.getResult());
        Assert.assertEquals(Expression.class,result.getResult().getClass());
        Expression expression = (Expression) result.getResult();
        Assert.assertEquals(125,expression.getResult(),0.1);
    }
    @Test
    public void getSubAsync() throws InterruptedException {
        DeferredResult result = calculate.getResultAsync(new Expression(100, 25), TypeOperation.SUB);
        while(!result.hasResult()){
            Thread.sleep(10);
        }
        Assert.assertNotNull(result.getResult());
        Assert.assertEquals(Expression.class,result.getResult().getClass());
        Expression expression = (Expression) result.getResult();
        Assert.assertEquals(75,expression.getResult(),0.1);
    }
    @Test
    public void getMulAsync() throws InterruptedException {
        DeferredResult result = calculate.getResultAsync(new Expression(100, 3), TypeOperation.MUL);
        while(!result.hasResult()){
            Thread.sleep(10);
        }
        Assert.assertNotNull(result.getResult());
        Assert.assertEquals(Expression.class,result.getResult().getClass());
        Expression expression = (Expression) result.getResult();
        Assert.assertEquals(300,expression.getResult(),0.1);
    }
}