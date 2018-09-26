'''
    booksdatasourcetest.py
    Mash Ibtesum, 18 Septembor 2018

    Tests booksdatasource.py
'''
import unittest
from booksdatasource import BooksDataSource

class testBooks(unittest.TestCase):
    def setUp(self):
        self.bds=BooksDataSource('books.csv', 'authors.csv', 'books_authors.csv')

    def test_book(self):
        self.assertEqual(self.bds.book(6),{'id': 6, 'title': 'Good Omens', 'publication_year': 1990})

    def test_book_False(self):
        self.assertNotEqual(self.bds.book(6),{})

    def test_bookWithAuthorId(self):
        temp =self.bds.books(author_id = 5)
        self.assertEqual(len(temp), 2)
        self.assertIn({'id': 6, 'title': 'Good Omens', 'publication_year': 1990}, temp)

    def test_bookWithSearch_text(self):
        temp =self.bds.books(search_text = 'Good Omens')
        self.assertEqual(len(temp), 1)
        self.assertIn({'id': 6, 'title': 'Good Omens', 'publication_year': 1990}, temp)

    def test_bookWithStart_year(self):
        temp =self.bds.books(start_year = 1990)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        self.assertIn({'id': 6, 'title': 'Good Omens', 'publication_year': 1990}, temp)

    def test_bookWithEnd_Year(self):
        temp =self.bds.books(end_year = 1990)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        self.assertIn({'id': 6, 'title': 'Good Omens', 'publication_year': 1990}, temp)

    def test_bookAll(self):
        temp =self.bds.books(end_year = 1990, author_id = 5, start_year = 1990)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one book")
        self.assertIn({'id': 6, 'title': 'Good Omens', 'publication_year': 1990}, temp)

    def test_bookAll_Fail(self):
        temp =self.bds.books(end_year = 1980, author_id = 5, start_year = 1990)
        self.assertEqual(len(temp), 0)

    def test_author(self):
        self.assertEqual(self.bds.author(5),{'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
         'birth_year': 1960, 'death_year': None})

    def test_authorWithBook_id(self):
        temp =self.bds.authors(book_id=6)
        self.assertEqual(len(temp), 2)
        self.assertIn({'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None}, temp)

    def test_authorWithSearch_text(self):
        temp =self.bds.authors(search_text= "Gaiman")
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        self.assertIn({'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
         'birth_year': 1960, 'death_year': None}, temp)

    def test_authorWithStart_year(self):
        temp =self.bds.authors(start_year= 1960)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")
        self.assertIn({'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
         'birth_year': 1960, 'death_year': None}, temp)

    def test_authorWithEnd_year(self):
        temp =self.bds.authors(end_year= 2015)
        self.assertTrue(len(temp)>0, "The method is supposed to return atleast one author")

        self.assertIn({'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None}, temp)

    def test_authorAll(self):
        temp =self.bds.authors(book_id=6, start_year= 1960, end_year= 1960)
        self.assertEqual(len(temp), 1)

        self.assertIn({'id': 5, 'last_name': 'Gaiman', 'first_name': 'Neil',
     'birth_year': 1960, 'death_year': None}, temp)

    def test_getAllAuthors(self):
        temp =self.bds.authors(end_year= 9999999999999, sort_by='ss') #FUTUREPROOF
        print(temp)
        self.assertEqual(len(temp), 25)

if __name__ == "__main__":
    unittest.main() # run all tests
