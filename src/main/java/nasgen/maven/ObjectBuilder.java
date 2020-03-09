package nasgen.maven;

import java.io.File;
import org.apache.maven.plugin.logging.Log;

import nasgen.Main;
import nasgen.Main.ErrorReporter;

public class ObjectBuilder {

    private final Log log;
    private final ErrorReporter reporter;

    public ObjectBuilder(final Log log) {
        this.log = log;

        final ErrorReporter reporter = new Main.ErrorReporter() {

            @Override
            public void error(final String message) {
                log.error(message);
            }
        };

        this.reporter = reporter;
    }

    public void process(final File dir) {
        final File[] files = dir.listFiles();

        for (final File toProcess : files) {
            if (toProcess.isFile() && toProcess.getName().endsWith(".class")) {
                log.info("Building " + toProcess.getName());
                if (!Main.process(toProcess, getParent(toProcess), reporter)) {
                    log.error("Failed to build " + toProcess.getName());
                }
            }
        }
    }

    private static File getParent(final File file) {
        return file.toPath().getParent().toFile();
    }
}
