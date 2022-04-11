package com.demo.DrFlight.Poco;

import java.io.File;

public class Country implements Poco{
    public int id;
    public String name;
    public String flagUrl = "src\\main\\resources\\FlagImg\\def_flag.jpg";;

    public Country(int id, String name, String imgName) {
        this.id = id;
        this.name = name;
        if(!name.isEmpty()){
            String url= "src\\main\\resources\\FlagImg\\"+imgName;
            File file = new File(url);
            if (file.exists()){
                this.flagUrl = url;
                System.out.println("you are not an idiot, good job");
            }
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + flagUrl + '\'' +
                '}';
    }
}
