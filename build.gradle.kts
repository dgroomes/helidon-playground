plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
	implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
    implementation(libs.slf4j.jul)

    implementation(libs.helidon.web.server) {
        /*
        'helidon-webserver' has some optional dependencies that we don't need.

        Unfortunately, only some are listed as optional dependencies in the POM. But I'm curious, do I really need any
        of the following modules to run a hello world-style Helidon web server program?
          * 'helidon-common-configurable'
          * 'helidon-common-security'
          * 'helidon-common-tls

        The big thing we don't want is "helidon-config" because it's large (300K+) but the TLS module depends on it which
        I think is an oversight. I get this error when I ignore "helidon-config".

        Exception in thread "main" java.lang.NoClassDefFoundError: io/helidon/config/ConfigException
            at io.helidon.webserver.WebServerConfig$BuilderBase.preBuildPrototype(WebServerConfig.java:429)
            at io.helidon.webserver.WebServerConfig$Builder.buildPrototype(WebServerConfig.java:639)
            at io.helidon.webserver.WebServerConfig$Builder.build(WebServerConfig.java:646)
            at dgroomes.Runner.main(Runner.java:24)
        Caused by: java.lang.ClassNotFoundException: io.helidon.config.ConfigException
            at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
            at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
            at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:526)
            ... 4 more
        */
        exclude(group = "io.helidon.common", module = "helidon-common-security")
    }
}

application {
    mainClass.set("dgroomes.Runner")
}
