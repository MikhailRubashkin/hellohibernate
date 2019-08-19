package by.pvt.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "Person")
public class Person implements Serializable {


    private static final long serialVersionUID = 7676610201971322455L;

    @Id
    @GenericGenerator ( name = "id", strategy = "increment")
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Date dateOfBirth;
    @Column
    private char gender;
    @OneToOne (optional=false, cascade= CascadeType.ALL)
    private Passport passport;

    public Passport getPassport (){
        return passport;
    }

    public void setPassport ( int i ){
        this.passport = passport;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName ( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName ( String lastName ) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth () {
        return dateOfBirth;
    }

    public void setDateOfBirth ( Date dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender () {
        return gender;
    }

    public void setGender ( char gender ) {
        this.gender = gender;
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (gender != person.gender) return false;
        if (!firstName.equals ( person.firstName )) return false;
        if (!lastName.equals ( person.lastName )) return false;
        return dateOfBirth != null ? dateOfBirth.equals ( person.dateOfBirth ) : person.dateOfBirth == null;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + firstName.hashCode ( );
        result = 31 * result + lastName.hashCode ( );
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode ( ) : 0);
        result = 31 * result + (int) gender;
        return result;
    }

    public static void main ( String[] args ) {
        System.out.println ( UUID.randomUUID ().toString () );
    }
}
