package de.microtema.maven.plugin.service;

import de.microtema.maven.plugin.model.Arch42Template;
import de.microtema.maven.plugin.model.ProjectData;
import de.microtema.maven.plugin.util.MojoUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class TemplateService {

    public String mergeTemplates(List<Arch42Template> availableTemplates, List<Arch42Template> templates, ProjectData projectData) {

        StringBuilder content = new StringBuilder();

        content.append("= ")
                .append(projectData.getApplicationDisplayName())
                .append(MojoUtil.lineSeparator(2));

        qualityRequirementsTemplate(content, projectData);

        content.append(":imagesdir: ").append(projectData.getImageDir())
                .append(MojoUtil.lineSeparator(2))
                .append(":numbered:")
                .append(MojoUtil.lineSeparator(2));

        for (Arch42Template template : templates) {

            Arch42Template availableTemplate = availableTemplates.stream()
                    .filter(it -> Objects.equals(template.getIndex(), it.getIndex()))
                    .findFirst()
                    .orElse(null);

            if (Objects.nonNull(availableTemplate)) {

                content.append("include::").append(availableTemplate.getFileName()).append("[]")
                        .append(MojoUtil.lineSeparator(2));

            } else {
                content.append("== ").append(template.getTitle())
                        .append(MojoUtil.lineSeparator(2))
                        .append("Inherited from bootstrap")
                        .append(MojoUtil.lineSeparator(2));
            }
        }


        return content.toString();
    }

    private void qualityRequirementsTemplate(StringBuilder content, ProjectData projectData) {

        if(!projectData.isSourceCode()) {
            return;
        }

        String sonarHostUrl = projectData.getSonarHostUrl();

        if (Objects.isNull(sonarHostUrl)) {
            return;
        }

        String token = projectData.getSonarLogin();
        String projectID = projectData.getArtifactId();

        String template = "image:" + sonarHostUrl + "/api/project_badges/measure?project=" + projectID + "&metric=alert_status&token=" + token + "[\"Quality Gate\"] " +
                "image:" + sonarHostUrl + "/api/project_badges/measure?project=" + projectID + "&metric=coverage&token=" + token + "[\"Coverage\"] " +
                "image:" + sonarHostUrl + "/api/project_badges/measure?project=" + projectID + "&metric=sqale_rating&token=" + token + "[\"Maintainability\"]  " +
                "image:" + sonarHostUrl + "/api/project_badges/measure?project=" + projectID + "&metric=security_rating&token=" + token + "[\"Security Gate\"] " +
                "image:" + sonarHostUrl + "/api/project_badges/measure?project=" + projectID + "&metric=reliability_rating&token=" + token + "[\"Reliability Rating\"]";

        content.append(template)
                .append(MojoUtil.lineSeparator(2));
    }

    @SneakyThrows
    public void writeFile(String outputFilePath, String fileContent) {

        File outputFile = new File(outputFilePath);
        FileUtils.writeStringToFile(outputFile, fileContent, Charset.defaultCharset());
    }
}
