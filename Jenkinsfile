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
    stage("test"){
      steps{
        script{
          sh 'echo "testing"'
        }
      }
    }
  }
}