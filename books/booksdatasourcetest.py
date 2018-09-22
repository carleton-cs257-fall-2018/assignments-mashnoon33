'''
    booksdatasourcetest.py
    Mash Ibtesum, 18 Septembor 2018

    Tests booksdatasource.py
'''
import unittest
from booksdatasource import BooksDataSource

class testBooks(unittest.TestCase):
    def setUp(self):
        self.bds=BooksDataSource('book.csv', 'authors.csv', 'link.csv')

    def test_book(self):
        self.assertEqual(self.bds.book(6),{'id': 193, 'title': 'Good Omens', 'publication_year': 1990})

    def test_book_False(self):
        self.assertNotEqual(self.bds.book(6),{})

    def test_bookWithAuthorId(self):
        temp =self.bds.books(author_id = 5)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        for book in temp:
            self.assertEqual(book, {'id': 193, 'title': 'Good Omens', 'publication_year': 1990})

    def test_bookWithSearch_text(self):
        temp =self.bds.books(search_text = {'id': 193, 'title': 'Good Omens', 'publication_year': 1990})
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        for book in temp:
            self.assertEqual(book, {'id': 193, 'title': 'Good Omens', 'publication_year': 1990})

    def test_bookWithStart_year(self):
        temp =self.bds.books(start_year = 1990)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        for book in temp:
            self.assertEqual(book, {'id': 193, 'title': 'Good Omens', 'publication_year': 1990})

    def test_bookWithEnd_Year(self):
        temp =self.bds.books(end_year = 1990)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        for book in temp:
            self.assertEqual(book, {'id': 193, 'title': 'Good Omens', 'publication_year': 1990})

    def test_author(self):
        self.assertEqual(self.bds.author(5),{'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
         'birth_year': 1960, 'death_year': None})

    def test_authorWithBook_id(self):
        temp =self.bds.authors(book_id=6)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        for book in temp:
            self.assertEqual(book, {'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None})

    def test_authorWithSearch_text(self):
        temp =self.bds.authors(search_text= "Gaiman")
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        for book in temp:
            self.assertEqual(book, {'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None})

    def test_authorWithStart_year(self):
        temp =self.bds.authors(start_year= 1960)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        for book in temp:
            self.assertEqual(book, {'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None})

    def test_authorWithEnd_year(self):
        temp =self.bds.authors(end_year= 2015)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        for book in temp:
            self.assertEqual(book, {'id': 6, 'last_name': 'Pratchett', 'first_name': 'Terry',
         'birth_year': 1948, 'death_year': 2015})

    def test_authorWithEnd_year_null(self):
        temp =self.bds.authors(end_year= None)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        for book in temp:
            self.assertEqual(book, {'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None})


if __name__ == "__main__":
    unittest.main() # run all tests
