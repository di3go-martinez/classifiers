
cat src/filters/* src/functions/* > main.r

docker run -p 8000:8000 \
    --rm \
    -v $(readlink -f main.r):/main.r \
    -w /ops \
    my-plumber /main.r


