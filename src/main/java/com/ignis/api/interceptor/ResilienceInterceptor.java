package com.ignis.api.interceptor;

import java.time.temporal.ChronoUnit;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

/**
 * Interceptor para resiliência automática usando MicroProfile Fault Tolerance.
 * 
 * Aplica Timeout, Retry e CircuitBreaker em todos os métodos anotados com
 * {@link Resilience}.
 * 
 * @author rsdelia
 */
@Resilience // O Interceptor será ativado para classes/métodos anotados com @Resilience
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class ResilienceInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
	return applyResilience(context);
    }

    /**
     * Aplica regras de Resiliência (Timeout, Retry, CircuitBreaker) dinamicamente.
     * 
     * @param context O contexto da invocação do método.
     * @return O resultado da execução original ou uma exceção tratada.
     * @throws Exception Se houver erro durante a execução.
     */
    @Timeout(value = 3, unit = ChronoUnit.SECONDS) // Timeout de 3 segundos
    @Retry(maxRetries = 3, delay = 500) // Retry: Tenta 3 vezes com delay de 500ms
    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.5, delay = 10000) // Circuit Breaker ativado após 50%
										   // de falhas
    public Object applyResilience(InvocationContext context) throws Exception {
	return context.proceed();
    }
}