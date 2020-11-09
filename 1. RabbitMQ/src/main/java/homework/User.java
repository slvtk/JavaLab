package homework;

public class User {

    private String name;
    private String surname;
    private Integer idNumber;
    private Integer age;
    private Integer idDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getAge() {
        return age;
    }

    public User(String name, String surname, Integer idNumber, Integer age, Integer idDate) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.age = age;
        this.idDate = idDate;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIdDate() {
        return idDate;
    }

    public void setIdDate(Integer idDate) {
        this.idDate = idDate;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", idNumber=" + idNumber +
                ", age=" + age +
                ", idDate=" + idDate;
    }
}
