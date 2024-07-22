@Library('java@main') _
pipelineUsingJava17AndMavenWithPublicDockerImage('marcoshssilva/spring-eureka',
    [
      'APP_NAME': 'spring-eureka',
      'DEPLOY': 'DOKKU',
      'USE_SSL': true,
      'USE_LETSENCRYPT': false,
      'SKIP_SONARQUBE': true,
      'SKIP_NEXUS': true,
      'SKIP_BUILDIMAGE': true
    ]
)
