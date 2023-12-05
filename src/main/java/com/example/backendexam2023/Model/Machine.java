package com.example.backendexam2023.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Machine {

    @Id
    @GeneratedValue(generator = "machine_generator")
    @SequenceGenerator(name = "machine_generator", sequenceName = "machine_seq", initialValue = 1, allocationSize = 1)
    @Column(name="machine_id")
    private Long machineId = 0L;

    @Column(name = "machine_name")
    private String machineName;

    @OneToMany
    @JoinColumn(name = "subassembly_id")
    private List<Subassembly> subassemblies = new ArrayList<>();

    public Machine(String machineName){
        this.machineName = machineName;
    }



}
