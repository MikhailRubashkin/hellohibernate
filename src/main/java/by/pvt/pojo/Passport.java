package by.pvt.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="passport")
public class Passport implements Serializable {

    private static final long serialVersionUID =1256L;

    @Id
    @GenericGenerator( name = "id", strategy = "increment")
    private int id;

    private String series;
    private String number;

    @OneToOne(optional=false, mappedBy="passport")
    private Person owner;

    public String getSeries (){
        return series;
    }

    public void setSeries ( String series ){
        this.series = series;
    }

    public String getNumber (){
        return number;
    }

    public void setNumber ( String number ){
        this.number = number;
    }

    public Person getOwner (){
        return owner;
    }

    public void setOwner ( Person owner ){
        this.owner = owner;
    }

    public int getId (){
        return id;
    }

    public void setId ( int id ){
        this.id = id;
    }
}
