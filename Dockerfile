FROM nginx
RUN mkdir myrepo
WORKDIR myrepo
RUN echo '<h1>Hello world!</h1>' > index.html
RUN cp index.html /usr/share/nginx/html