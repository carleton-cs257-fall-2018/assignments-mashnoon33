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


def get_ssh_keys(query, string):
    api_token = '340187c3b6aa4d838984bcff8594c47d'
    api_url_base = "http://www.omdbapi.com/?apikey=515febc6&"

    response = requests.get(api_url_base, params={"s": query} )
    jsonify = json.loads(response.text)

    for item in jsonify['Search'] :
        response_ = requests.get(api_url_base, params={"i": item['imdbID']})
        jsonify_ = json.loads(response_.text)
        organize(jsonify_, string)



        # print(item['display_title'], item['mpaa_rating'], item['opening_date'])

#
    # response = requests.get(api_url, headers=headers)

def organize(movieDict, string):
    if string == "none":
        print(movieDict['Title'] + " : " + movieDict['Year'] + " - " + movieDict['Rated'])
    if string == "date":
        print(movieDict['Title'] + " : " + movieDict['Date'])
    if string == "time":
        print(movieDict['Title'] + " : " + movieDict['Time'])
    if string == "awards":
        print(movieDict['Title'] + " : " + movieDict['Awards'])
    if string == "language":
        print(movieDict['Title'] + " : " + movieDict['Language'])
    if string == "director":
        print(movieDict['Title'] + " : " + movieDict['Director'])
    if string == "writer":
        print(movieDict['Title'] + " : " + movieDict['Writer'])
    if string == "actors":
        print(movieDict['Title'] + " : " + movieDict['Actors'])
    if string == "plot":
        print(movieDict['Title'] + " : " + movieDict['Plot'])
    if string == "ratings":
        ratingsArray = movieDict['Ratings']
        for item in ratingsArray:
            print(item['Source', ":", item['Value']])
    else:
        raise ValueError("This is not a possible second argument")
    
def main():
    if len(sys.argv) >= 2:
        print('Searching for "' + sys.argv[1] + '"')
        print()
        if len(sys.argv) == 2:
            get_ssh_keys(sys.argv[1],"none")
        if len(sys.argv) < 3:
            print("Too many arguments")
        elif len(sys.argv[2]) == 3:
            print("test" + " " + sys.argv[2])
            get_ssh_keys(sys.argv[1],sys.argv[2])
    else:
        print("Enter a title to search the API for it e.g. | api-test.py moviename date/time/awards/language/director/writer/actors/plot/ratings")



if __name__ == '__main__':
    # When I use argparse to parse my command line, I usually
    # put the argparse setup here in the global code, and then
    # call a function called main to do the actual work of
    # the program.

    main()