package org.bioplat.classifiers.controller.result;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;

import java.util.Set;
import java.util.stream.Collectors;

public class ClassifierResult {

    private final ClassifierFunctionDescriptor cfd;

    public ClassifierResult(ClassifierFunctionDescriptor cfd){
        this.cfd = cfd;
    }

    public String getName(){
        return cfd.name();
    }

    public String getAuthor(){return cfd.author();}

    public String getResourceId(){
        return cfd.resourceId();
    }

    public Long getId() {
        return cfd.id();
    }

    public Set<String> getGenes(){
        return cfd.genes().stream().map(g -> g.name()).collect(Collectors.toSet());
    }
}
