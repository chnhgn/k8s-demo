from flask import Flask, json

api = Flask(__name__)

@api.route('/demo', methods=['GET'])
def get_demo():
    return 'Hello world'


if __name__ == '__main__':
    api.run(host='0.0.0.0')