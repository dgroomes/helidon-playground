package io.helidon.config;

/**
 * This class only exists to satisfy an accidental dependency from the Helidon web server module to the Helidon config
 * module. If this class is not present, the Helidon web server module throws {@link NoClassDefFoundError} at runtime.
 */
public class ConfigException extends RuntimeException {}
