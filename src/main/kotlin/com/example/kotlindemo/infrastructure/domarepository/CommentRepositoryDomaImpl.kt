package com.example.kotlindemo.infrastructure.domarepository

import com.example.kotlindemo.domain.model.Comment
import com.example.kotlindemo.domain.repository.CommentRepository
import com.example.kotlindemo.infrastructure.doma.dao.CommentDomaDao
import com.example.kotlindemo.infrastructure.doma.entity.CommentDomaEntity
import org.springframework.stereotype.Repository

@Repository
class CommentRepositoryDomaImpl(
        private val commentDomaDao: CommentDomaDao
) : CommentRepository {
    override fun selectAllByCustomerId(customerId: Int): List<Comment> {
        return commentDomaDao.selectAllByCustomerId(customerId).map{ _mapToModel(it)}
    }

    override fun create(comment: Comment): Int {
        val domaEntity = _mapToDomaEntity(comment)
        commentDomaDao.insert(domaEntity)
        return domaEntity.id
    }

    private fun _mapToModel(domaEntity: CommentDomaEntity) : Comment {
        return Comment(
                id = domaEntity.id,
                content = domaEntity.content,
                customerId = domaEntity.customerId
        )
    }

    private fun _mapToDomaEntity(comment: Comment) : CommentDomaEntity {
        return CommentDomaEntity().also {
            it.id = comment.id
            it.content = comment.content
            it.customerId = comment.customerId
        }
    }
}