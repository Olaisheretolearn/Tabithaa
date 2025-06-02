package com.summerproject.Tabithaa.Tabithaa.controllers

import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkRequest
import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkResponse
import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkUpdateRequest
import com.summerproject.Tabithaa.Tabithaa.model.Bookmark
import com.summerproject.Tabithaa.Tabithaa.repository.BookmarkRepository
import com.summerproject.Tabithaa.Tabithaa.service.BookmarkService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @PostMapping
    fun save(@RequestBody request: BookmarkRequest): ResponseEntity<BookmarkResponse> {
        return ResponseEntity.ok(bookmarkService.create(request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        bookmarkService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun findBookmarks(
        @RequestParam userId: String,
        @RequestParam(required = false) folder: String?,
        @RequestParam(required = false) tag: String?,
        @RequestParam(defaultValue = "desc") sort: String
    ): ResponseEntity<List<BookmarkResponse>> {
        return ResponseEntity.ok(bookmarkService.find(userId, folder, tag, sort))
    }

    @PutMapping("/{id}")
    fun updateBookmark(
        @PathVariable id: String,
        @RequestBody update: BookmarkUpdateRequest
    ): ResponseEntity<BookmarkResponse> {
        return ResponseEntity.ok(bookmarkService.update(id, update))
    }
}
