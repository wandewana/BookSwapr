-- Clear existing data to avoid duplicates on restart
TRUNCATE TABLE books RESTART IDENTITY;

-- Seed initial book data
INSERT INTO books (title, author, isbn, publication_year, latitude, longitude) VALUES
('The Lord of the Rings', 'J.R.R. Tolkien', '978-0618640157', 1954, 37.7749, -122.4194),
('Dune', 'Frank Herbert', '978-0441013593', 1965, 37.8044, -122.2712),
('The Great Gatsby', 'F. Scott Fitzgerald', '978-0743273565', 1925, 40.7128, -74.0060),
('1984', 'George Orwell', '978-0451524935', 1949, 51.5074, -0.1278),
('To Kill a Mockingbird', 'Harper Lee', '978-0061120084', 1960, 41.8781, -87.6298);
