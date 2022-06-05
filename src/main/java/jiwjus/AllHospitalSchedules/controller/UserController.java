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
    public ResponseEntity<List<Region1Dto>> findRegion1s(){
        return ResponseEntity.ok(userService.findRegion1s());
    }

    @GetMapping("/region2/{region1Id}")
    public ResponseEntity<List<Region2Dto>> findRegion2sByRegion1Id(@PathVariable Long region1Id){
        return ResponseEntity.ok(userService.findRegion2sByRegion1Id(region1Id));
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentDto>> findDepartments(){
        return ResponseEntity.ok(userService.findDepartments());
    }

    @GetMapping("/hospital-department")
    public ResponseEntity<List<HospitalDepartmentDto>> findHospitalDepartmentsByRegion2sAndDepartment(@RequestBody HospitalDepartmentRequestDto hospitalDepartmentRequestDto){
        return ResponseEntity.ok(userService.findHospitalDepartmentsByDepartmentIdAndRegion2Ids(hospitalDepartmentRequestDto));
    }
}
