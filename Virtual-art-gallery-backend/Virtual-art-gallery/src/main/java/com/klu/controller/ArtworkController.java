package com.klu.controller;

import com.klu.model.Artwork;
import com.klu.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @PostMapping
    public Artwork addArtwork(@RequestBody Artwork artwork) {
        return artworkService.addArtwork(artwork);
    }

    @GetMapping
    public List<Artwork> getAll() {
        return artworkService.getAllArtworks();
    }

    @PutMapping("/approve/{id}")
    public Artwork approve(@PathVariable Long id) {
        return artworkService.approveArtwork(id);
    }

    @PutMapping("/sold/{id}")
    public Artwork markAsSold(@PathVariable Long id) {
        Artwork art = artworkService.getArtworkById(id);
        if (art != null) {
            art.setSold(true);
            return artworkService.addArtwork(art);
        }
        return null;
    }
}
