package org.bioplat.classifiers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.Set;

//FIXME subclasificar en predefinidas y las originadas dinámicamente.
@Entity
@SequenceGenerator(name = "seq_function_resource")
public class ClassifierFunctionDescriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_function_resource")
    private Long id;

    @NotEmpty
    private String author;

    @NotEmpty
    private String name;

    @Lob
    private String expressionAsJson;

    @Lob
    private String groupsAsJson;

    private String groupLabelsAsJson;

    @ManyToMany
    private Set<GeneReference> genes;

    //los clasificadores predefinidos tiene un id, los creados no (tiene esta prop en null)
    private String idClassifier = null;

    //Spring
    private ClassifierFunctionDescriptor() {
    }

    //FIXME sacar strings, modelar!
    public ClassifierFunctionDescriptor(String author, String name, String expressionAsJson, String groupsAsJson, String groupLabelsAsJson, Set<GeneReference> genes) {
        this.author = author;
        this.name = name;
        this.expressionAsJson = expressionAsJson;
        this.genes = genes;
        this.groupsAsJson = groupsAsJson;
        this.groupLabelsAsJson = groupLabelsAsJson;
    }


    // Clasificadores predefinidos, ya creados en la base de datos
    public ClassifierFunctionDescriptor(String idClassifier) {
        Preconditions.checkNotNull(idClassifier);
        this.idClassifier = idClassifier;
    }

    public Long id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String name() { return name; }

    public String resourceId() {
        if (idClassifier == null)
            return id.toString();
        else
            return idClassifier;
    }

    @JsonIgnore
    public Boolean isPredefined(){
        return idClassifier != null;
    }


    @Override
    public String toString() {
        return name+" (by "+author()+")"+ " @"+resourceId();
    }

    private static final Logger logger = LoggerFactory.getLogger(ClassifierFunctionDescriptor.class);

    public String expressionAsJson() {
        return expressionAsJson;
    }

    public String groupsAsJson() {
        return groupsAsJson;
    }

    public String groupLabelsAsJson() {
        return groupLabelsAsJson;
    }

    public Set<GeneReference> genes() {
        return this.genes;
    }
}
