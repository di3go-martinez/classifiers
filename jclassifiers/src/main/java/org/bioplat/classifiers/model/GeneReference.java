package org.bioplat.classifiers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity
public class GeneReference {

    @Id
    private String name;

    //hibernate
    private GeneReference(){}

    public GeneReference(String name) {
        this.name = name;
    }

    public String name(){return name;}
}
