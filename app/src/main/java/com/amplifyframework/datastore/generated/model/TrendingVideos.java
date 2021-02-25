package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the TrendingVideos type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TrendingVideos", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class TrendingVideos implements Model {
  public static final QueryField ID = field("TrendingVideos", "id");
  public static final QueryField TITLE = field("TrendingVideos", "title");
  public static final QueryField S3_URL = field("TrendingVideos", "s3_url");
  public static final QueryField CF_URL = field("TrendingVideos", "cf_url");
  public static final QueryField THUMBNAIL = field("TrendingVideos", "thumbnail");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String title;
  private final @ModelField(targetType="String") String s3_url;
  private final @ModelField(targetType="String") String cf_url;
  private final @ModelField(targetType="String") String thumbnail;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getS3Url() {
      return s3_url;
  }
  
  public String getCfUrl() {
      return cf_url;
  }
  
  public String getThumbnail() {
      return thumbnail;
  }
  
  private TrendingVideos(String id, String title, String s3_url, String cf_url, String thumbnail) {
    this.id = id;
    this.title = title;
    this.s3_url = s3_url;
    this.cf_url = cf_url;
    this.thumbnail = thumbnail;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TrendingVideos trendingVideos = (TrendingVideos) obj;
      return ObjectsCompat.equals(getId(), trendingVideos.getId()) &&
              ObjectsCompat.equals(getTitle(), trendingVideos.getTitle()) &&
              ObjectsCompat.equals(getS3Url(), trendingVideos.getS3Url()) &&
              ObjectsCompat.equals(getCfUrl(), trendingVideos.getCfUrl()) &&
              ObjectsCompat.equals(getThumbnail(), trendingVideos.getThumbnail());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getS3Url())
      .append(getCfUrl())
      .append(getThumbnail())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TrendingVideos {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("s3_url=" + String.valueOf(getS3Url()) + ", ")
      .append("cf_url=" + String.valueOf(getCfUrl()) + ", ")
      .append("thumbnail=" + String.valueOf(getThumbnail()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static TrendingVideos justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new TrendingVideos(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      s3_url,
      cf_url,
      thumbnail);
  }
  public interface BuildStep {
    TrendingVideos build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep title(String title);
    BuildStep s3Url(String s3Url);
    BuildStep cfUrl(String cfUrl);
    BuildStep thumbnail(String thumbnail);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String title;
    private String s3_url;
    private String cf_url;
    private String thumbnail;
    @Override
     public TrendingVideos build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TrendingVideos(
          id,
          title,
          s3_url,
          cf_url,
          thumbnail);
    }
    
    @Override
     public BuildStep title(String title) {
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep s3Url(String s3Url) {
        this.s3_url = s3Url;
        return this;
    }
    
    @Override
     public BuildStep cfUrl(String cfUrl) {
        this.cf_url = cfUrl;
        return this;
    }
    
    @Override
     public BuildStep thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String s3Url, String cfUrl, String thumbnail) {
      super.id(id);
      super.title(title)
        .s3Url(s3Url)
        .cfUrl(cfUrl)
        .thumbnail(thumbnail);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder s3Url(String s3Url) {
      return (CopyOfBuilder) super.s3Url(s3Url);
    }
    
    @Override
     public CopyOfBuilder cfUrl(String cfUrl) {
      return (CopyOfBuilder) super.cfUrl(cfUrl);
    }
    
    @Override
     public CopyOfBuilder thumbnail(String thumbnail) {
      return (CopyOfBuilder) super.thumbnail(thumbnail);
    }
  }
  
}
