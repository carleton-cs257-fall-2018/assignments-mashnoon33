'''
    api-test.py
    Jeff Ondich, 11 April 2016

    An example for CS 257 Software Design. How to retrieve results
    from an HTTP-based API, parse the results (JSON in this case),
    and manage the potential errors.
'''

import json
import requests
import sys

class Apitest:
    def __init__(self,type, param):
        self.api_url_base = "http://www.omdbapi.com/?apikey=515febc6&"

        if type=="search":
            self.search(param)
        if type=="movie":
            self.getMovieByName(param)
        if type=="id":
            self.getMovieByID(param)


    def getMovieByName(self, param):
        response = requests.get(self.api_url_base, params={"t": param})
        jsonify = json.loads(response.text)
        try:
            self._printer(jsonify)
        except KeyError:
            print('\n No Movies found')


    def getMovieByID(self, param):
        response = requests.get(self.api_url_base, params={"i": param})
        jsonify = json.loads(response.text)
        try:
            self._printer(jsonify)
        except KeyError:
            print('\n No Movies found')

    def search(self,param):
        response = requests.get(self.api_url_base, params={"s": param} )
        jsonify = json.loads(response.text)
        try:
            for item in jsonify['Search'] :
                self._printer(item)
        except KeyError:
            print('\n No Movies found')

    def _printer(self,movieDict):
        dict = movieDict
        for key in dict:
            if key not in ["Response", "Poster","imdbVotes", "imdbID", "Website"] and type(dict[key])==type(""):
                print(key , ":" , dict[key])
        print("\n")

def main():
    if len(sys.argv) >1:
        run = Apitest(sys.argv[1],sys.argv[2])
    else:
        print("Usage statement")
#NOTE : FIX THIS


if __name__ == '__main__':
    # When I use argparse to parse my command line, I usually
    # put the argparse setup here in the global code, and then
    # call a function called main to do the actual work of
    # the program.

    main()
