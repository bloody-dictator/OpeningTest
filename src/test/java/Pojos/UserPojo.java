package Pojos;

public class UserPojo {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public UserPojo(int id, String email, String firstName, String lastName, String avatar){
        this.id = id;
        this.email = email;
        this.first_name = firstName;
        this.last_name = lastName;
        this.avatar = avatar;
    }
}

