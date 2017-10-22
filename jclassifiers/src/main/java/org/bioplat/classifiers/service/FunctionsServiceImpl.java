package org.bioplat.classifiers.service;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.bioplat.classifiers.model.GeneReference;
import org.bioplat.classifiers.repository.FunctionsRepository;
import org.bioplat.classifiers.repository.GeneReferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ClassifierFunctionDescriptor create(String author, String name, String datasetJson, String groupsJson, String groupLabelsJson) {
        Set<GeneReference> genes = findGenes(datasetJson);
        return functionsRepository.save(new ClassifierFunctionDescriptor(author, name, datasetJson, groupsJson, groupLabelsJson, genes));
    }

    @Autowired
    private GeneReferencesRepository genesRepository;

    private static Gson gson = new Gson();
    private Set<GeneReference> findGenes( String expressionAsJson) {
        Set<GeneReference> result = Sets.newHashSet();
        String[][] data =  gson.fromJson(expressionAsJson, String[][].class);
        for (String[] tuple: data)
            result.add(findOrCreate(tuple[0]));
        return result;
    }

    private GeneReference findOrCreate(String id) {
        GeneReference gref = genesRepository.findOne(id);
        if (gref == null)
            gref = genesRepository.save(new GeneReference(id));
        return gref;
    }

    @Override
    public List<ClassifierFunctionDescriptor> findByAuthor(String author) {
        return functionsRepository.findByAuthor(author);
    }
}
