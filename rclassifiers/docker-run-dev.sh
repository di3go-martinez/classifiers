

cat src/filters/* src/functions/* > /tmp/main.r

docker run -p 8000:8000 \
    --rm \
    -v /tmp/main.r:/main.r \
    -w /ops \
    --name rclassifiers-dev-service \
    rclassifiers-dev-service /main.r


