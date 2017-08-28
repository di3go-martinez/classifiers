from trestletech/plumber

#Ejecuto el servicio de R que ejecuta las funciones
#TODO revisar si es necesario o dejar el default que no define pr como global
ENTRYPOINT ["R", "-e", "pr <<- plumber::plumb(commandArgs()[4]); pr$run(host='0.0.0.0', port=8000)"]
