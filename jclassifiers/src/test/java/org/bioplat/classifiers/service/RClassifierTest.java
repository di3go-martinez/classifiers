package org.bioplat.classifiers.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.http.NameValuePair;
import org.bioplat.classifiers.AbstractTest;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;

;import static org.mockito.Mockito.when;

public class RClassifierTest extends AbstractTest {

    @Autowired
    private RClassifier rClassifier;

    @Test
    public void checkCreationApi() {

        ClassifierFunctionDescriptor classifierFunctionDescriptor =
                Mockito.mock(ClassifierFunctionDescriptor.class);

        when(classifierFunctionDescriptor.id()).thenReturn(1L);
        when(classifierFunctionDescriptor.name()).thenReturn("class1test");
        when(classifierFunctionDescriptor.expressionAsJson()).thenReturn("expresions");
        when(classifierFunctionDescriptor.author()).thenReturn("diego");
        when(classifierFunctionDescriptor.groupLabelsAsJson()).thenReturn("groups");
        when(classifierFunctionDescriptor.groupsAsJson()).thenReturn("groups");


        //setid(classifierFunctionDescriptor, 1L);
        List<? extends org.apache.http.NameValuePair> params = rClassifier.prepareCreationParams(classifierFunctionDescriptor);

        isPresent(params, "author");
        isPresent(params, "id");
        isPresent(params, "expressionAsJson");
        isPresent(params, "groupsAsJson");
        isPresent(params, "groupLabelsAsJson");
    }

    //TODO hacer mock
    private void setid(ClassifierFunctionDescriptor classifierFunctionDescriptor, long id) {
        try {
            Field idfield = ClassifierFunctionDescriptor.class.getField("id");
            idfield.setAccessible(true);
            idfield.set(classifierFunctionDescriptor, id);
        } catch (NoSuchFieldException e) {
            Assert.fail();
        } catch (IllegalAccessException e) {
            Assert.fail();
        }
    }

    private void isPresent(List<? extends org.apache.http.NameValuePair> params, String key) {
        boolean present = Iterables.tryFind(params, paramCondition(key)).isPresent();
        if (!present)
            Assert.fail("Falta el parámetro " + key);
    }

    private Predicate<NameValuePair> paramCondition(String key) {
        return input -> input.getName().equals(key);
    }


}
