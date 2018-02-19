package org.bioplat.classifiers.model;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by diego on 17/02/18.
 */
public class Mrna {

    private Map<GeneReference, Double> data;

    public static Mrna parse(String mrna) {
        Mrna result = new Mrna();
        result.data = Maps.newHashMap();
        Gson gson = new Gson();
        String[][] parseddataset = gson.fromJson(mrna, String[][].class);
        for (String[] row : parseddataset) {
            GeneReference gref = new GeneReference(row[0]);
            Double expr = Double.valueOf(row[1]);
            result.data.put(gref, expr);
        }
        return result;
    }

    public Set<GeneReference> genes() {
        return data.keySet();
    }

    public Mrna filter(Set<GeneReference> genes) {
        Mrna result = new Mrna();
        result.data
                = Maps.filterEntries(this.data, input -> genes.contains(input.getKey()));
        return result;
    }

    public String toJson() {
        if (data.isEmpty())
            return "[]";
        StringBuilder result = new StringBuilder("[");
        for (Map.Entry<GeneReference, Double> entry : data.entrySet()) {
            String genename = entry.getKey().name();
            Double expr = entry.getValue();
            result.append("[\"").append(genename).append("\",").append(expr).append("],");
        }
        return result.deleteCharAt(result.length()-1). append("]").toString();
    }
}
