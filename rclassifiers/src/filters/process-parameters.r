
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

