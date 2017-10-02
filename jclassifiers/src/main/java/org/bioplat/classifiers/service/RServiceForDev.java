package org.bioplat.classifiers.service;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(Profiles.development)
public class RServiceForDev implements RService {

    private static final Logger logger = LoggerFactory.getLogger(RService.class);

    @Override
    public void create(ClassifierFunctionDescriptor f) {
        logger.info("Creando clasificador en R: " + f);
        if (f.name().startsWith("error"))
            throw new RuntimeException();
    }

    @Override
    public boolean isFunctionAvailable(ClassifierFunctionDescriptor f) {
        return false;
    }

    @Override
    public String eval(ClassifierFunctionDescriptor function, String mrna) {
        logger.info("Se evalúa la función " + function + " (" + mrna + ") => 'ok'");
        return "ok";
    }
}
