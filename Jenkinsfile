pipeline {
  agent{
    kubernetes{
        label 'slave'
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
        script{
          sh 'mvn test"'
        }
      }
    }
  }
}