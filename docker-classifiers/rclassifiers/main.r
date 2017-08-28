#* Log some information about the incoming request
#* @filter logger
function(req){
    cat(as.character(Sys.time()), "-", 
            req$REQUEST_METHOD, req$PATH_INFO, "-", 
                req$HTTP_USER_AGENT, "@", req$REMOTE_ADDR, "\n")
  plumber::forward()
}

#TODO read.table??

#* @filter transform_parameters
function (req, author, genes=NULL, expressions=NULL, res){
  if (!is.null(expressions)){
    mrna <- scan(textConnection(expressions), sep=",")
    geneNames <- scan(textConnection(genes), what="character", sep=",")
    names(mrna) <- geneNames
    req$mrna <- mrna 
  }
  plumber::forward()
}



#* @post /functions
function (id, genes){
  pr$handle("POST", paste0("/",id), function(req, res){
    #ver src/filters/procees_parameters.r
    print(req$mrna) #ejemplo de parámetro
    mrna <- req$mrna
    tp53<-mrna["tp53"]
    if (tp53<1) return ("Grupo de riesgo alto, debido a que perdi[o la proteccion de tp53")
    else return ("Grupo de riesgo bajo, esta protegido por tp53")
  })
  print(pr) #imprimo todas las urls registradas
  print(paste0("registered: ", "/",id)) #imprimo y retorno 
}



#* @post /mamma-print 
mammaPrint <- function(req, mrna=NULL){
  mrna <- req$mrna
  tp53<-mrna["tp53"]
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
