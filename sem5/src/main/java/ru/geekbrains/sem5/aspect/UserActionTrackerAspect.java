package ru.geekbrains.sem5.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Класс для отслеживания действий пользователя
 */
@Aspect
@Component
public class UserActionTrackerAspect {
    /**
     * Метод выводит сообщение перед вызовом метода в сервисе с параметрами метода
     * @param joinPoint точка подключение - вызываемый метод
     */
    @Before("@annotation(ru.geekbrains.sem5.annotation.TrackUserAction)")
    @Order(1)
    public void trackUserAction(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Вызван метод: " + methodName);
        for (Object arg : args) {
            System.out.println("Параметр: " + arg);
        }
    }

    /**
     * Метод подсчета длительноси выполнения вызываемого метода
     * @param joinPoint точка подключение - вызываемый метод
     * @return результат выполнения вызываемого метода
     * @throws Throwable
     */
    @Around("@annotation(ru.geekbrains.sem5.annotation.TrackUserAction)")
    @Order(2)
    public Object timeUserAction(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Метод " + joinPoint.getSignature() + " выполнялся " +
                (endTime - startTime) + " миллисекунд.");

        return result;
    }

    /**
     * Метод сообщает об окончании выполнения вызываемого метода
     */
    @After("@annotation(ru.geekbrains.sem5.annotation.TrackUserAction)")
    @Order(3)
    public void endUserActions() {
        System.out.println("Метод завершен!");
    }
}
