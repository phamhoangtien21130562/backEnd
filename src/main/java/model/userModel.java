
package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class userModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String address;
    private String birthday;
    private String phone;
    private String username;
    private String password;
    private int role;
    private String image;



}
