package de.microtema.maven.plugin.model;


public class ProjectData {

    private String applicationDisplayName;

    private String outputFile;

    private String imageDir;

    private String sonarHostUrl;

    private String sonarLogin;

    private String artifactId;

    private boolean sourceCode;

    private boolean secure;

    public String getApplicationDisplayName() {
        return applicationDisplayName;
    }

    public void setApplicationDisplayName(String applicationDisplayName) {
        this.applicationDisplayName = applicationDisplayName;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getImageDir() {
        return imageDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public String getSonarHostUrl() {
        return sonarHostUrl;
    }

    public void setSonarHostUrl(String sonarHostUrl) {
        this.sonarHostUrl = sonarHostUrl;
    }

    public String getSonarLogin() {
        return sonarLogin;
    }

    public void setSonarLogin(String sonarLogin) {
        this.sonarLogin = sonarLogin;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public boolean isSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(boolean sourceCode) {
        this.sourceCode = sourceCode;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
