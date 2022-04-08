pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Clone Works!!'
                git branch: 'main', credentialsId: 'MyGitToken', url: 'https://github.com/hrjayanth/Intro-to-Devops-Assignment.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Build and Unit Testing!!'
                withMaven {
                    bat "mvn clean package"
                } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
            }
        }
        stage('Test') {
            steps {
                echo 'Integration or Regression Testing using Suits like Selenium'
            }
        }
        stage('Static Code Analysis') {
            steps {
                withMaven {
                    // bat "mvn sonar:sonar -Dsonar.login=fab8e39c40eef5158e429a6fa80bf7708cb9976a -Dsonar.branch.name='$env.BRANCH_NAME'"
                    bat "mvn sonar:sonar -Dsonar.login=fab8e39c40eef5158e429a6fa80bf7708cb9976a"
                }
            }
        }
        stage('Deploy To Staging Environment') {
            steps {
                echo 'Deploy to Staging Environment Works!!'
            }
        }
        stage('Deploy To Production Environment') {
            steps {
                echo 'Deploy to Production Environment Works!!'
            }
        }
    }
}
