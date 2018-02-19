package org.bioplat.classifiers.service;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;

/**
 * Accede al servidor R, crendo y evaluando las funciones clasificadores en
 */
public interface RService {

    /**
     * Crea y publica una función
     * @param f
     */
    void create(ClassifierFunctionDescriptor f);

    /**
     *
     * @return chequea si la función f está correctamnte generada
     */
    boolean isFunctionAvailable(ClassifierFunctionDescriptor f);

    /**
     *
     * evalua la función con los parámetros mrna (json)
     */
    String eval(ClassifierFunctionDescriptor function, String mrna);
}
