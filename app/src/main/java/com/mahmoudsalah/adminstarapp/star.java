
package com.mahmoudsalah.adminstarapp;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class star
{
  private String ownerId;
  private java.util.Date created;
  private String name;
  private String age;
  private java.util.Date updated;
  private String image;
  private String life;
  private String objectId;
  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getAge()
  {
    return age;
  }

  public void setAge( String age )
  {
    this.age = age;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage( String image )
  {
    this.image = image;
  }

  public String getLife()
  {
    return life;
  }

  public void setLife( String life )
  {
    this.life = life;
  }

  public String getObjectId()
  {
    return objectId;
  }

                                                    
  public star save()
  {
    return Backendless.Data.of( star.class ).save( this );
  }

  public void saveAsync( AsyncCallback<star> callback )
  {
    Backendless.Data.of( star.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( star.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( star.class ).remove( this, callback );
  }

  public static star findById( String id )
  {
    return Backendless.Data.of( star.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<star> callback )
  {
    Backendless.Data.of( star.class ).findById( id, callback );
  }

  public static star findFirst()
  {
    return Backendless.Data.of( star.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<star> callback )
  {
    Backendless.Data.of( star.class ).findFirst( callback );
  }

  public static star findLast()
  {
    return Backendless.Data.of( star.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<star> callback )
  {
    Backendless.Data.of( star.class ).findLast( callback );
  }

  public static List<star> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( star.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<star>> callback )
  {
    Backendless.Data.of( star.class ).find( queryBuilder, callback );
  }
}