/*
 * Mailer service API
 * Mailer service for microservices architecture for sending emails.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: andrzej.przybysz01@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.lazydev.stksongbook.mailer.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * EmailAttachments
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-08-25T00:05:28.841+02:00")
public class EmailAttachments {
  @JsonProperty("filename")
  private String filename = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("path")
  private String path = null;

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("httpHeaders")
  private Object httpHeaders = null;

  @JsonProperty("contentType")
  private String contentType = null;

  @JsonProperty("cid")
  private String cid = null;

  @JsonProperty("encoding")
  private String encoding = null;

  @JsonProperty("headers")
  private String headers = null;

  @JsonProperty("raw")
  private String raw = null;

  public EmailAttachments filename(String filename) {
    this.filename = filename;
    return this;
  }

   /**
   * Get filename
   * @return filename
  **/
  @ApiModelProperty(value = "")
  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public EmailAttachments content(String content) {
    this.content = content;
    return this;
  }

   /**
   * Get content
   * @return content
  **/
  @ApiModelProperty(value = "")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public EmailAttachments path(String path) {
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(value = "")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public EmailAttachments href(String href) {
    this.href = href;
    return this;
  }

   /**
   * Get href
   * @return href
  **/
  @ApiModelProperty(value = "")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public EmailAttachments httpHeaders(Object httpHeaders) {
    this.httpHeaders = httpHeaders;
    return this;
  }

   /**
   * Get httpHeaders
   * @return httpHeaders
  **/
  @ApiModelProperty(value = "")
  public Object getHttpHeaders() {
    return httpHeaders;
  }

  public void setHttpHeaders(Object httpHeaders) {
    this.httpHeaders = httpHeaders;
  }

  public EmailAttachments contentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

   /**
   * Get contentType
   * @return contentType
  **/
  @ApiModelProperty(value = "")
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public EmailAttachments cid(String cid) {
    this.cid = cid;
    return this;
  }

   /**
   * Get cid
   * @return cid
  **/
  @ApiModelProperty(value = "")
  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public EmailAttachments encoding(String encoding) {
    this.encoding = encoding;
    return this;
  }

   /**
   * Get encoding
   * @return encoding
  **/
  @ApiModelProperty(value = "")
  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public EmailAttachments headers(String headers) {
    this.headers = headers;
    return this;
  }

   /**
   * Get headers
   * @return headers
  **/
  @ApiModelProperty(value = "")
  public String getHeaders() {
    return headers;
  }

  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public EmailAttachments raw(String raw) {
    this.raw = raw;
    return this;
  }

   /**
   * Get raw
   * @return raw
  **/
  @ApiModelProperty(value = "")
  public String getRaw() {
    return raw;
  }

  public void setRaw(String raw) {
    this.raw = raw;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailAttachments emailAttachments = (EmailAttachments) o;
    return Objects.equals(this.filename, emailAttachments.filename) &&
        Objects.equals(this.content, emailAttachments.content) &&
        Objects.equals(this.path, emailAttachments.path) &&
        Objects.equals(this.href, emailAttachments.href) &&
        Objects.equals(this.httpHeaders, emailAttachments.httpHeaders) &&
        Objects.equals(this.contentType, emailAttachments.contentType) &&
        Objects.equals(this.cid, emailAttachments.cid) &&
        Objects.equals(this.encoding, emailAttachments.encoding) &&
        Objects.equals(this.headers, emailAttachments.headers) &&
        Objects.equals(this.raw, emailAttachments.raw);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filename, content, path, href, httpHeaders, contentType, cid, encoding, headers, raw);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailAttachments {\n");
    
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    httpHeaders: ").append(toIndentedString(httpHeaders)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    cid: ").append(toIndentedString(cid)).append("\n");
    sb.append("    encoding: ").append(toIndentedString(encoding)).append("\n");
    sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
    sb.append("    raw: ").append(toIndentedString(raw)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

