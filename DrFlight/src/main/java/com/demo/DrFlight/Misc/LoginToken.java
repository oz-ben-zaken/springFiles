package com.demo.DrFlight.Misc;

public class LoginToken {
    private final long id;
    private final String name;
    private final int role;

    public LoginToken(long id, String name, int role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    /**
     * Returns (int) role.
     */
    public int getRole() {
        return  this.role;
    }

    /**
     * Returns (String) name.
     */
    public String getName() {
        return  this.name;
    }

    /**
     * Returns (long) id.
     */
    public long GetId() {
        return  this.id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + " role=" + role;
    }
}
