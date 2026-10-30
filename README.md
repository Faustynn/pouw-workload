# pouw-workload
Synthetic client workload generator

## Run locally

```bash
./gradlew build          # compile and tests
LIGHT_NODE_URL=http://localhost:8000 ./gradlew run   # /health on port 8000
```

Variables: `LIGHT_NODE_URL` (default `http://localhost:8080`), `MAX_CONCURRENCY`
(100), `TIMEOUT_MS` (5000).

`Dockerfile` builds the image that pouw-deploy runs as `workload`
(`ghcr.io/pouw-zk/pouw-workload`, published from `main` by `docker.yml`). To run the
whole network with your local changes, clone pouw-deploy next to this repository and
run `make dev` there.
