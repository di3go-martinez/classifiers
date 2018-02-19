package org.bioplat.classifiers;

import org.bioplat.classifiers.model.Mrna;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by diego on 17/02/18.
 */
public class MrnaTest {

    @Test
    public void creation(){
        String mrnajson = "[[\"gene1\",5.0]]";
        Mrna mrna= Mrna.parse(mrnajson);
        Assert.assertTrue(mrna.genes().size() == 1);


        mrnajson = "[[\"gene1\",5.0],[\"gene2\",5.0]]";
        mrna= Mrna.parse(mrnajson);
        Assert.assertTrue(mrna.genes().size() == 2);
    }


    @Test
    public void jsonify(){
        String mrnajson = "[[\"gene1\",5.0]]";
        Mrna mrna= Mrna.parse(mrnajson);
        Assert.assertTrue(mrna.toJson().equals(mrnajson));


        mrnajson = "[[\"gene1\",5.0],[\"gene2\",5.0]]";
        mrna= Mrna.parse(mrnajson);
        Assert.assertTrue(mrna.toJson().equals(mrnajson));


    }

    @Test public void jsonify_empty_mrna(){
        String emptymrnajson = "[]";
        Assert.assertTrue(Mrna.parse(emptymrnajson).toJson().equals(emptymrnajson));
    }
}
