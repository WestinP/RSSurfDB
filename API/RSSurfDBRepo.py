# Path: API\RSsurfDBRepo.py
import mysql.connector
import os
import subprocess
import requests
import arrow

subprocess.call("mysql -u root -proot < Scripts\RSSurfDB.sql", shell=True)

# Get first hour of today
start = arrow.now().floor('day')

# Get last hour of today
end = start.shift(days=+1).floor('day')

db = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="root",
    database="RSsurfDB"
)

cursor = db.cursor()

cursor.execute("SHOW TABLES")

def get_data():
    response = requests.get(
        'https://api.stormglass.io/v2/weather/point',
        params={
            'lat': 29.4795,
            'lng': -81.1237,
            'params': ','.join(['windSpeed', 'windDirection', 'waveHeight', 'waveDirection', 'wavePeriod']),
            'start': start.to('UTC'),  # Convert to UTC timestamp
            'end': start.to('UTC')  # Convert to UTC timestamp
        },
        headers={
            'Authorization': '5a269510-ba9e-11ed-bc36-0242ac130002-5a26957e-ba9e-11ed-bc36-0242ac130002'
        }
    )
    json_data = response.json()
    for hour in json_data['hours']:
        cursor.execute("CALL insertWind(%s, %s, %s, %s)", (start, "Flager Beach", json_data['hours'][hour]['windDirection']['noaa'], json_data['hours'][hour]['windSpeed']['noaa']))
        cursor.execute("CALL insertSwell(%s, %s, %s, %s, %s)", (start, "Flager Beach", json_data['hours'][hour]['waveHeight']['noaa'], json_data['hours'][hour]['waveDirection']['noaa'], json_data['hours'][hour]['wavePeriod']['noaa']))


