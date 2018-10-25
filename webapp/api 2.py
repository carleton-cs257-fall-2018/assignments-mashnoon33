'''
    api.py
    Mash Ibtesum, October 23, 2018
    Simple API to retrive data from the schools databse
'''

import psycopg2
import sys
import flask
import json
import ast
from config import *
from flask import request
from urllib.parse import urlparse, parse_qs

app = flask.Flask(__name__, static_folder='static')


@app.after_request
def after_request(response):
  response.headers.add('Access-Control-Allow-Origin', '*')
  response.headers.add('Access-Control-Allow-Headers', 'Content-Type,Authorization')
  response.headers.add('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS')
  return response


@app.route('/')
def hello():
    print("hello")
    return('Youve reached the home of the APIIII')

@app.route('/schools/',methods=['GET'])
def schools():
    try:
        response  = getSchools(
        ast.literal_eval(request.args.get('adm_rate')),
        ast.literal_eval(request.args.get('sat_avg')),
        ast.literal_eval(request.args.get('region_id')),
        ast.literal_eval(request.args.get('ACTCMMID'.lower())),
        ast.literal_eval(request.args.get('md_earn_wne_p10')),
        ast.literal_eval(request.args.get('COSTT4_A'.lower()))
        )
        return(json.dumps(response, indent=4))
    except Exception as e:
        print(e)
        return("Wrong query. Check the API doc because the example is too long")


@app.route('/school/',methods=['GET'])
def school():
    try:
        response = getSchool(int(request.args.get('opeid')))
        return(json.dumps(response, indent=4))
    except Exception as e:
        print(e)
        return("Wrong query. Check the console log. \nExcepted structure : /school/?opeid=[insertOpeidHere]")

@app.route('/schools/name/',methods=['GET'])
def schoolsByName():
    try:
        response = getSchoolsByName(str(request.args.get('name')))
        return(json.dumps(response, indent=4))
    except Exception as e:
        print(e)
        return("Wrong query. Check the console log. \nExcepted structure : /schools/name/?name=[insertNameHere]")

@app.route('/states/', methods=['GET'])
def states():
    try:
        return(json.dumps(getStates(), indent=4))
    except Exception as e:
        print(e)
        return("Contact your sys admin")

# TODO: implement /major and /regions and also enum.
# TODO: Add sort
# TODO: Refactor usign jeffs example


def getConnectection():
    '''
    Returns a connection to the database described
    in the config module. Returns None if the
    connection attempt fails.
    '''
    try:
        connection = psycopg2.connect(database=database, user=user, password=password)
    except Exception as e:
        print(e)
        exit()
    return connection

def getSchools(adm_rate=[0, 1], sat_avg=[1000, 1600], region_id=None, ACTCMMID=[0,36], md_earn_wne_p10=[0,200000], COSTT4_A=[0,100000]):
    try:
        connection = getConnectection()
        cursor = connection.cursor()
        query = f'''
        SELECT name, CITY, state, OPEID, ADM_RATE,actcmmid, SAT_AVG, UGDS_WHITE, COSTT4_A, MD_EARN_WNE_P10
        FROM schools
        WHERE adm_rate >= {adm_rate[0]}
        AND adm_rate <= {adm_rate[1]}
        AND sat_avg >= {sat_avg[0]}
        AND sat_avg <={sat_avg[1]}
        AND md_earn_wne_p10 >= {md_earn_wne_p10[0]}
        AND md_earn_wne_p10 <= {md_earn_wne_p10[1]}
        AND ACTCMMID >= {ACTCMMID[0]}
        AND ACTCMMID <={ACTCMMID[1]}
        AND COSTT4_A >= {COSTT4_A[0]}
        AND COSTT4_A <={COSTT4_A[1]}
        '''
        if region_id:
            query+= "\n AND region_id = " + str(region_id)
        cursor.execute(query)
        answer = []

        # header = [field[0] for field in cursor.description]
        header = ['name', 'city', 'state', 'opeid', 'adm_rate','actcmmid', 'sat_avg', 'ugds_white', 'costt4_a', 'md_earn_wne_p10']
        body = []
        for row in cursor:
            body.append(row)

        for school in body:
            school_dict = {}
            for i in range(0, len(header)):
                school_dict[header[i]] = school[i]
            answer.append(school_dict)
        connection.close()
        return answer

    except Exception as e:
        print(e)
        connection.close()
        return Nonec

def getSchoolsByName(name):
    try:
        connection = getConnectection()
        cursor = connection.cursor()
        query =f'''
        SELECT name, CITY, state, OPEID, ADM_RATE, SAT_AVG, UGDS_WHITE, COSTT4_A, MD_EARN_WNE_P10
        FROM schools
        WHERE name ilike '%{name}%'
        '''

        cursor.execute(query)
        answer = []
        # header = [field[0] for field in cursor.description]
        header = ['name', 'city', 'state', 'opeid', 'adm_rate', 'sat_avg', 'ugds_white', 'costt4_a', 'md_earn_wne_p10']
        body = []

        for row in cursor:
            body.append(row)

        for school in body:
            school_dict = {}
            for i in range(0, len(header)):
                school_dict[header[i]] = school[i]
            answer.append(school_dict)
        connection.close()
        return answer

    except Exception as e:
        connection.close()
        print(e)
        return None

def getStates():
    try:
        connection = getConnectection()
        cursor = connection.cursor()
        query =f'''
        SELECT name, abbr
        FROM states
        '''

        cursor.execute(query)
        answer = {}
        answer["sucess"] = True
        answer["results"] = []
        # header = [field[0] for field in cursor.description]

        for row in cursor:
            td = {}
            td["value"]= row[1]
            td["name"]= row[0]
            td["text"]= row[0]
            answer["results"].append(td)
        connection.close()
        return answer

    except Exception as e:
        print(e)
        connection.close()
        return None


def getSchool(opeid):
    try:
        connection = getConnectection()
        cursor = connection.cursor()
        query =f'''
        SELECT *
        FROM schools
        WHERE opeid = {opeid}
        '''
        cursor.execute(query)
        header = [field[0] for field in cursor.description]
        body = [row for row in cursor]

        for school in body:
            school_dict = {}
            for i in range(0, len(header)):
                school_dict[header[i]] = school[i]
        connection.close()
        return school_dict

    except Exception as e:
        connection.close()
        print(e)
        return None

if __name__ == '__main__':

    if len(sys.argv) != 3:
        print('Usage: {0} host port'.format(sys.argv[0]))
        print('  Example: {0} perlman.mathcs.carleton.edu 5101'.format(sys.argv[0]))
        exit()
    host = sys.argv[1]
    port = int(sys.argv[2])
    app.run(host=host, port=port, debug=True)
