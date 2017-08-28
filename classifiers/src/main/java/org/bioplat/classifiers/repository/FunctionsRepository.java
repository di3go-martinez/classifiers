package org.bioplat.classifiers.repository;

import org.bioplat.classifiers.model.ClassifierFunctionDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunctionsRepository extends JpaRepository<ClassifierFunctionDescriptor, Long> {

    List<ClassifierFunctionDescriptor> findByAuthor(String author);
}
