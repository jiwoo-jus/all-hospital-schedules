package jiwjus.AllHospitalSchedules.controller;

import jiwjus.AllHospitalSchedules.dto.*;
import jiwjus.AllHospitalSchedules.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/region1")
    public ResponseEntity<List<Region1Dto>> findAllRegion1s(){
        return ResponseEntity.ok(userService.findAllRegion1s());
    }

    @GetMapping("/region2/{region1Id}")
    public ResponseEntity<List<Region2Dto>> findRegion2sByRegion1(@PathVariable Long region1Id){
        return ResponseEntity.ok(userService.findRegion2sByRegion1(region1Id));
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentDto>> findAllDepartments(){
        return ResponseEntity.ok(userService.findAllDepartments());
    }


}
