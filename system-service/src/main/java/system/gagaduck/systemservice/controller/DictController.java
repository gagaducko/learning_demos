package system.gagaduck.systemservice.controller;

import system.gagaduck.systemservice.model.DictItem;
import system.gagaduck.systemservice.repository.DictRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 字典管理
@RestController
@CrossOrigin
@RequestMapping(value = "/dict")
public class DictController {
    @Resource
    private DictRepository dictRepository;

    @GetMapping("/allDict")
    public List<DictItem> getAllDict(){
        return dictRepository.findAll();
    }

    @PostMapping("/addDict")
    public boolean addDict(@RequestBody DictItem dictItem){
        dictRepository.save(dictItem);
        return true;
    }

    @PostMapping("/modifyDict")
    public boolean modifyDict(@RequestBody DictItem dictItem){
        dictRepository.save(dictItem);
        return true;
    }

    @DeleteMapping("/deleteDict")
    public boolean deleteDict(@RequestParam("id") Integer id ){
        dictRepository.delete(dictRepository.findOneById(id));
        return true;
    }
}
