package tr.com.fkocak.bytranslator.model.signIn;

public class SignInModel {

    private String email;
    private String password;

    public SignInModel() {

    }

    public SignInModel(String email, String password) {
        this.email = email;
        this.password = password;
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
}