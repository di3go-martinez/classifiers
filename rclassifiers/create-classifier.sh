curl --data "id=3&expressionAsJson=$(cat examples/expression.applied-dataset.json)&groupsAsJson=$(cat examples/groups.applied-dataset.json)&groupLabelsAsJson=$(cat examples/groups-labels.json)" http://localhost:8000/functions


