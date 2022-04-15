pipeline {
    agent any

    environment {
		SONARQUBE_LOGIN_ID	= credentials('SONARQUBE_LOGIN_ID')
		AWS_ACCESS_KEY_ID     	= credentials('AWS_ACCESS_KEY_ID')
		AWS_SECRET_ACCESS_KEY 	= credentials('AWS_SECRET_ACCESS_KEY')
		ARTIFACT_NAME 		= 'Intro-to-Devops-Assignment.jar'
		AWS_S3_BUCKET 		= 'intro-to-devops-assignment-bucket'
		AWS_EB_APP_NAME 	= 'Intro-to-Devops-Assignment'
		AWS_EB_TEST_ENVIRONMENT = 'Introtodevopsassignment-test-env'
		AWS_EB_PROD_ENVIRONMENT = 'Introtodevopsassignment-prod-env'
		AWS_EB_APP_VERSION	= "${BUILD_ID}"
    }

    stages {
        stage('Build and Unit Testing') {
            steps {
                withMaven {
                    bat "mvn clean package"
                }
            }
        }
        stage('Integration Testing') {
            steps {
                echo 'Integration Testing using Suits like Selenium'
            }
        }
        stage('Static Code Analysis') {
            steps {
                withMaven {
                    bat "mvn sonar:sonar -Dsonar.login=${SONARQUBE_LOGIN_ID}"
                }           
            }
        }
        stage('Upload Artifact') {
            when {
                branch 'main'
            }
            steps {
                bat 'aws configure set region ap-south-1'
                bat 'aws s3 cp ./target/Intro-to-Devops-Assignment-1.0.jar s3://intro-to-devops-assignment-bucket/Intro-to-Devops-Assignment.jar'
            }
        }
        stage('Deploy to Test Environment') {
	    when {
		branch 'main'
            }
            steps {
		bat "aws elasticbeanstalk create-application-version --application-name ${AWS_EB_APP_NAME} --version-label ${AWS_EB_APP_VERSION} --source-bundle S3Bucket=${AWS_S3_BUCKET},S3Key=${ARTIFACT_NAME}"
                bat "aws elasticbeanstalk update-environment --application-name ${AWS_EB_APP_NAME} --environment-name ${AWS_EB_TEST_ENVIRONMENT} --version-label ${AWS_EB_APP_VERSION}"
            }
        }
        stage('Regression Testing') {
            when {
                branch 'main'
            }
            steps {
        	echo 'Regresssion Testing using Selenium or any other regression testing suit in Test Environment'
            }
        }
        stage('Deploy To Production Environment') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploy to Production Environment Works!!'
                bat "aws elasticbeanstalk update-environment --application-name ${AWS_EB_APP_NAME} --environment-name ${AWS_EB_PROD_ENVIRONMENT} --version-label ${AWS_EB_APP_VERSION}"
            }
        }
    }
    post {
	failure {
    	    echo "This gets called only when there is a failure"
	    // bat "mail bcc: '', body: '', cc: '', from: '', replyTo: '', subject: 'Build Failed', to: 'hrjayanth@gmail.com'"
	    }
	always { 
            echo "This always gets called"
	}
    }
}
