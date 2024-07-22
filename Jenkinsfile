@Library('java@main') _
pipelineUsingJava17AndMavenWithPublicDockerImage('marcoshssilva/spring-eureka',
    [
        'APP_NAME': 'spring-eureka',
        'CERT_DOMAIN': 'starlord443.dev',
        'DEPLOY': 'DOKKU',
        'HOST': 'spring-eureka.starlord443.dev',
        'USE_SSL': true,
        // 'USE_LETSENCRYPT': true, // available only for branch 'main'
    ],
)
