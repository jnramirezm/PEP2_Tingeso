pipeline{
    agent any
    tools{
        maven "maven"
    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jnramirezm/PEP2_Tingeso']])
                dir("Backend/acopio-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                dir("Backend/config-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                dir("Backend/eureka-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                dir("Backend/gateway-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                dir("Backend/laboratorio-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                dir("Backend/pago-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                dir("Backend/proveedor-service"){
                        bat "mvn clean package -DskipTests=true"
                }
                
            }
        }
        stage("Build Docker Image"){
            steps{
                dir("Backend/acopio-service"){
                        bat "docker build -t jramirezmo/acopio-servicer:latest ."
                }
                dir("Backend/config-service"){
                        bat "docker build -t jramirezmo/config-service:latest ."
                }
                dir("Backend/eureka-service"){
                        bat "docker build -t jramirezmo/eureka-service:latest ."
                }
                dir("Backend/gateway-service"){
                        bat "docker build -t jramirezmo/gateway-service:latest ."
                }
                dir("Backend/laboratorio-service"){
                        bat "docker build -t jramirezmo/laboratorio-service:latest ."
                }
                dir("Backend/pago-service"){
                        bat "docker build -t jramirezmo/pago-service:latest ."
                }
                dir("Backend/proveedor-service"){
                        bat "docker build -t jramirezmo/proveedor-service:latest ."
                }
            }
        }
        stage("Push Docker Image"){
            steps{
                withCredentials([string(credentialsId: 'dckrhubpassword', variable: 'dckpass')]){
                        bat "docker login -u jramirezmo -p ${dckpass}"
                        }
                dir("Backend/acopio-service"){
                        bat "docker push jramirezmo/acopio-service"
                }
                dir("Backend/config-service"){
                        bat "docker push jramirezmo/config-service"
                }
                dir("Backend/eureka-service"){
                        bat "docker push jramirezmo/eureka-service"
                }
                dir("Backend/gateway-service"){
                        bat "docker push jramirezmo/gateway-service"
                }
                dir("Backend/laboratorio-service"){
                        bat "docker push jramirezmo/laboratorio-service"
                }
                dir("Backend/pago-service"){
                        bat "docker push jramirezmo/pago-service"
                }
                dir("Backend/proveedor-service"){
                        bat "docker push jramirezmo/proveedor-service"
                }
            }
        }
    }
    post{
        always{
            dir("Tingeso"){
                bat "docker logout"
            }
        }
    }
}