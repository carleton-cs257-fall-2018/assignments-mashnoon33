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


def get_ssh_keys(query):
    api_token = '340187c3b6aa4d838984bcff8594c47d'
    api_url_base = "http://www.omdbapi.com/?i=tt3896198&apikey=515febc6"
    response = requests.get(api_url_base, params={"s": query} )
    jsonify = json.loads(response.text)
    print(jsonify)
        # print(item['display_title'], item['mpaa_rating'], item['opening_date'])

#
    # response = requests.get(api_url, headers=headers)

def main():
    print(sys.argv[1])
    get_ssh_keys(sys.argv[1])


if __name__ == '__main__':
    # When I use argparse to parse my command line, I usually
    # put the argparse setup here in the global code, and then
    # call a function called main to do the actual work of
    # the program.

    main()
