
import psycopg2
from config import password
from config import database
from config import user

# Connect to the database
try:

    connection = psycopg2.connect(database=database, user=user, password=password)
except Exception as e:
    print(e)
    exit()


cursor = connection.cursor()
id = str(4)
query = '''select name, city, state, region_id, zip
from schools
WHERE region_id = ''' + id
try:
    cursor.execute(query)
    for row in cursor:
        print(row)
    print()

except Exception as e:
    print(e)
    exit()


# Don't forget to close the database connection.
connection.close()
