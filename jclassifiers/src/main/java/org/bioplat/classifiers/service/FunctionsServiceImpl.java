package org.bioplat.classifiers.service;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.repository.FunctionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FunctionsServiceImpl implements FunctionsService {

    @Autowired
    private FunctionsRepository functionsRepository;

    @Override
    public List<ClassifierFunctionDescriptor> findAll() {
        return functionsRepository.findAll();
    }

    @Override
    public ClassifierFunctionDescriptor create(String author, String name, Set<String> genes) {
        return functionsRepository.save(new ClassifierFunctionDescriptor(author, name, genes));
    }

    @Override
    public List<ClassifierFunctionDescriptor> findByAuthor(String author) {
        return functionsRepository.findByAuthor(author);
    }
}
