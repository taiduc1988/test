package com.example.easywork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.easywork.model.Work;
import com.example.easywork.repository.WorkRepository;

@Service
public class WorkService {
    @Autowired
    private WorkRepository noteRepository;;

    public Page<Work> search(String orderBy, String direction, int page, int size) {
        Page<Work> data = null;
        data = noteRepository.findAll(PageRequest.of(page, size,
                (direction.equals("ASC") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending())));
        return data;
    }
}
