package org.bioplat.classifiers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GeneReference))
            return false;
        GeneReference other = (GeneReference) obj;
        return other.name().equals(this.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name());
    }
}
