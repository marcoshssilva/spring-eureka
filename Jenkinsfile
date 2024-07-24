@Library('java@main') _
pipelineUsingJava17AndMavenWithPublicDockerImage('marcoshssilva/spring-eureka',
    [
        'APP_NAME': 'spring-eureka',
        'CERT_DOMAIN': 'starlord443.dev',
        'DEPLOY': 'DOKKU',
        'DOKKU_SELECTED_BUILDPACK': 'herokuish', // Options can be 'dockerfile', 'null' and DEFAULT 'herokuish'
        // 'HOST': 'sp-cloud-eureka.starlord443.dev',
        // 'USE_SSL': true,          // NOT NEED SET IF HAS BEEN EXECUTED
        // 'USE_LETSENCRYPT': false, // available only for branch 'main', NOT NEED SET IF HAS BEEN EXECUTED
    ],
)
