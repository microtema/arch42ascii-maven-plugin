package de.microtema.maven.plugin;

import de.microtema.maven.plugin.converter.FileToArch42TemplateConverter;
import de.microtema.maven.plugin.model.Arch42Template;
import de.microtema.maven.plugin.model.ProjectData;
import de.microtema.maven.plugin.service.Arch42Service;
import de.microtema.maven.plugin.service.TemplateService;
import de.microtema.maven.plugin.util.MojoUtil;
import de.microtema.model.converter.util.ClassUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;
import java.util.Optional;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class Arch42AsciiGeneratorMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(property = "outputDir", required = true)
    String outputFile = "/README.adoc";

    @Parameter(property = "imagesDir", required = true)
    String imagesDir = "./docs";

    @Parameter(property = "inputDir", required = true)
    String inputDir = "./docs";

    @Parameter(property = "secure", required = true)
    boolean secure = false;

    Arch42Service arch42Service = new Arch42Service(new FileToArch42TemplateConverter());
    TemplateService templateService = new TemplateService();

    public void execute() {

        String appName = Optional.ofNullable(project.getName()).orElse(project.getArtifactId());

        ProjectData projectData = new ProjectData();

        projectData.setApplicationDisplayName(project.getName());
        projectData.setArtifactId(project.getArtifactId());

        projectData.setSonarHostUrl(project.getProperties().getProperty("sonar.host.url"));
        projectData.setSonarLogin(project.getProperties().getProperty("sonar.login"));

        projectData.setOutputFile(outputFile);
        projectData.setImageDir(imagesDir);
        projectData.setSourceCode(MojoUtil.hasSourceCode(project.getBasedir()));
        projectData.setSecure(secure);

        List<Arch42Template> templates = arch42Service.getTemplates();
        List<Arch42Template> availableTemplates = arch42Service.getAvailableTemplates(inputDir);

        if (availableTemplates.isEmpty()) {
            logMessage("Skip Generate " + outputFile + " for " + appName + " due to missing " + inputDir + " directory!");
            return;
        }

        logMessage("Generate " + outputFile + " for " + appName);

        String mergeTemplates = templateService.mergeTemplates(availableTemplates, templates, projectData);

        templateService.writeFile(outputFile, mergeTemplates);
    }

    void logMessage(String message) {

        Log log = getLog();

        log.info("+----------------------------------+");
        log.info(message);
        log.info("+----------------------------------+");
    }
}
