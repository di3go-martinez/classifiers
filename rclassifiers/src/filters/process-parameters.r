

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

