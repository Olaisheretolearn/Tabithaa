package com.summerproject.Tabithaa.Tabithaa.service

import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkRequest
import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkResponse
import com.summerproject.Tabithaa.Tabithaa.dto.BookmarkUpdateRequest
import com.summerproject.Tabithaa.Tabithaa.model.Bookmark
import com.summerproject.Tabithaa.Tabithaa.repository.BookmarkRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    private val repository: BookmarkRepository
) {

    fun create(userId: String, request: BookmarkRequest): BookmarkResponse {
        val bookmark = Bookmark(
            userId = userId,
            title = request.title,
            url = request.url,
            folder = request.folder,
            tags = request.tags
        )
        return repository.save(bookmark).toDto()
    }


    fun delete(id: String) {
        repository.deleteById(ObjectId(id))
    }

    fun find(userId: String, folder: String?, tag: String?, sort: String): List<BookmarkResponse> {
        var bookmarks = repository.findByUserId(userId)

        if (folder != null) {
            bookmarks = bookmarks.filter { it.folder.equals(folder, ignoreCase = true) }
        }

        if (tag != null) {
            bookmarks = bookmarks.filter { tag.lowercase() in it.tags.map { t -> t.lowercase() } }
        }


        bookmarks = when (sort.lowercase()) {
            "asc" -> bookmarks.sortedBy { it.createdAt }
            else -> bookmarks.sortedByDescending { it.createdAt }
        }

        return bookmarks.map { it.toDto() }
    }

    fun update(id: String, update: BookmarkUpdateRequest): BookmarkResponse {
        val bookmark = repository.findById(ObjectId(id)).orElseThrow { RuntimeException("Bookmark not found") }

        val updated = bookmark.copy(
            title = update.title ?: bookmark.title,
            url = update.url ?: bookmark.url,
            folder = update.folder ?: bookmark.folder,
            tags = update.tags ?: bookmark.tags
        )

        return repository.save(updated).toDto()
    }

    // Mapping (could be moved to a mapper later)
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
}
