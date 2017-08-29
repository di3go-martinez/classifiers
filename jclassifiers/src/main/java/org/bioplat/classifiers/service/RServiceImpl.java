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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Implementacion de {@link RService} interactuando con un servidor R
 */
@Service
public class RServiceImpl implements RService {

    @Override
    public void create(ClassifierFunctionDescriptor f) {
        if (f.isPredefined()) {
            logger.warn("la función " + f + " está predefinida en R");
            return;
        }
        //hace la llamada rest a r que crea una funcion y la expone dinámicamente
        try {
            Content content = Request.Post(urlRservice() + "/functions")
                    .body(new UrlEncodedFormEntity(prepareCreationParams(f)))
                    .execute()
                    .returnContent();
            logger.info("Registro de la función " + f + " en R: " + content.asString());
        } catch (Exception e) {
            throw new RuntimeException("Error registrando la función en R", e);
        }
    }

    private String urlRservice() {
        return System.getProperty("rservice.url", "http://localhost:8000");
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
        try {
            Content requestresult = Request.Post(urlRservice() + "/" + function.resourceId())
                    .body(prepareEvaluationParams(function, mrna))
                    .execute().returnContent();
            String result = "Resultado de la invocación a la función " + function.id() + ": " + requestresult.asString();
            logger.info(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Error invocando a la función " + function, e);
        }

    }

    private HttpEntity prepareEvaluationParams(ClassifierFunctionDescriptor f, String mrna) throws UnsupportedEncodingException {
        List<BasicNameValuePair> list = Lists.newArrayList();
        list.add(new BasicNameValuePair("id", f.id().toString()));
        list.add(new BasicNameValuePair("mrna", mrna));
        return new UrlEncodedFormEntity(list);
    }



    private List<? extends NameValuePair> prepareCreationParams(ClassifierFunctionDescriptor f) {
        List<NameValuePair> result = Lists.newArrayList();
        result.add(new BasicNameValuePair("id", f.id().toString()));
        //result.add(new BasicNameValuePair("author", f.author()));

        result.add(new BasicNameValuePair("expressionAsJson", f.expressionAsJson()));
        result.add(new BasicNameValuePair("groupsAsJson", f.groupsAsJson()));
        result.add(new BasicNameValuePair("groupLabelsAsJson", f.groupLabelsAsJson()));
        return result;
    }

    private static final Logger logger = LoggerFactory.getLogger(RServiceImpl.class);
}
