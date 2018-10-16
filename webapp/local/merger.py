import sys
import re
import csv

def main(csv_file_name):

    csv_file = open(csv_file_name, encoding='utf-8')
    reader = csv.reader(csv_file)
    for row in reader:
        print("\""+row[0] +"\" " + ":"  + row[1]+" ,")
    csv_file.close()

main("api_fields.csv")
