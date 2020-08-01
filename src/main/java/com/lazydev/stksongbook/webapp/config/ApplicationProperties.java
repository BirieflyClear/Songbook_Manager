package com.lazydev.stksongbook.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

  private Role role;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public static class Role {
    private String user;
    private String moderator;
    private String admin;
    private String superAdmin;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getModerator() {
      return moderator;
    }

    public void setModerator(String moderator) {
      this.moderator = moderator;
    }

    public String getAdmin() {
      return admin;
    }

    public void setAdmin(String admin) {
      this.admin = admin;
    }

    public String getSuperAdmin() {
      return superAdmin;
    }

    public void setSuperAdmin(String superAdmin) {
      this.superAdmin = superAdmin;
    }
  }
}
