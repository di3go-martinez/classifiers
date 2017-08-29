


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
