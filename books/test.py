# test.py
# Written by Justin Hahn
#
# Meant to Test books1.py, Not fully implemented yet
#
import unittest
import books1

class testBooks(unittest.TestCase):


    def setUp(self):
        self.data=books1.scanner('books.csv')

    def test_hello(self):
        self.assertEqual(len(books1.getBooks(self.data)), 31)

    def test_books(self):
        self.assertTrue(books1.search(books1.getBooks(self.data), "Omoo"))


if __name__ == "__main__":
    unittest.main() # run all tests
