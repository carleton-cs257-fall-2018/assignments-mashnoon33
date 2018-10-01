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
    api_url_base = f"https://api.nytimes.com/svc/movies/v2/reviews/search.json"
    print(api_url_base)
    response = requests.get(api_url_base, headers={"api-key": api_token}, params={"query": query} )
    print(response)
    jsonify = json.loads(response.text)
    for item in jsonify['results'] :
        print(item)
        return
#
    # response = requests.get(api_url, headers=headers)

def main():
    get_ssh_keys(sys.argv[1])


if __name__ == '__main__':
    # When I use argparse to parse my command line, I usually
    # put the argparse setup here in the global code, and then
    # call a function called main to do the actual work of
    # the program.

    main()
