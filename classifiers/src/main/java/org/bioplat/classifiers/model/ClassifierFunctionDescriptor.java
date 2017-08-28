package org.bioplat.classifiers.model;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "seq_function_resource")
public class ClassifierFunctionDescriptor {

    //TODO guardar colección, hecho así por simplicidad en el mapeo
    private String geneNamesCommaSeparated;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_function_resource")
    private Long id;

    @NotEmpty
    private String author;

    private String name;

    //los clasificadores predefinidos tiene un id, los creados no (tiene esta prop en null)
    private String idClassifier = null;

    //Spring
    private ClassifierFunctionDescriptor() {
    }

    public ClassifierFunctionDescriptor(String author, String name, Set<String> geneNames) {
        this.author = author;
        this.name = name;
        this.geneNamesCommaSeparated = Joiner.on(",").join(geneNames);
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

    public String genesAsString() {
        return geneNamesCommaSeparated;
    }

    public String resourceId() {
        if (idClassifier == null)
            return id.toString();
        else
            return idClassifier;
    }

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

    public String getResourseId(){
        return "/"+resourceId();
    }

    private static final Logger logger = LoggerFactory.getLogger(ClassifierFunctionDescriptor.class);
}
