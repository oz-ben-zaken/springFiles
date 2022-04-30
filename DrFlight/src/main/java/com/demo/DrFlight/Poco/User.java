package com.demo.DrFlight.Poco;

import java.io.File;

public class User implements Poco {
    public long id;
    public String username;
    public String password;
    public String email;
    public int userRole;
    public String imgUrl = "src\\main\\resources\\UserImg\\def_user.png";


    public User(long id, String username, String password, String email, int userRole, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        if (name != null){
            String url = "src\\main\\resources\\UserImg\\" + name;
            File file = new File(url);
            if (file.exists())
                this.imgUrl = url;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", url=" + imgUrl +
                '}';
    }
}
