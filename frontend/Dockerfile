# containerize a react app in NGINX
# Step 1
FROM node:18-alpine as build-step

RUN mkdir /app
WORKDIR /app
COPY package.json /app

RUN npm install

ARG REACT_APP_API_URL
ENV REACT_APP_API_URL $REACT_APP_API_URL
COPY . /app
RUN npm run build

# Stage 2
FROM nginx:1.17.1-alpine
# COPY --from=build-step /app/build /usr/share/nginx/html

WORKDIR /usr/share/nginx/html
# Remove default nginx static assets
RUN rm -rf ./*
# Copy static assets from builder stage
COPY --from=build-step /app/build .
# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]