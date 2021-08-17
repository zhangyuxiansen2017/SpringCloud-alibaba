pipeline {
  agent {
    node {
      label 'maven'
    }
  }
  parameters {
      string(name:'TAG_NAME',defaultValue:'latest',description:'版本号')
      choice(name:'PROJECT_NAME', choices:['provider-9001'], description: '需要部署项目')
  }
  environment {
      DOCKER_CREDENTIAL_ID = 'harbor'
      GOGS_CREDENTIAL_ID = 'git'
      KUBECONFIG_CREDENTIAL_ID = 'cloud-kubeconfig'

      REGISTRY = '192.168.2.2:30002'
      DOCKER_NAMESPACE = 'octopus'
      BRANCH_NAME = 'k8s'
      GIT_URL = 'git地址'
  }
  stages {
    stage('拉取代码') {
      steps {
        git(credentialsId: 'git', url: 'https://git地址', branch: 'k8s', changelog: true, poll: false)
        sh 'echo 正在构建 $PROJECT_NAME 版本号 $TAG_NAME'
        container ('maven') {
          sh "mvn clean install -Dprofiles.active=pro -Dmaven.test.skip=true -gs `pwd`/mvn-settings.xml"
        }
      }
    }
    stage ('构建镜像-推送快照镜像') {
      steps {
        container ('maven') {
          script {
            sh 'mvn -Dprofiles.active=pro -Dmaven.test.skip=true -gs `pwd`/mvn-settings.xml clean package'
            sh 'cd $PROJECT_NAME && docker build -f Dockerfile -t $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER .'
          }
            withCredentials([usernamePassword(passwordVariable : 'DOCKER_PASSWORD' ,usernameVariable : 'DOCKER_USERNAME' ,credentialsId : "$DOCKER_CREDENTIAL_ID" ,)]) {
                sh 'echo "$DOCKER_PASSWORD" | docker login $REGISTRY -u "$DOCKER_USERNAME" --password-stdin'
                sh 'docker tag  $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:$TAG_NAME'
                sh 'docker push  $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:$TAG_NAME'
                sh 'docker tag  $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:latest'
                sh 'docker push  $REGISTRY/$DOCKER_NAMESPACE/$PROJECT_NAME:latest'
            }
        }
      }
    }
    stage('部署到k8s') {
      steps {
        input(id: 'deploy-to-dev', message: "是否将 $PROJECT_NAME 部署到k8s集群中？")
        script {
            kubernetesDeploy(configs: "$PROJECT_NAME/deploy/**", enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
         }
      }
    }
    stage('发布版本'){
      when{
        expression{
          return params.TAG_NAME =~ /v.*/
        }
      }
      steps {
        container ('maven') {
          input(id: 'release-git-with-tag', message: '发布带tag到git?')
          withCredentials([usernamePassword(credentialsId: "$GOGS_CREDENTIAL_ID", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
             sh 'git config --global user.email "guimmm@126.com"'
             sh 'git config --global user.name "octopus"'
             sh 'git tag -a $TAG_NAME -m "$TAG_NAME"'
             sh 'git push http://$GIT_USERNAME:$GIT_PASSWORD@$GIT_URL --tags --ipv4'
          }
        }
      }
    }
  }
}