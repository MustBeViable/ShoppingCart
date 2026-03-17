pipeline {
  agent any

  options {
    skipDefaultCheckout(false)
  }

  environment {
    PATH                    = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    JAVA_HOME                = "/Library/Java/JavaVirtualMachines/zulu-17.jdk/Contents/Home"
    DOCKERHUB_CREDENTIALS_ID = 'Docker'
    IMAGE_NAME              = 'eliasrinne/shopping_cart'
    IMAGE_TAG               = 'latest'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Verify Tools') {
      steps {
        sh 'echo "PATH=$PATH"'
        sh 'echo "JAVA_HOME=$JAVA_HOME"'
        sh 'which mvn && mvn -v'
        sh 'which java && java -version'
        sh 'which docker'
        sh 'docker --version'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -B clean compile'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn -B test'
      }
      post {
        always {
          junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
      }
    }

    stage('Code Coverage') {
      steps {
        sh 'mvn -B jacoco:report'
      }
      post {
        always {
          jacoco()
        }
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -B package -DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t "${IMAGE_NAME}:${IMAGE_TAG}" .'
      }
    }

    stage('Push Docker Image') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: "${DOCKERHUB_CREDENTIALS_ID}",
          usernameVariable: 'DOCKER_USER',
          passwordVariable: 'DOCKER_PASS'
        )]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
            docker push "${IMAGE_NAME}:${IMAGE_TAG}"
            docker logout
          '''
        }
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/*.jar, target/site/jacoco/**', allowEmptyArchive: true
    }
  }
}