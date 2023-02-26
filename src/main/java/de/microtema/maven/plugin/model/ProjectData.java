package de.microtema.maven.plugin.model;

import lombok.Data;

@Data
public class ProjectData {

    private String applicationDisplayName;

    private String outputFile;

    private String imageDir;

    private String sonarHostUrl;

    private String sonarLogin;

    private String artifactId;
}
