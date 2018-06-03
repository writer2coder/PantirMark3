package pantriapp.russellmcdonald.com.pantirmark3.model;

public class NewUser {
    public String firstName;
    public String lastName;
    public String eMail;






    public NewUser() {
    }

    public NewUser(String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
