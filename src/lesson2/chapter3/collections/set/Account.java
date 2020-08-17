package lesson2.chapter3.collections.set;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Account {

    private String firstName;
    private String lastName;
    private int age;
    private final LocalDate createdDate;
    private Gender gender;
    private Address address;

    public Account(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    private Account(LocalDate localDate, AccountBuilder builder) {
        this.createdDate = localDate;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.gender = builder.gender;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("age=" + age)
                .add("createdDate=" + createdDate)
                .add("gender=" + gender)
                .add("address=" + address)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return age == account.age &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName) &&
                Objects.equals(createdDate, account.createdDate) &&
                gender == account.gender &&
                Objects.equals(address, account.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, createdDate, gender, address);
    }

    public static class AccountBuilder {
        private String firstName;
        private String lastName;
        private int age;
        private final LocalDate createdDate;
        private Gender gender;
        private Address address;

        public AccountBuilder(LocalDate createdDate) {
            this.createdDate = createdDate;
        }

        public AccountBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AccountBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AccountBuilder age(int age) {
            this.age = age;
            return this;
        }

        public AccountBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public AccountBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Account build() {
            return new Account(LocalDate.now(), this);
        }
    }

    enum Gender {
        MALE, FEMALE
    }

    public static class Address {
        private String country;
        private String region;
        private String city;
        private String street;
        private int postalCode;

        private Address(AddressBuilder addressBuilder) {
            this.city = addressBuilder.city;
            this.country = addressBuilder.country;
            this.postalCode = addressBuilder.postalCode;
            this.street = addressBuilder.street;
            this.region = addressBuilder.region;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Address address = (Address) o;
            return postalCode == address.postalCode &&
                    Objects.equals(country, address.country) &&
                    Objects.equals(region, address.region) &&
                    Objects.equals(city, address.city) &&
                    Objects.equals(street, address.street);
        }

        @Override
        public int hashCode() {
            return Objects.hash(country, region, city, street, postalCode);
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(int postalCode) {
            this.postalCode = postalCode;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                    .add("country='" + country + "'")
                    .add("region='" + region + "'")
                    .add("city='" + city + "'")
                    .add("street='" + street + "'")
                    .add("postalCode=" + postalCode)
                    .toString();
        }

        public static class AddressBuilder {
            private final String country;
            private String region;
            private final String city;
            private final String street;
            private int postalCode;

            public AddressBuilder(String country, String city, String street) {
                this.country = country;
                this.city = city;
                this.street = street;
            }

            public AddressBuilder region(String region) {
                this.region = region;
                return this;
            }

            public AddressBuilder postalCode(int postalCode) {
                this.postalCode = postalCode;
                return this;
            }

            public Address build() {
                return new Address(this);
            }
        }
    }
}
