'''
    api-test.py
    Jeff Ondich, 11 April 2016
    Edited by Mash Ibetsum, Justin Yamada, and Justin Hahn

    An example for CS 257 Software Design. How to retrieve results
    from an HTTP-based API, parse the results (JSON in this case),
    and manage the potential errors.
'''

import json
import requests
import sys

class Apitest:
    # Initializes an initial dictionary
    def __init__(self):
        self.api_url_base = "http://www.omdbapi.com/?apikey=515febc6&"
        self.initial= {}

    # Calls the getMovieByName, getMovieByID, and search given the correct type
    def fetch(self,type, param):
        if type=="search":
            self.search(param)
        elif type=="title":
            self.getMovieByName(param)
        elif type=="id":
            self.getMovieByID(param)
        else:
            raise ValueError('WARNING : WARNING : Only acceptable values for searchType is [search, title, id]')
        
    # Prints all of the API's details about a movie given a movie's name
    def getMovieByName(self, param):
        self.initial["t"] = param
        response = requests.get(self.api_url_base, params=self.initial)
        jsonify = json.loads(response.text)
        try:
            self._printer(jsonify)
        except KeyError:
            print('\n No Movies found')

    # Prints all of the API's details about a movie given a movie's id
    def getMovieByID(self, param):
        self.initial["i"] = param
        response = requests.get(self.api_url_base, params=self.initial)
        jsonify = json.loads(response.text)
        try:
            self._printer(jsonify)
        except KeyError:
            print('\n No Movies found')

    # Prints ten movies that contain the given search term with minimal details
    def search(self,param):
        self.initial["s"] = param
        response = requests.get(self.api_url_base, params=self.initial )
        jsonify = json.loads(response.text)
        try:
            for item in jsonify['Search'] :
                self._printer(item)
        except KeyError:
            print('\n No Movies found')
    
    # Prints the given dictionary with certain keys removed in a nice format
    def _printer(self,movieDict):
        dict = movieDict
        for key in dict:
            if key not in ["Response", "Poster","imdbVotes", "imdbID", "Website"] and type(dict[key])==type(""):
                print(key , ":" , dict[key])
        print("\n")

    def addParam(self, key, value):
        self.initial[key] = value

def main():
    # Checks if any parameters were incorrectly typed or not typed
    if len(sys.argv) >2:
        run = Apitest()
        for i in range(3,len(sys.argv)):
            if i==3:
                if sys.argv[3] in ["movie", "series", "episode"]:
                    run.addParam('type',sys.argv[3])
                else:
                    raise ValueError("WARNING : Only acceptable values for type is [movie, series, episode]")
            if i==4:
                try:
                    if int(sys.argv[4])>0:
                        run.addParam('y',sys.argv[4])
                except ValueError:
                    print('\nWARNING : Only Positive Numericals allowed, running the search without a year param\n')

        run.fetch(sys.argv[1],sys.argv[2])

    else:
        print("Usage: \npython3 api-test.py {search, title, id} <search paramaters> \n\noptional arguments: \n[movie, series, episode]  --> Inidicate the type of Media \n<Year> ---> Indiciate the year released (In order to use Year, You muse specify the type of Media) \n\nOnly the first two arguements are required.\n\nExamples :\npython3 api-test.py search ass movie 2013\npython3 api-test.py title 'Eighth Grade'")


if __name__ == '__main__':
    main()
