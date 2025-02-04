package com.ignis.api;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain // IMPORTANTE: Indica o ponto de entrada da aplicação para Quarkus Native
public class MainApplication {
    public static void main(String[] args) {
	Quarkus.run(args); // Inicia a aplicação Quarkus
    }
}
