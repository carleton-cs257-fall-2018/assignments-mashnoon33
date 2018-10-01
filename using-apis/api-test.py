'''
    api-test.py
    Jeff Ondich, 11 April 2016

    An example for CS 257 Software Design. How to retrieve results
    from an HTTP-based API, parse the results (JSON in this case),
    and manage the potential errors.
'''

import json
import requests


def get_ssh_keys(q):
    api_token = '340187c3b6aa4d838984bcff8594c47d'
    query = q
    api_url_base = 'https://api.nytimes.com/svc/movies/v2/reviews/search.json'
    cat = requests.get(api_url_base, headers={"api-key": api_token, "query" : q})
    sanity = json.loads(cat.text)
    for item in sanity['results'] :
        x+=1
        print(item)
        return
#
    # response = requests.get(api_url, headers=headers)

def main():
    get_ssh_keys(q)


if __name__ == '__main__':
    # When I use argparse to parse my command line, I usually
    # put the argparse setup here in the global code, and then
    # call a function called main to do the actual work of
    # the program.

    main()
