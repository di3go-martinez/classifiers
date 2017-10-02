package org.bioplat.classifiers.service;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Implementacion de {@link RService} interactuando con un servidor R
 */
@Service
@Profile(Profiles.production)
public class RServiceImpl implements RService {

    @Autowired
    private RClassifier rclassifier;

    @Override
    public void create(ClassifierFunctionDescriptor f) {
        if (f.isPredefined()) {
            logger.warn("la función " + f + " está predefinida en R");
            return;
        }
        logger.info("Creating the classifier " + f);

        String result = rclassifier.create(f);
        logger.info("Registro de la función " + f + " en R: " + result);

    }


    /**
     * @param f
     * @return false siempre, permite la recreación ante la caída del server.
     */
    //FIXME evaluar como identificar si ya existe la función y es la correcta
    @Override
    public boolean isFunctionAvailable(ClassifierFunctionDescriptor f) {
        return false;
    }

    @Override
    public String eval(ClassifierFunctionDescriptor function, String mrna) {
        return rclassifier.eval(function, mrna);
    }


    private static final Logger logger = LoggerFactory.getLogger(RServiceImpl.class);
}
