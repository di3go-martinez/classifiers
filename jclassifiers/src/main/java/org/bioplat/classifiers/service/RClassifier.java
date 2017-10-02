package org.bioplat.classifiers.service;

import com.google.common.annotations.Beta;
import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Realiza las invocaciones HTTP API al servicio de R (rclassifiers)
 */
@Service
@Beta
public class RClassifier {

    /**
     * hace la llamada rest a r que crea una funcion y la expone dinámicamente
     *
     * @param f
     * @return
     */
    public String create(ClassifierFunctionDescriptor f) {
        try {
            Content content = Request.Post(urlRservice() + "/functions")
                    .body(new UrlEncodedFormEntity(prepareCreationParams(f)))
                    .execute()
                    .returnContent();
            return content.asString();
        } catch (Exception e) {
            throw new RuntimeException("Error registrando la función en R", e);
        }
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


    private String urlRservice() {
        return System.getProperty("rservice.url", "http://localhost:8000");
    }

    public String eval(ClassifierFunctionDescriptor function, String mrna) {
        try {
            Content requestresult = Request.Post(urlRservice() + "/" + function.resourceId())
                    .body(prepareEvaluationParams(function, mrna))
                    .execute().returnContent();
            String result = "Resultado de la invocación a la función " + function.name() + ": " + requestresult.asString();
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

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RClassifier.class);
}
