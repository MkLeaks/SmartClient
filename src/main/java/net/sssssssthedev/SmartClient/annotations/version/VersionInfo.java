package net.sssssssthedev.SmartClient.annotations.version;

public class VersionInfo {

    public String name;
    public String version;
    public String build;

    public VersionInfo() {
        this.name = (getClass().getAnnotation(IVersion.class)).name();
        this.version = (getClass().getAnnotation(IVersion.class)).version();
        this.build = (getClass().getAnnotation(IVersion.class)).build();
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public String getBuild() {
        return this.build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}

