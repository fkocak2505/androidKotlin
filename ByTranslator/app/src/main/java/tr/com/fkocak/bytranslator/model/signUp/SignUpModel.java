package tr.com.fkocak.bytranslator.model.signUp;

public class SignUpModel {

    private String email;
    private String password;
    private String uuid;
    private String nameSurname;
    private String birthday;

    public SignUpModel() {

    }

    public SignUpModel(String email, String password, String uuid, String nameSurname, String birthday) {
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.nameSurname = nameSurname;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
