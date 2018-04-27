package shoppingcart.sk.fmph.uniba.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="firstname", nullable=false)
    @NotEmpty
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @NotEmpty
    @Email
    @Column(name="email", nullable=false, unique=true)
    private String email;
    @NotEmpty
    @Column(name="password", nullable=false)
    private String password;
}
