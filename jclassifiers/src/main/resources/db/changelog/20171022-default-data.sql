
insert into classifier_function_descriptor(id, author, id_classifier, name)
values (hibernate_sequence.nextval, 'bioplat', '/mamma-print', 'Mamma print');

insert into gene_reference values ('TP53BP1');

insert into classifier_function_descriptor_genes values (currval('hibernate_sequence'), 'TP53BP1');

