package org.bioplat.classifiers.controller;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.bioplat.classifiers.controller.result.ClassifierResult;
import org.bioplat.classifiers.controller.result.EvaluationResult;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.service.FunctionsService;
import org.bioplat.classifiers.service.RService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO sacar String de los parámetros! modelar y utilizar Gson!
//TODO sacar String de los parámetros! modelar y utilizar Gson!
//TODO sacar String de los parámetros! modelar y utilizar Gson!
//TODO sacar String de los parámetros! modelar y utilizar Gson!
//TODO sacar String de los parámetros! modelar y utilizar Gson!
@RestController
@Transactional
@CrossOrigin
public class ClassifierFunctionsController {

    private static final Logger logger = LoggerFactory.getLogger(ClassifierFunctionsController.class);

    @Autowired
    private FunctionsService functionsService;


    @Autowired
    private RService rService;


    @GetMapping("/functions")
    public Set<ClassifierResult> findByAuthor(@RequestParam String author) {
        return functionsService.findByAuthor(author).stream().map( e -> new ClassifierResult(e)).collect(Collectors.toSet());
    }

    @PostMapping("/functions")
    public ClassifierResult create(@RequestParam String author, @RequestParam String name,
                                   @RequestParam String expressionAsJson, @RequestParam String groupsAsJson, @RequestParam String groupLabelsAsJson) {
        ClassifierFunctionDescriptor result = functionsService.create(author, name, expressionAsJson, groupsAsJson, groupLabelsAsJson);
        rService.create(result);
        return new ClassifierResult(result);
    }



    //POST por el tamaño del request sino sería GET
    @PostMapping("/functions/{function}")
    public EvaluationResult eval(@PathVariable ClassifierFunctionDescriptor function, @RequestParam String mrna) {
        String message = rService.eval(function, mrna);
        return new EvaluationResult(message);
    }


}
