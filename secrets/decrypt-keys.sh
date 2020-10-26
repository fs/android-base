#!/bin/bash
if [ -z "$SECRET_ANDROID_BASE" ]; then
    echo 'You must provide secret key $SECRET_ANDROID_BASE' >&2
    exit 2
else
    openssl aes-256-cbc -d -md sha256 -nosalt -a -pass pass:$SECRET_ANDROID_BASE -in secrets/keys.properties.crypted -out secrets/keys.properties
    openssl aes-256-cbc -d -md sha256 -nosalt -a -pass pass:$SECRET_ANDROID_BASE -in secrets/google-services.json.crypted -out app/google-services.json
fi
