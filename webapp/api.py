import psycopg2
import sys
import flask
import json
from flask import request
import ast

app = flask.Flask(__name__)

from config import password
from config import database
from config import user
from urllib.parse import urlparse, parse_qs

app = flask.Flask(__name__)

@app.route('/')
def hello():
    print("hello")
    return('Youve reached the home of the APIIII')

@app.route('/schools/',methods=['GET'])
def schools():
    callback  = run.getSchools(
    ast.literal_eval(request.args.get('adm_rate')),
    ast.literal_eval(request.args.get('sat_avg')),
    ast.literal_eval(request.args.get('region_id')),
    ast.literal_eval(request.args.get('ACTCMMID'.lower())),
    ast.literal_eval(request.args.get('md_earn_wne_p10')),
    ast.literal_eval(request.args.get('COSTT4_A'.lower()))
    )
    return(json.dumps(callback, indent=4))

@app.route('/school/',methods=['GET'])
def school():
    try:
        callback = run.getSchool(int(request.args.get('opeid')))
        return(json.dumps(callback, indent=4))
    except Exception as e:
        print(e)
        return("Wrong query. Check the console log. \nExcepted structure : /school/?opeid=[insertOpeidHere]")

@app.route('/schools/name/',methods=['GET'])
def schoolsByName():
    try:
        callback = run.getSchoolsByName(str(request.args.get('name')))
        return(json.dumps(callback, indent=4))
    except Exception as e:
        print(e)
        return("Wrong query. Check the console log. \nExcepted structure : /schools/name/?name=[insertNameHere]")


class api:
    def connect(self):
        try:
            self.connection = psycopg2.connect(database=database, user=user, password=password)
        except Exception as e:
            print(e)
            exit()

    def getSchools(self,adm_rate=[0, 1], sat_avg=[1000, 1600], region_id=None, ACTCMMID=[0,36], md_earn_wne_p10=[0,200000], COSTT4_A=[0,100000]):
        try:
            cursor = self.connection.cursor()
            query =f'''
            SELECT INSTNM, CITY, STABBR, OPEID, ADM_RATE, SAT_AVG, UGDS_WHITE, COSTT4_A, MD_EARN_WNE_P10
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
            header = ['instnm', 'city', 'stabbr', 'opeid', 'adm_rate', 'sat_avg', 'ugds_white', 'costt4_a', 'md_earn_wne_p10']
            body = []

            for row in cursor:
                body.append(row)

            for school in body:
                school_dict = {}
                for i in range(0, len(header)):
                    school_dict[header[i]] = school[i]
                answer.append(school_dict)
            return answer

        except Exception as e:
            print(e)
            exit()

    def getSchoolsByName(self,name):
        try:
            cursor = self.connection.cursor()
            query =f'''
            SELECT INSTNM, CITY, STABBR, OPEID, ADM_RATE, SAT_AVG, UGDS_WHITE, COSTT4_A, MD_EARN_WNE_P10
            FROM schools
            WHERE INSTNM ilike '%{name}%'
            '''

            cursor.execute(query)
            answer = []
            # header = [field[0] for field in cursor.description]
            header = ['instnm', 'city', 'stabbr', 'opeid', 'adm_rate', 'sat_avg', 'ugds_white', 'costt4_a', 'md_earn_wne_p10']
            body = []

            for row in cursor:
                body.append(row)

            for school in body:
                school_dict = {}
                for i in range(0, len(header)):
                    school_dict[header[i]] = school[i]
                answer.append(school_dict)
            return answer

        except Exception as e:
            print(e)
            exit()




    def shutdown(self):
        self.connection.close()


    def getSchool(self, opeid):
        try:
            cursor = self.connection.cursor()
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
            return school_dict

        except Exception as e:
            print(e)
            exit()

if __name__ == '__main__':

    if len(sys.argv) != 3:
        print('Usage: {0} host port'.format(sys.argv[0]))
        print('  Example: {0} perlman.mathcs.carleton.edu 5101'.format(sys.argv[0]))
        exit()
    host = sys.argv[1]
    port = int(sys.argv[2])
    run = api()
    run.connect()
    app.run(host=host, port=port, debug=True)
    run.shutdown()