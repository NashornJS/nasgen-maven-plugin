package nasgen.maven;

import java.io.File;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generate-objects", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class GenerateObjectsMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.outputDirectory}", readonly = true)
    private File outputDirectory;

    @Parameter(property = "paths", required = true)
    private List<String> paths;

    @Override
    public void execute() {
        final ObjectBuilder builder = new ObjectBuilder(getLog());
        for (final String path : paths) {
            final File pkg = new File(outputDirectory, path);
            builder.process(pkg);
        }
    }
}
