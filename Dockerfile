FROM nginx
RUN mkdir myrepo
WORKDIR myrepo
RUN git clone https://github.com/chnhgn/k8s-demo.git
WORKDIR k8s-demo
RUN echo '<h1>Hello Docker!</h1>' > index.html
COPY index.html /usr/share/nginx/html