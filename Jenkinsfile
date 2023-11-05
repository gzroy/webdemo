pipeline {
  agent{
    kubernetes{
        defaultContainer 'jnlp'
        label 'slave'
        yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: "jnlp"
    image: "jenkins/inbound-agent:latest"
    imagePullPolicy: "IfNotPresent"
    volumeMounts:
    - mountPath: "/var/run/docker.sock"
      name: "volume-0"
      readOnly: true
    - mountPath: "/home/jenkins"
      name: "workspace-volume"
      readOnly: false
    workingDir: "/home/jenkins"
  - name: "maven"
    image: "maven:3.8.3-openjdk-17"
    tty: true
    imagePullPolicy: "IfNotPresent"
    command:
    - run-jnlp-client 
    - cat
    volumeMounts:
    - mountPath: "/var/run/docker.sock"
      name: "volume-0"
      readOnly: true
    - mountPath: "/home/jenkins"
      name: "workspace-volume"
      readOnly: false
    workingDir: "/home/jenkins"
  restartPolicy: "Never"
  securityContext:
    runAsUser: 0
  serviceAccountName: "jenkins-sa"
  volumes:
  - hostPath:
      path: "/var/run/docker.sock"
    name: "volume-0"
  - emptyDir:
      medium: ""
    name: "workspace-volume"
'''
    }
  }
  environment {
    ZONE = "us-central1"
    PROJECT_ID = "curious-athlete-401708"
  }

  stages{      
    stage("git checkout") {
        steps {
            script {
                git(
                    url: 'https://github.com/gzroy/webdemo.git',
                    credentialsId: '2b498f25-c7ea-4f67-b416-479c2f92b48f',
                    branch: 'main'
                )
            }
        }
    }
    stage("test"){
      steps{
        container ('maven') {
          script{
            sh 'mvn test '
          }
        }
      }
    }
  }
}