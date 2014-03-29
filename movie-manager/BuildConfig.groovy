grails.servlet.version = '3.0'
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.work.dir = 'target/work'
grails.project.target.level = 1.7
grails.project.source.level = 1.7

grails.config.locations = [
        "classpath:${appName}-config.properties",
        "classpath:${appName}-config.groovy",
        "file:./logback-test.groovy"
        ]

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    compile: [maxMemory: 512, minMemory: 256, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 512, minMemory: 256, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 512, minMemory: 256, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 512, minMemory: 256, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 512, minMemory: 256, debug: false, maxPerm: 256]
]

