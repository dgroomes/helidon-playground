package dgroomes;

import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * Please see the README for more information.
 */
public class Runner {

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        installLoggingBridge();
        WebServer server = WebServer.builder()
                .port(8080)
                .routing(Runner::routing)
                .build()
                .start();

        log.info("The server is running http://localhost:{}/always-lucky", server.port());
    }

    /**
     * Install the JUL-to-SLF4J bridge. This is the standard boilerplate to make this work, but you should study the
     * performance impact of this configuration. The JavaDoc in {@link SLF4JBridgeHandler} explains it well.
     * <p>
     * Also see <a href="https://stackoverflow.com/a/9117188">this StackOverflow answer</a> for more information about this
     * topic. In particular, you need to blanket enable all logs in JUL if you want them to all be routed to SLF4J.
     */
    private static void installLoggingBridge() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        LogManager.getLogManager().getLogger("").setLevel(Level.FINEST);
    }

    static void routing(HttpRouting.Builder routing) {
        routing
                .register("/dice-roll", new DiceRollService())
                .get("/always-lucky", (req, res) -> res.send("You rolled a 7!"));
    }
}
