package org.bioplat.classifiers;

import org.assertj.core.util.Sets;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.repository.FunctionsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionTest {

    @Autowired
    private FunctionsRepository functionsRepository;

    @Test
    public void creation(){
        Set<String> genes = Sets.newHashSet();
        genes.add("tp53");
        genes.add("g23");
        ClassifierFunctionDescriptor f = new ClassifierFunctionDescriptor("yo", "classifier1", genes);
        f = functionsRepository.save(f);
        ClassifierFunctionDescriptor f2 = new ClassifierFunctionDescriptor("yo también", "classifier2", genes);
        f2 = functionsRepository.save(f2);

        Assert.assertTrue(!f.id().equals(f2.id()));

    }
}
