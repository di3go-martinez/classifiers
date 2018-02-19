package org.bioplat.classifiers;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.service.RService;
import org.junit.Ignore;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Created by diego on 17/02/18.
 * Modelar primero!!
 */
@Ignore
public class EvaluationTests extends AbstractTest {

    @MockBean
    @Autowired
    private RService rservice;

    public void test_evaluation(){
        Mockito.when(rservice.eval(Mockito.any(ClassifierFunctionDescriptor.class), Mockito.anyString()))
            .thenThrow( new RuntimeException());

        String mrna = "";
        ClassifierFunctionDescriptor fd = Mockito.mock(ClassifierFunctionDescriptor.class);
        rservice.eval(fd, mrna);

    }
}
