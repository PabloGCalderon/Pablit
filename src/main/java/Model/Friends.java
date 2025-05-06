/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Objects;

public class Friends 
{
    private String username;
    private String friendUsername;

    public Friends() {
        this.username = "";
        this.friendUsername = "";
    }

    public Friends(String username, String friendUsername) {
        this.username = username;
        this.friendUsername = friendUsername;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }
    
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Friends)) {
            return false;
        }
        Friends f = (Friends) o;
        return username.equals(f.username) && friendUsername.equals(f.friendUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, friendUsername);
    }

    @Override
    public String toString() {
        return "Friend{" + "username=" + username + ", friendUsername=" + friendUsername + '}';
    }
    
}
