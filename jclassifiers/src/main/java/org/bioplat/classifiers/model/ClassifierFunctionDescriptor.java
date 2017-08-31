package org.bioplat.classifiers.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

//FIXME subclasificar en predefinidas y las originadas disnámicamente.
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


    //los clasificadores predefinidos tiene un id, los creados no (tiene esta prop en null)
    private String idClassifier = null;

    //Spring
    private ClassifierFunctionDescriptor() {
    }

    public ClassifierFunctionDescriptor(String author, String name, String expressionAsJson, String groupsAsJson, String groupLabelsAsJson) {
        this.author = author;
        this.name = name;
        this.expressionAsJson = expressionAsJson;
        this.groupsAsJson = groupsAsJson;
        this.groupLabelsAsJson = groupLabelsAsJson;
    }

    //por ahora se crean en la base de datos directamente...
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

    //para capa dto
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getResourceId(){
        return "/"+resourceId();
    }

    public Long getId() {
        return id();
    }
//fin capa dto

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

}
