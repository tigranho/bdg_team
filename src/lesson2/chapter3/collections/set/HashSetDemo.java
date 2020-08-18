package lesson2.chapter3.collections.set;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static lesson2.chapter3.collections.set.Account.Gender.MALE;

/**
 * @author Hrach
 */

public class HashSetDemo {
    public static void main(String[] args) {
        Account john = new Account.AccountBuilder(LocalDate.now())
                .firstName("John")
                .lastName("Smith")
                .age(23)
                .gender(MALE)
                .address(new Account.Address
                        .AddressBuilder("UK", "Cardiff", "Bridge Stone 5")
                        .region("North new land")
                        .postalCode(4421)
                        .build()
                ).build();
        Account harry = new Account.AccountBuilder(LocalDate.now())
                .firstName("Harry")
                .lastName("Browne")
                .age(31)
                .gender(MALE)
                .address(new Account.Address
                        .AddressBuilder("Scotland", "Seltic", "Greenwood 12/3")
                        .region("Amenden")
                        .postalCode(1223)
                        .build()
                ).build();
        Set<Account> accounts = new HashSet<>();
        accounts.add(john);
        accounts.add(harry);
        accounts.forEach(System.out::println);
    }
}
