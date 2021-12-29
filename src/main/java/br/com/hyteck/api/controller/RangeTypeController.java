package br.com.hyteck.api.controller;

import br.com.hyteck.api.enums.RangeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("range-type")
public class RangeTypeController {

    @GetMapping
    public List<String> findAll(){
        return Arrays.stream(RangeType.values()).map(Enum::name).collect(Collectors.toList());
    }
}

