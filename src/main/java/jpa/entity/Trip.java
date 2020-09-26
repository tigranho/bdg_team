package jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table
public class Trip {

    @Id
    private int id;
    private int trip_number;
    private Timestamp time;
    private int address_id;
    private int passenger_id;
}
