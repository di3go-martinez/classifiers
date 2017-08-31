package org.bioplat.classifiers;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.service.FunctionsService;
import org.bioplat.classifiers.service.RService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("production")
public class SynchronizeClassifiers {

    @Autowired
    private FunctionsService functions;

    @Autowired
    private RService rService;


    private static final Logger logger = LoggerFactory.getLogger(SynchronizeClassifiers.class);


    @Bean
    public Void run() throws Exception {
        logger.info("Chequeando sincronización de funciones");
        for (ClassifierFunctionDescriptor fg : functions.findAll())
            if (!rService.isFunctionAvailable(fg))
                rService.create(fg);
        return null;
    }
}
