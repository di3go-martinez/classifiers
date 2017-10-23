curl --data "mrna=$(cat mrna.json)" http://localhost:8080/functions/${1?"falta el id del clasificador"}
