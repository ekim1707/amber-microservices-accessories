package com.galvanize.accessoriesgradle.controllers;

import com.galvanize.accessoriesgradle.entities.Accessories;
import com.galvanize.accessoriesgradle.repositories.AccessoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping(value = "/accessories")
public class AccessoriesController {

    @Autowired
    private AccessoriesRepository accessoriesRepository;

    @GetMapping
    public @ResponseBody Iterable<Accessories> getAllAccessories() { return accessoriesRepository.findAll(); }

    @GetMapping("/{id}")
    public Accessories getAccessoriesById(@PathVariable final Integer id) {
        Accessories accessoryToFind = accessoriesRepository.findById(id).orElse(null);

        if (accessoryToFind == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Accessory not found.");
        } else {
            return accessoryToFind;
        }
    }

    @PostMapping
    public Accessories createAccessories(@RequestBody Accessories accessory) {
        accessory.setCan_delete(true);
        return accessoriesRepository.save(accessory);
    }

    @PatchMapping("/{id}")
    public Accessories updateAccessoriesRating(@PathVariable final Integer id, @RequestBody final Accessories accessoriesSent) {
        Accessories accessoryToEdit = accessoriesRepository.findById(id).orElse(null);

        if (accessoryToEdit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Headphone not found.");
        }

        accessoryToEdit.setMake(accessoriesSent.getMake());
        accessoryToEdit.setModel(accessoriesSent.getModel());
        accessoryToEdit.setWeight(accessoriesSent.getWeight());
        accessoryToEdit.setLength(accessoriesSent.getLength());
        accessoryToEdit.setWidth(accessoriesSent.getWidth());
        accessoryToEdit.setHeight(accessoriesSent.getHeight());
        accessoryToEdit.setRating(accessoriesSent.getRating());
        accessoryToEdit.setFullPrice(accessoriesSent.getFullPrice());
        accessoryToEdit.setImageURL(accessoriesSent.getImageURL());
        accessoryToEdit.setCan_delete(accessoriesSent.isCan_delete());

        return accessoriesRepository.save(accessoryToEdit);
    }

    @DeleteMapping("/{id}")
    public void deleteAccessoriesById(@PathVariable final Integer id) {
        Accessories accessoryToDelete = accessoriesRepository.findById(id).orElse(null);

        if (accessoryToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Accessory not found.");
        } else if (!accessoryToDelete.isCan_delete()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Accessory cannot be deleted.");
        } else {
            accessoriesRepository.deleteById(id);
        }
    }
}
