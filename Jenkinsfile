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
  triggers {
    GenericTrigger(
      genericVariables: [
       [key: 'action', value: '$.action', expressionType: 'JSONPath'],
       [key: 'clone_url', value: '$.pull_request.base.repo.clone_url', expressionType: 'JSONPath'],
       [key: 'ref', value: '$.pull_request.head.ref', expressionType: 'JSONPath']
      ],
      token: 'abc'
    )
  }
  environment {
    ZONE = "us-central1"
    PROJECT_ID = "curious-athlete-401708"
  }
  stages{
    stage("git checkout") {
      when {
        expression {
          return action=="opened" || action=="updated"
        }
      }
      steps {
        script {
          git(
            url: clone_url,
            credentialsId: '2b498f25-c7ea-4f67-b416-479c2f92b48f',
            branch: ref
          )
        }
      }
    }
    stage("test"){
      when {
        expression {
          return action=="opened" || action=="updated"
        }
      }
      steps{
        container('maven') {
          script{
            sh 'mvn test '
          }
        }
      }
      post {
        failure {
          echo 'unit test failure!'
        }
        success {
          echo 'unit test success!'
        }
      }
    }
  }
}