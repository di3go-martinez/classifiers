curl --data "id=3&expressionAsJson=$(cat expression.applied-dataset.json)&groupsAsJson=$(cat groups.applied-dataset.json)&groupLabelsAsJson=$(cat groups-labels.json)" http://localhost:8000/functions


