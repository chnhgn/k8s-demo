FROM nginx
RUN mkdir myrepo
WORKDIR myrepo
RUN echo '<h1>Hello Docker!</h1>' > index.html
COPY index.html /usr/share/nginx/html