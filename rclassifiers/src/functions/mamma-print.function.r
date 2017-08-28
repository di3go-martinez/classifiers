


#* @post /mamma-print 
mammaPrint <- function(req, mrna=NULL){
  mrna <- req$mrna
  tp53<-mrna["tp53"]
  if (tp53<1) return ("Grupo de riesgo alto, debido a que perdi[o la proteccion de tp53")
    else return ("Grupo de riesgo bajo, esta protegido por tp53")
}
