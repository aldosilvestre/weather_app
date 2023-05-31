build:
    podman build -t user/java:appWeather .
run:
    podman run --name appWeather -d -p 8080:8080 user/java:appWeather
kill:
    podman container kill appWeather
logs:
    podman logs -f appWeather
interactive:
    podman exec -it appWeather /bin/zsh
