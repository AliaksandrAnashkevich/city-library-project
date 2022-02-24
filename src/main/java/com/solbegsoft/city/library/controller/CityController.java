package com.solbegsoft.city.library.controller;

import com.solbegsoft.city.library.dto.request.CityRequest;
import com.solbegsoft.city.library.dto.response.CityResponse;
import com.solbegsoft.city.library.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("{id}")
    public CityResponse getById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @GetMapping(params = {"page", "size"})
    public Page<CityResponse> getByPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        return cityService.findByPage(page, size);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
    public CityResponse update(@PathVariable Long id, @RequestBody CityRequest cityRequest) {
        return cityService.update(id, cityRequest);
    }
}
