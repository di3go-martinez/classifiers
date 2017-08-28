

#* @post /functions
function (id, expressionAsJSON, groups, groupLabels){
 

  #classify

  pr$handle("POST", paste0("/",id), function(req, res){
             print( req$mrna)
             print (req$id)
  })
  print(pr) #imprimo todas las urls registradas
  print(paste0("registered: ", "/",id)) #imprimo y retorno 
}
