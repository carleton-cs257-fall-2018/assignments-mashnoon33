# books1.py
# Written by Mash Ibtesum, Justin Hahn & Justin Yamada
# Phase 1

import csv
import sys
import re

def scanner(file):
    '''parses the csv file and returns an array of csv rows'''
    booksData = []
    with open(file, newline='') as f:
        reader = csv.reader(f)
        for row in reader:
            booksData.append(row)
    return booksData

def getBooks(data):
    '''Returns a array of book names, unsorted'''
    books = []
    for row in data:
        books.append(row[0])
    return books

def getAuthors(data):
    '''Returns a array of book authors, unsorted'''
    authors = []
    authorsTemp = []
    authorsTempData = []
    authorsFinal = []

    # inserts data from CSV file into list
    for row in data:
        authors.append(row[2])

    # the authors are split using the re.split method which allows us to split using multiple params
    for author in authors:
        authorsTemp = re.split(' \(|\) and ',author)
        authorsTemp.pop()
        if len(authorsTemp) > 1:
            del authorsTemp[1]
        for item in authorsTemp:
            authorsTempData.append(item)

    return authorsTempData

def sort(array, type):
    '''Sorts array according to its type'''
    if len(sys.argv)<4:
        return array

    if type == "author" :
        # Uses sorted to sort accordin to the last name
        sortedArray = sorted(array, key=lambda x:x.split(" ")[-1])
    elif type == "book" :
        sortedArray = sorted(array)

    if sys.argv[3] == "forward" :
        return sortedArray
    elif sys.argv[3] == "reverse" :
        return sortedArray[::-1]
    else :
        sys.exit("Please use either \"forward\" or \"reverse\" to specify order")

def printer(array) :
    '''Prints each element of the array NICELEY'''
    for item in array :
        print(item)

def main():

    # prompts user for CSV file input if none provided
    if len(sys.argv)<2:
        sys.exit("What CSV file do you want me to use?")

    # reacts to authors or books input in order to know what to sort
    if sys.argv[2] == "books":
        books = getBooks(scanner(sys.argv[1]))
        sortedBooks = sort(books, "book")
        printer(sortedBooks)

    elif sys.argv[2] == "authors":
        authors = getAuthors(scanner(sys.argv[1]))
        sortedAuthors = sort(authors, "author")
        printer(sortedAuthors)

    # if wrong input stops program and asks for correct books and authors input
    else :
        sys.exit("Please speficiy either \"books\" or \"authors\"" )

if __name__=="__main__":
    main()
