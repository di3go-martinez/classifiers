package org.bioplat.classifiers.service;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;

import java.util.List;
import java.util.Set;

public interface FunctionsService {

    List<ClassifierFunctionDescriptor> findAll();

    ClassifierFunctionDescriptor create(String author, String name,  String datasetJson, String groupsJson, String groupLabelsJson);

    List<ClassifierFunctionDescriptor> findByAuthor(String author);
}
