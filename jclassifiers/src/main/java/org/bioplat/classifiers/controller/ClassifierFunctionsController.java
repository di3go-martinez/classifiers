package org.bioplat.classifiers.controller;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.service.FunctionsService;
import org.bioplat.classifiers.service.RService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RestController
public class ClassifierFunctionsController {

    private static final Logger logger = LoggerFactory.getLogger(ClassifierFunctionsController.class);

    @Autowired
    private FunctionsService functionsService;


    @Autowired
    private RService rService;


    @GetMapping("/functions")
    public List<ClassifierFunctionDescriptor> findByAuthor(@RequestParam String author) {
        return functionsService.findByAuthor(author);
    }

    @PostMapping("/functions")
    public ClassifierFunctionDescriptor create(@RequestParam String author, @RequestParam String name,
                                               @RequestParam String expressionAsJson, @RequestParam String groupsAsJson, @RequestParam String groupLabelsAsJson) {
        ClassifierFunctionDescriptor result = functionsService.create(author, name, expressionAsJson, groupsAsJson,groupLabelsAsJson);
        rService.create(result);
        return result;
    }


    //post por el tamaño del request sino sería GET
    @PostMapping("/functions/{function}")
    public String eval(@PathVariable ClassifierFunctionDescriptor function, @RequestParam String mrna ) {
        return rService.eval(function, mrna);
    }


    private Map<String, Number> combine(List<String> thenames, List<Double> thevalues) {
        Map<String, Number> result = Maps.newHashMap();
        Iterator<String> i1 = thenames.iterator();
        Iterator<Double> i2 = thevalues.iterator();
        while (i1.hasNext() || i2.hasNext()) result.put(i1.next(), i2.next());

        return result;
    }

    private ArrayList<String> getThenames(String names) {
        return Lists.newArrayList(Splitter.on(",").trimResults().split(names));
    }

    private List<Double> getTheValues(String values) {
        final List<Double> thevalues = Lists.newArrayList();
        Splitter.on(",").trimResults().split(values).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                thevalues.add(Double.valueOf(s));
            }
        });
        return thevalues;
    }
}
