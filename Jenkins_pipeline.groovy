pipeline{
    agent any
    stages{
        stage('Clean') {
		  steps{
			  sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'kubectl delete deployment k8s-demo-deployment --ignore-not-found'"		
			  sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'kubectl delete service k8s-demo-svc --ignore-not-found'"
			  sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'rm -rf k8s-demo'"
		  }
		}
		stage('Build docker image') {
          steps{
              sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'mkdir k8s-demo'"
			  sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'cd k8s-demo'"
			  sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'git clone https://github.com/chnhgn/k8s-demo.git'"			
			  sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'docker build -t test-service:v1 ./k8s-demo/'"
          }
        }
		stage('Unit Test') {
          steps{
              echo "Do some unit test here"
          }
        }
		stage('Create service') {
          steps{
              sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'kubectl create -f ./k8s-demo/svc.yml'"
          }
        }
		stage('Deploy') {
          steps{
              sh "ssh -i ~/.ssh/fall.pem -o StrictHostKeyChecking=no cloud-user@fall.edmt.sashq-d.openstack.sas.com 'kubectl create -f ./k8s-demo/deployment.yml --record=true'"
          }
        }
		stage('Automated Test') {
          steps{
              echo "Automated Test Stage"
          }
        }
    }
}