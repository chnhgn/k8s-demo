import requests
import json

res = requests.get('http://fall.unx.sas.com:30050')

con = res.content.decode('utf-8')

assert(con.index('Hello world'))
