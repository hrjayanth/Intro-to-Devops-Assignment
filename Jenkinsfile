pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID     = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
		ARTIFACT_NAME = 'Intro-to-Devops-Assignment.jar'
        AWS_S3_BUCKET = 'intro-to-devops-assignment-bucket'
        AWS_EB_APP_NAME = 'Intro-to-Devops-Assignment'
        AWS_EB_ENVIRONMENT = 'Introtodevopsassignment-test-env'
        AWS_EB_APP_VERSION = '1.3'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Clone Works!!'
                // git branch: 'main', credentialsId: 'MyGitToken', url: 'https://github.com/hrjayanth/Intro-to-Devops-Assignment.git'
                
                bat '%printenv%'
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
            	echo 'Skipping static code analysis'
                // withMaven {
                    // bat "mvn sonar:sonar -Dsonar.login=fab8e39c40eef5158e429a6fa80bf7708cb9976a -Dsonar.branch.name='$env.BRANCH_NAME'"
                    // bat "mvn sonar:sonar -Dsonar.login=fab8e39c40eef5158e429a6fa80bf7708cb9976a"
                // }
            }
        }
        stage('Upload Artifact') {
            steps {
                echo 'Deploy to Staging Environment Works!!'
                
                bat 'aws configure set region ap-south-1'
                bat 'aws s3 cp ./target/Intro-to-Devops-Assignment-1.0.jar s3://intro-to-devops-assignment-bucket/Intro-to-Devops-Assignment.jar'
            }
        }
        stage('Deploy to Test Environment') {
            steps {
                echo 'Deploy to Staging Environment Works!!'
            	echo "BuildID: ${BUILD_ID}"
                echo 'BuildNumber: $AWS_EB_APP_VERSION'
                echo 'BuildNumber: ${AWS_EB_APP_VERSION}'
                echo 'BuildID: ${BUILD_ID}'
                echo 'App Name: $AWS_EB_APP_NAME'
				
				// bat 'aws elasticbeanstalk create-application-version --application-name $AWS_EB_APP_NAME --version-label $AWS_EB_APP_VERSION --source-bundle S3Bucket=$AWS_S3_BUCKET,S3Key=$ARTIFACT_NAME'
                // bat 'aws elasticbeanstalk update-environment --application-name $AWS_EB_APP_NAME --environment-name $AWS_EB_ENVIRONMENT --version-label $AWS_EB_APP_VERSION'
                bat 'aws elasticbeanstalk create-application-version --application-name Intro-to-Devops-Assignment --version-label 1.3 --source-bundle S3Bucket=intro-to-devops-assignment-bucket,S3Key=Intro-to-Devops-Assignment.jar'
				bat 'aws elasticbeanstalk update-environment --application-name Intro-to-Devops-Assignment --environment-name Introtodevopsassignment-test-env --version-label 1.3'                
            }
        }
        stage('Deploy To Production Environment') {
            steps {
                echo 'Deploy to Production Environment Works!!'
            }
        }
    }
}
