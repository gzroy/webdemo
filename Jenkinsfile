pipeline {
  agent{
    kubernetes{
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: maven
            image: maven:3.8.3-openjdk-17
            tty: true
            imagePullPolicy: "IfNotPresent"
            command:
            - cat
      '''
    }
  }
  environment {
    ZONE = "us-central1"
    PROJECT_ID = "curious-athlete-401708"
  }

<<<<<<< HEAD
  stages{      
    stage("Get env") {
      steps {
        echo "{env}" 
      }
    }
=======
  stages{
    stage("Get env") {
      steps {
        echo "abc"
        echo "Print the env!"
        echo "${env}"
      }
    }      
>>>>>>> 0b27014e3b31158060282c9828956903730f9b0c
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
        container('maven') {
          script{
            sh 'mvn test '
          }
        }
      }
    }
  }
}