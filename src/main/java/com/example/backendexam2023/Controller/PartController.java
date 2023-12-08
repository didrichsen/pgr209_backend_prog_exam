package com.example.backendexam2023.Controller;

import com.example.backendexam2023.Records.OperationResult;
import com.example.backendexam2023.Records.DeleteResult;
import com.example.backendexam2023.Model.Part;
import com.example.backendexam2023.Util.ResponseEntityHelper;
import com.example.backendexam2023.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {

    private final PartService partService;

    @Autowired
    public PartController(PartService partService){
        this.partService = partService;
    }

    @PostMapping
    public ResponseEntity<Object> createPart(@RequestBody Part part){

        OperationResult<Object> operationResult = partService.createPart(part);

        if(operationResult.success()){
            return new ResponseEntity<>(operationResult.createdObject(),HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(operationResult.errorMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public List<Part> getAllParts(){
        return partService.getAllParts();
    }
    @GetMapping("/page/{pageNumber}")
    public List<Part> getPartsByPage(@PathVariable int pageNumber) {
        return partService.getPartsPageable(pageNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable Long id){
        Part part = partService.getPartById(id);

        if(part == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(part,HttpStatus.OK);
    }

    //Return Bad Request if the part is not correct formated.

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePart(@PathVariable Long id){

        DeleteResult deleteResult = partService.deletePartById(id);

        return ResponseEntityHelper.getResponseForDelete(deleteResult);

    }

    @PutMapping
    public Part updatePart(@RequestBody Part part){
        return partService.updatePart(part);
    }




}
