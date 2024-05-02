#!/bin/bash

# Ensuring that mandatory environment variables are set
: "${USERNAME:?USERNAME not set or empty}"
: "${REPO:?REPO not set or empty}"
: "${TAG:?TAG not set or empty}"
: "${APPNAME:?APPNAME not set or empty}"
    echo "ok: USERNAME, APPNAME, și TAG SUNT SETATE"


# Create a new builder instance and use it
#docker buildx create --use
#
# Build and push the Docker image

#docker buildx build \
#    --platform=linux/amd64,linux/arm64 \
#    -t "${USERNAME}/${REPO}:${TAG}" \
#    -t "${USERNAME}/${REPO}:latest" \
#    "${@:2}" \
#    --push \
#    "$1"
#    # Ensure this points to the directory with Dockerfile

docker tag \
  "${APPNAME}" \
  "${USERNAME}/${APPNAME}:${TAG}"
    echo "OK: GATA TAG"

docker push "${USERNAME}/${APPNAME}:${TAG}"
