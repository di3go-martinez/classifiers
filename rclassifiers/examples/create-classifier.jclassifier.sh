curl -v --data "author=di3go&name=tp53solo&expressionAsJson=$(cat expression.applied-dataset.json)&groupsAsJson=$(cat groups.applied-dataset.json)&groupLabelsAsJson=$(cat groups-labels.json)" http://localhost:8080/functions

