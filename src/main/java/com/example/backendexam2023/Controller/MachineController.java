package com.example.backendexam2023.Controller;

import com.example.backendexam2023.Records.OperationResult;
import com.example.backendexam2023.Records.DeleteResult;
import com.example.backendexam2023.Model.Machine.Machine;
import com.example.backendexam2023.Model.Machine.MachineRequest;
import com.example.backendexam2023.Util.ResponseEntityHelper;
import com.example.backendexam2023.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machine")
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService){
        this.machineService = machineService;
    }

    @PostMapping
    public ResponseEntity<Object> createMachine(@RequestBody MachineRequest machineRequest){

        OperationResult<Object> operationResult = machineService.createMachine(machineRequest);

        if(operationResult.success()){
            return new ResponseEntity<>(operationResult.createdObject(),HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(operationResult.errorMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id){
        Machine machine = machineService.getMachineById(id);

        if (machine == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(machine, HttpStatus.OK);

    }

    @GetMapping("/page/{pageNumber}")
    public List<Machine> getMachinesByPage(@PathVariable int pageNumber) {
        return machineService.getMachinesPageable(pageNumber);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMachineById(@PathVariable Long id){

        DeleteResult deleteResult = machineService.deleteMachineById(id);

        return ResponseEntityHelper.getResponseForDelete(deleteResult);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMachine(@PathVariable Long id, @RequestBody Machine newMachine){
        try{
            return new ResponseEntity<>(machineService.updateMachine(id, newMachine), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }








}
