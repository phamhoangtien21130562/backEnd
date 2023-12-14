
package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor()
public class userModel {
    private String id;
    private String name;
    private String email;
    private String address;
    private String birthday;
    private String phone;
    private String username;
    private String password;
    private int role;


}
