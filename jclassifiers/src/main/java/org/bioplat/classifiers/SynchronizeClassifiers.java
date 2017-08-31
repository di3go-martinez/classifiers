package org.bioplat.classifiers;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.service.FunctionsService;
import org.bioplat.classifiers.service.RService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class SynchronizeClassifiers implements ApplicationRunner {

    @Autowired
    private FunctionsService functions;

    @Autowired
    private RService rService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Chequeando sincronización de funciones");
        for (ClassifierFunctionDescriptor fg : functions.findAll())
            if (!rService.isFunctionAvailable(fg))
                rService.create(fg);
    }

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeClassifiers.class);
}
