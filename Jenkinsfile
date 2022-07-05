pipeline{
    agent any
    stages{
        stage('Build jar files'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Run Automated tests in Tst'){
            steps{
                sh "mvn clean test -DbaseUrl=test"
            }
            post{
                always{
                    junit 'target/surefire-reports/junitreports/*.xml'
                    emailext attachLog: true, attachmentsPattern: 'target/surefire-reports/emailable-report.html, Logs/testlogs.txt', body: '$DEFAULT_CONTENT', subject: '$DEFAULT_SUBJECT', to: '$DEFAULT_RECIPIENTS'

                }
            }
           
        }
        stage('Run automated test in prod'){
            steps{
                sh "mvn clean test -DbaseUrl=prod"
            }
            post{
                always{
                    junit 'target/surefire-reports/junitreports/*.xml'
                    emailext attachLog: true, attachmentsPattern: 'target/surefire-reports/emailable-report.html, Logs/prodlogs.txt', body: '$DEFAULT_CONTENT', subject: '$DEFAULT_SUBJECT', to: '$DEFAULT_RECIPIENTS'
                }
            }
        }
        }
    }
