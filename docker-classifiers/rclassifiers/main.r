#* Log some information about the incoming request
#* @filter logger
function(req){
    cat(as.character(Sys.time()), "-", 
            req$REQUEST_METHOD, req$PATH_INFO, "-", 
                req$HTTP_USER_AGENT, "@", req$REMOTE_ADDR, "\n")
  plumber::forward()
}


#* @filter transform_parameters
function (req, id, author, mrna=NULL, res){
  library(jsonlite)
  if (!is.null(mrna)) {
    m <- fromJSON(mrna)
    req$mrna <- m
  }
  req$id <- id
  plumber::forward()
}

#expressionAsJson es un json que serializa una matriz en la que la primera columna contiene los nombres de los genes y las demas columnas son los pacientes (datos de expresion)
#Ejemplo de expressionAsJson para una matriz de 4 pacientes y 2 genes: [["KRT17","-0.36","-0.34","-0.27","-0.51"],["FOXC1","-0.36","-0.4","-0.39","-0.45"]]
#
#groupsAsJson nombre del sample y grupo al que pertenece. GARANTIZAR QUE VIENEN EN EL MISMO ORDEN EN QUE ESTAN EXPRESADOAS LAS COLUMNAS DE LA MATRIZ DE EXPRESION!!(el formato json no define orden, depende de la implementación)
#Ejemplo de grupoAsJson para 4 pacientes:[{"sample":"sample1","grupo":1},{"sample":"sample2","grupo":2},{"sample":"sample3","grupo":2},{"sample":"sample4","grupo":2}]
#
#groupLabelsAsJson tiene la descripcion que el investigador le dio a cada grupo desde la GUI de bioplat al crear el clasificador desde una validacion.
#Ejemplo de groupLabelsAsJson: [{"group":1,"descripcion":"alto riesgo. Se sugiere bla bla"},{"group":2,"descripcion":"bajo riesgo. Se sugiere blo blao"}]
#* @post /functions
function (id, expressionAsJson, groupsAsJson, groupLabelsAsJson){
  library(jsonlite)
  
  #print(expressionAsJson)
  #print(groupsAsJson)
  #print(groupLabelsAsJson)

  #Se lee el json, se le saca la primera columna (la de genes) y se setea esa columna con row.names de la matriz.
  expression<-fromJSON(expressionAsJson)
  expression.without.gene.names.cols<-expression[,2:ncol(expression)]
  expression.without.gene.names.cols<-apply(expression.without.gene.names.cols, 2, as.numeric)
  row.names(expression.without.gene.names.cols)<-expression[,1]
  
  #Se leen los grupos. COmo viene con los nombres de columnas se lee como dataframe, con lo cual hay que convertirlo a matriz.
  groups=fromJSON(groupsAsJson)
  groups=groups[,2]
  
  #Se leen las descripciones de los grupos que tambien vienen como json.
  group.labels=fromJSON(groupLabelsAsJson)

  library(pamr)
  exp.ordered=expression.without.gene.names.cols[ order(row.names(expression.without.gene.names.cols)), ]
  mydata <- list(x=exp.ordered,y=factor(groups))
  mytrain<- pamr.train(mydata)
  saveRDS(mytrain, paste(id, ".rds", sep=""))
  
  
  
  #classify
  
  pr$handle("POST", paste0("/",id), function(req, res){
    
    library(pamr)
    expression<- req$mrna
    expression.without.gene.names.cols<-expression[,2]
    expression.without.gene.names.cols<-as.numeric(expression.without.gene.names.cols)
    names(expression.without.gene.names.cols)<-expression[,1]
    exp.ordered=expression.without.gene.names.cols[order(names(expression.without.gene.names.cols))]
    new=cbind(exp.ordered,exp.ordered)
    read=readRDS(paste(req$id, ".rds", sep=""))
    group.predicted<-pamr.predict(read, new, threshold=1)[1]
    return (group.labels[as.numeric(group.predicted),2])

    
    
  })
  print(pr) #imprimo todas las urls registradas
  print(paste0("registered: ", "/",id)) #imprimo y retorno 
}



#* @post /mamma-print 
mammaPrint <- function(req, mrna){
  
  expression<-fromJSON(mrna)
  expression.without.gene.names.cols<-expression[,2]
  expression.without.gene.names.cols<-as.numeric(expression.without.gene.names.cols)
  names(expression.without.gene.names.cols)<-expression[,1]
  exp.ordered=expression.without.gene.names.cols[order(names(expression.without.gene.names.cols))]
  
  tp53<-exp.ordered["tp53"]
  if (tp53<1) return ("Grupo de riesgo alto, debido a que perdi[o la proteccion de tp53")
  else return ("Grupo de riesgo bajo, esta protegido por tp53")
}


#* @post /mul
mul <- function (a, b) {
    as.numeric(a) * as.numeric(b)
}


#* @post /sum
addTwo <- function (a, b) {
    as.numeric(a) + as.numeric(b)
}
