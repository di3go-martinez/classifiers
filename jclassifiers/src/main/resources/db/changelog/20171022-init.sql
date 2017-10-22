
    create sequence hibernate_sequence start with 1 increment by 1;
 
    create table classifier_function_descriptor (
        id bigint not null,
        author varchar(255) not null,
        expression_as_json clob,
        group_labels_as_json varchar(255),
        groups_as_json clob,
        id_classifier varchar(255),
        name varchar(255) not null,
        primary key (id)
    );
 
    create table classifier_function_descriptor_genes (
        classifier_function_descriptor_id bigint not null,
        genes_name varchar(255) not null,
        primary key (classifier_function_descriptor_id, genes_name)
    );
 
    create table gene_reference (
        name varchar(255) not null,
        primary key (name)
    );
 
    alter table classifier_function_descriptor_genes 
        add constraint FKm0msmm0p9rob7txhw73dhjtrr 
        foreign key (genes_name) 
        references gene_reference;

    alter table classifier_function_descriptor_genes 
        add constraint FKj8rdv587tm91jb2b1hysqah6b 
        foreign key (classifier_function_descriptor_id) 
        references classifier_function_descriptor;