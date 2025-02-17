package ro.alexmamo.roomjetpackcompose.domain.model

sealed class BookError {
    object EmptyTitle : BookError()
    object EmptyAuthor : BookError()
}