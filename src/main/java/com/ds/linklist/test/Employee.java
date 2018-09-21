package com.ds.linklist.test;

import java.io.Serializable;

public class Employee implements Serializable{

    private static final long serialVersionUID = 1L;
    private String name;
    private Integer id;
    private String company;
    private Sex gender;
    private Double salary;
    transient private String password;

    public enum Sex
    {
        Male, Female
    };


    public Employee()
    {
        super();
    }


    public Employee(String name, Integer id, String company)
    {
        super();
        this.name = name;
        this.id = id;
        this.company = company;
    }


    public Employee(String name, Integer id, String company, Sex gender)
    {
        super();
        this.name = name;
        this.id = id;
        this.company = company;
        this.gender = gender;
    }


    public Employee(String name, Integer id, String company, Sex gender, Double salary)
    {
        super();
        this.name = name;
        this.id = id;
        this.company = company;
        this.gender = gender;
        this.salary = salary;
    }


    public Employee(String name, Integer id, String company, Sex gender, Double salary, String password)
    {
        super();
        this.name = name;
        this.id = id;
        this.company = company;
        this.gender = gender;
        this.salary = salary;
        this.password = password;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getCompany()
    {
        return company;
    }


    public void setCompany(String company)
    {
        this.company = company;
    }


    public Sex getGender()
    {
        return gender;
    }


    public void setGender(Sex gender)
    {
        this.gender = gender;
    }


    public Double getSalary()
    {
        return salary;
    }


    public void setSalary(Double salary)
    {
        this.salary = salary;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


   
    @Override
    public String toString()
    {
        return "Employee [name=" + name + ", id=" + id + ", company=" + company + ", gender=" + gender + ", salary=" + salary + ", password=" + password + "]";
    }


    public void salaryIncrement(Double percentage)
    {
        this.salary *= (100 + percentage) / 100;
    }


    public Employee finById(int id)
    {
        return this;
    }
}
