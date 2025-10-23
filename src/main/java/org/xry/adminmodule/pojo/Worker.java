package org.xry.adminmodule.pojo;

public class Worker {
    private Integer id;
    private String name;
    private String inDate;
    private Integer inAge;
    private Integer salary;
    private String phone;
    private String state;
    private String position;

    public Worker() {}

    public Worker(Integer id, String name, String inDate, Integer inAge, Integer salary, String phone, String state, String position) {
        this.id = id;
        this.name = name;
        this.inDate = inDate;
        this.inAge = inAge;
        this.salary = salary;
        this.phone = phone;
        this.state = state;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public Integer getInAge() {
        return inAge;
    }

    public void setInAge(Integer inAge) {
        this.inAge = inAge;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inDate='" + inDate + '\'' +
                ", inAge=" + inAge +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
