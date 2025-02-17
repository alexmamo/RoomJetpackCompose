package ro.alexmamo.roomjetpackcompose.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import ro.alexmamo.roomjetpackcompose.data.network.BookDb
import ro.alexmamo.roomjetpackcompose.domain.repository.BookRepository
import ro.alexmamo.roomjetpackcompose.presentation.book_list.BookListViewModel
import ro.alexmamo.roomjetpackcompose.data.repository.FakeBookRepositoryImpl

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class AppModuleTest {
    @Provides
    fun provideBookDb() = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext<Context>(),
        BookDb::class.java
    ).build()

    @Provides
    fun provideBookDao(
        bookDb: BookDb
    ) = bookDb.bookDao

    @Provides
    fun provideBookRepository(): BookRepository = FakeBookRepositoryImpl()

    @Provides
    fun provideBookListViewModel(
        repo: BookRepository
    ) = BookListViewModel(repo)
}