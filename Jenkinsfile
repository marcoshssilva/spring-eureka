@Library('java@main') _
pipelineUsingJava17AndMavenWithPublicDockerImage('marcoshssilva/spring-eureka',
    [
        'APP_NAME': 'spring-eureka',
        'DEPLOY': 'DOKKU',
        'DOKKU_SELECTED_BUILDPACK': 'https://github.com/heroku/heroku-buildpack-java', // Options can be 'dockerfile', 'null' and DEFAULT 'herokuish'
        'HOST': 'spring-eureka.starlord443.dev',
    ],
)
