package com.android.demo;

/**
 * Created by Sukrut on 9/9/2017.
 */

public class DataObject {
    String name;
    String gender;
    String age;
    String occupation;
    String description;
    String address;
    DataObject()
    {

    }

    DataObject(String name,String gender,String age,String occupation,String description,String address)
    {
        this.name=name;
        this.gender=gender;
        this.age=age;
        this.occupation=occupation;
        this.description=description;
        this.address=address;
    }

    public  String getName()

    {
        return name;
    }

    public String getGender()

    {
        return gender;
    }

    public String getAge()

    {
        return age;
    }
    public String getOccupation()

    {
        return occupation;
    }
    public String getDescription()

    {
        return description ;
    }
    public String getAddress()

    {
        return address;
    }
    public void setName(String name)

    {
        this.name=name;
    }

    public void setAge(String age)

    {
        this.age = age;
    }

    public void setOccupation(String occupation)

    {
        this.occupation = occupation;
    }

    public void setDescription(String description)

    {
        this.description = description;
    }

    public void setAddress(String address)

    {
        this.address = address;
    }

    public void setGender(String gender)

    {
        this.gender = gender;
    }
}
