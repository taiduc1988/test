package com.example.easywork.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.easywork.common.enums.Direction;
import com.example.easywork.common.enums.OrderBy;
import com.example.easywork.exception.PaginationSortingException;
import com.example.easywork.exception.ResourceNotFoundException;
import com.example.easywork.model.Work;
import com.example.easywork.repository.WorkRepository;
import com.example.easywork.service.WorkService;
import com.example.easywork.common.Constants.*;

@RestController
@RequestMapping(Url.API_URL)
public class WorkController {

    @Autowired
    WorkRepository workRepository;

    @Autowired
    private WorkService workService;

    // Get All works
    @GetMapping(Url.WORKS_URL)
    public List<Work> getAllworks() {
        return workRepository.findAll();
    }

    // Create a new work
    @PostMapping(Url.WORKS_URL)
    public Work creatework(@Valid @RequestBody Work work) {
        return workRepository.save(work);
    }

    @GetMapping(value = Url.WORKS_URL, params = { "orderBy", "direction", "page", "size" })
    @ResponseBody
    public Page<Work> search(@RequestParam("orderBy") String orderBy, @RequestParam("direction") String direction,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        if (!(direction.equals(Direction.ASCENDING.getDirectionCode())
                || direction.equals(Direction.DESCENDING.getDirectionCode()))) {
            throw new PaginationSortingException("Invalid sort direction");
        }

        if (!(orderBy.equals(OrderBy.ID.getOrderByCode()) || orderBy.equals(OrderBy.WORK_NAME.getOrderByCode()))) {
            throw new PaginationSortingException("Invalid orderBy condition");
        }
        Page<Work> list = workService.search(orderBy, direction, page, size);
        return list;
    }

    // Get a Single work
    @GetMapping(Url.WORKS_URL + Url.DETAIL_URL)
    public Work getworkById(@PathVariable(value = "id") Long workId) {
        return workRepository.findById(workId).orElseThrow(() -> new ResourceNotFoundException("work", "id", workId));
    }

    // Update a work
    @PutMapping(Url.WORKS_URL + Url.DETAIL_URL)
    public Work updatework(@PathVariable(value = "id") Long workId, @Valid @RequestBody Work workDetails) {

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("work", "id", workId));
        work.setWorkName(workDetails.getWorkName());
        work.setStartingDate(workDetails.getStartingDate());
        work.setEndingDate(workDetails.getEndingDate());
        work.setStatus(workDetails.getStatus());

        Work updatedwork = workRepository.save(work);
        return updatedwork;
    }

    // Delete a work
    @DeleteMapping(Url.WORKS_URL + Url.DETAIL_URL)
    public ResponseEntity<?> deletework(@PathVariable(value = "id") Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("work", "id", workId));

        workRepository.delete(work);

        return ResponseEntity.ok().build();
    }

}
