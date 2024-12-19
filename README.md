# vcap-services-consumption-java-app

Java Spring App that consumes the btp-environment-variable-access lib. The application uses java-sap-service-operator-0.10.5.jar as a dependency. This jar has been built from a custom branch feat/layered-k8s-with-root-env-var. The changes that the branch introduces can be found in this PR: https://github.com/SAP/btp-environment-variable-access/pull/213.
The java-sap-service-operator-0.10.5.jar, all of its required dependencies, and the Java application will be packaged in a single jar after:

mvn clean install
