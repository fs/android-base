#!/bin/bash
./gradlew downloadApolloSchema -Pcom.apollographql.apollo.endpoint=https://rails-base-graphql-api.herokuapp.com/graphql -Pcom.apollographql.apollo.schema=src/main/graphql/com/flatstack/android/schema.json
