package com.bookrent.book.service;

import com.bookrent.book.dto.BookDto;
import com.bookrent.book.model.Book;
import com.bookrent.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.AbstractMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDto createBook(BookDto bookDto) {
        Book book = toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return toDto(savedBook);
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::toDto)
                .orElse(null); // Or throw an exception
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDto.getTitle());
                    existingBook.setAuthor(bookDto.getAuthor());
                    existingBook.setIsbn(bookDto.getIsbn());
                                        existingBook.setPublicationYear(bookDto.getPublicationYear());
                    existingBook.setLatitude(bookDto.getLatitude());
                    existingBook.setLongitude(bookDto.getLongitude());
                    Book updatedBook = bookRepository.save(existingBook);
                    return toDto(updatedBook);
                })
                .orElse(null); // Or throw an exception
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> findBooksNearby(double latitude, double longitude, double distanceKm) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getLatitude() != null && book.getLongitude() != null)
                .map(book -> {
                    double distance = calculateDistance(latitude, longitude, book.getLatitude(), book.getLongitude());
                    return new AbstractMap.SimpleEntry<>(book, distance);
                })
                .filter(entry -> entry.getValue() <= distanceKm)
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Haversine formula
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .latitude(book.getLatitude())
                .longitude(book.getLongitude())
                .build();
    }

    private Book toEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .isbn(bookDto.getIsbn())
                .publicationYear(bookDto.getPublicationYear())
                .latitude(bookDto.getLatitude())
                .longitude(bookDto.getLongitude())
                .build();
    }
}
