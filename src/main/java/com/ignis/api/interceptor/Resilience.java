package com.ignis.api.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.interceptor.InterceptorBinding;

/**
 * Annotation para ativar a Resiliência automaticamente.
 * 
 * Quando usada, ativa o {@link ResilienceInterceptor} que aplica Timeout, Retry
 * e CircuitBreaker.
 * 
 * @author rsdelia
 */
@InterceptorBinding
@Retention(RUNTIME)
@Target({ METHOD, TYPE }) // Pode ser usada em Métodos e Classes
public @interface Resilience {
}