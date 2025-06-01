package com.summerproject.Tabithaa.Tabithaa.controllers

import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkRequest
import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkResponse
import com.summerproject.Tabithaa.Tabithaa.model.Bookmark
import com.summerproject.Tabithaa.Tabithaa.repository.BookmarkRepository
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bookmarks")

class BookmarkController(
    private val repository : BookmarkRepository
) {

    @PostMapping
    fun save(@RequestBody request: BookmarkRequest): ResponseEntity<BookmarkResponse> {
        val bookmark = repository.save(
            Bookmark(
                userId = "demo-user",
                title = request.title,
                url = request.url,
                folder = request.folder,
                tags = request.tags
            )
        )
        return bookmark.toResponse()
    }










    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        repository.deleteById(ObjectId(id))
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun findBookmarks(
        @RequestParam userId: String,
        @RequestParam(required = false) folder: String?,
        @RequestParam(required = false) tag: String?,
        @RequestParam(defaultValue = "desc") sort: String
    ): ResponseEntity<List<BookmarkResponse>> {

        var bookmarks = repository.findByUserId(userId)

        if (folder != null) {
            bookmarks = bookmarks.filter { it.folder == folder }
        }

        if (tag != null) {
            bookmarks = bookmarks.filter { it.tags.contains(tag) }
        }

        bookmarks = when (sort.lowercase()) {
            "asc" -> bookmarks.sortedBy { it.createdAt }
            else -> bookmarks.sortedByDescending { it.createdAt }
        }

        return ResponseEntity.ok(bookmarks.map { it.toDto() })
    }


    //the mapper guys are below



    fun Bookmark.toDto(): BookmarkResponse {
        return BookmarkResponse(
            id = this.id ?: "no-id",
            url = this.url,
            title = this.title,
            folder = this.folder,
            tags = this.tags,
            createdAt = this.createdAt.toString()
        )
    }

    private fun Bookmark.toResponse(): ResponseEntity<BookmarkResponse> {
        return ResponseEntity.ok(
            BookmarkResponse(
                id = this.id ?: "", // or throw if null
                url = this.url,
                title = this.title,
                folder = this.folder,
                tags = this.tags,
                createdAt = this.createdAt.toString()
            )
        )
    }




}