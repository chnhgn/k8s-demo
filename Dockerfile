FROM continuumio/anaconda3
RUN mkdir myrepo
WORKDIR myrepo
RUN git clone https://github.com/chnhgn/k8s-demo.git
WORKDIR k8s-demo
CMD python ./Endpoint.py