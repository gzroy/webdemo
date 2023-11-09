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
        [key: 'ref', value: '$.pull_request.head.ref', expressionType: 'JSONPath'],
        [key: 'sha', value: '$.pull_request.head.sha', expressionType: 'JSONPath']
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
          sh """
          (curl -L -X POST \
          -H \"Accept: application/vnd.github+json\" \
          -H \"Authorization: Bearer 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\" \
          -H \"X-GitHub-Api-Version: 2022-11-28\" \
          https://api.github.com/repos/gzroy/webdemo/issues/7/comments \
          -d \'{\"body\": \"UT test failure\"}\')
          """
        }
        success {
          echo 'unit test successa!'
        }
      }
    }
  }
}